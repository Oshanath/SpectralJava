package org.wso2.spectral.document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.InvalidPathException;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.PathNotFoundException;
import org.snakeyaml.engine.v2.api.Load;
import org.snakeyaml.engine.v2.api.LoadSettings;
import org.wso2.spectral.functions.FunctionResult;
import org.wso2.spectral.ruleset.Rule;
import org.wso2.spectral.ruleset.RuleThen;
import org.wso2.spectral.ruleset.Ruleset;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Document {

    private String documentString;
    private Object document;
    private ArrayList<FunctionResult> results;

    public Document(InputStream inputStream) {
        results = new ArrayList<>();
        LoadSettings settings = LoadSettings.builder().build();
        Load yamlLoader = new Load(settings);
        Object yamlData = yamlLoader.loadFromInputStream(inputStream);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        this.documentString = gson.toJson(yamlData);

        this.document = JsonPath.parse(this.documentString).json();
        resolveReferences();
    }

    public ArrayList<FunctionResult> lint(Ruleset ruleset) {
        // TODO: Add parsing errors to the result set

        // TODO: Filter enabled and relevant rules

        for(Rule rule : ruleset.rules.values()) {
            for(String given : rule.given) {
                try {
                    Configuration config = Configuration.builder().options(Option.AS_PATH_LIST).build();
                    List<String> paths = JsonPath.using(config).parse(this.document).read(given);
                    for (String path : paths) {
                        lintNode(path, rule);
                    }
                } catch(PathNotFoundException e) {
//                    System.out.println("Json Path not found: " + given);
                } catch (InvalidPathException e) {
//                    System.out.println("Unsupported Json Path: " + given);
                    // TODO: Implement json path plus features
//                    e.printStackTrace();
                }
            }
        }

        return results;
    }

    private void resolveReferences() {
        // TODO: Resolve references
        /**
         * A document Inventory maintains a graph (non-circular) pointing to other documents via refs. When a ref is in
         * a document, it adds a Node in the graph pointing to the document, and if there are refs within that ref, that
         * node will point to another node with a ref.
         *
         * When traversing the json paths for linting with a ruleset, it will get the closest path until the ref, then
         * traverse the rest on the ref doc, and this is done recursively.
         */
    }

    private void lintNode(String path, Rule rule) {
        Object node;
        try {
            node = JsonPath.read(this.document, path);
        } catch (PathNotFoundException e) {
            return;
        }
        for (RuleThen then : rule.then) {
            ArrayList<LintTarget> lintTargets = getLintTargets(node, then);

            for (LintTarget target : lintTargets) {
                boolean result = then.lintFunction.execute(target);
                String targetPath = target.getPathString();
                results.add(new FunctionResult(result, path + targetPath, rule.message));
            }
        }
    }

    private ArrayList<LintTarget> getLintTargets(Object node, RuleThen then) {
        ArrayList<LintTarget> lintTargets = new ArrayList<>();

        if ((node instanceof List || node instanceof Map) && (then.field != null && !then.field.isEmpty())) {
            if (then.field.equals("@key")) {
                if (node instanceof Map) {
                    Map<String, Object> map = (Map<String, Object>) node;
                    for (String key : map.keySet()) {
                        lintTargets.add(new LintTarget(new ArrayList<>(Arrays.asList(key)), key));
                    }
                }
                else if (node instanceof List) {
                    // TODO: Test
                    List<Object> list = (List<Object>) node;
                    for (int i = 0; i < list.size(); i++) {
                        lintTargets.add(new LintTarget(new ArrayList<>(Arrays.asList(String.valueOf(i))),
                                String.valueOf(i)));
                    }
                }
                else {
                    throw new RuntimeException("Node is not a Map or List but the field is @key");
                }
            }
            else if (then.field.startsWith("$")) {
                Configuration config = Configuration.builder().options(Option.AS_PATH_LIST).build();
                List<String> paths;
                try {
                    paths = JsonPath.using(config).parse(node).read(then.field);
                } catch (PathNotFoundException e) {
                    return lintTargets;
                }

                for (String path : paths) {
                    ArrayList<String> splitPath = splitJsonPath(path);
                    Object value;
                    try {
                        value = JsonPath.read(node, path);
                        lintTargets.add(new LintTarget(splitPath, value));
                    } catch (PathNotFoundException ignored) {}
                }
            }
            else {
                ArrayList<String> path = toPath(then.field);
                Object value;
                try {
                    value = JsonPath.read(node, then.field);
                } catch (PathNotFoundException e) {
                    return lintTargets;
                }
                lintTargets.add(new LintTarget(path, value));
            }
        }
        else {
            lintTargets.add(new LintTarget(new ArrayList<>(), node));
        }

        return lintTargets;
    }

    public static ArrayList<String> splitJsonPath(String jsonPath) {
        ArrayList<String> parts = new ArrayList<>();
        StringBuilder currentPart = new StringBuilder();
        boolean insideBrackets = false;

        for (char ch : jsonPath.toCharArray()) {
            if (ch == '[') {
                insideBrackets = true;
                continue; // Skip the opening bracket
            } else if (ch == ']') {
                insideBrackets = false;
                parts.add(currentPart.toString().replace("'", "").replace("\"", ""));
                currentPart.setLength(0); // Clear the current part
            } else if (insideBrackets) {
                currentPart.append(ch);
            }
        }

        return parts;
    }

    public static ArrayList<String> toPath(String path) {
        ArrayList<String> segments = new ArrayList<>();

        // Regex to match either dot-separated keys or bracket notation
        Pattern pattern = Pattern.compile(
                "\\[(?:'([^']*)'|\"([^\"]*)\")\\]|([^.\\[\\]]+)"
        );
        Matcher matcher = pattern.matcher(path);

        while (matcher.find()) {
            if (matcher.group(1) != null) {
                // Single-quoted key in brackets
                segments.add(matcher.group(1));
            } else if (matcher.group(2) != null) {
                // Double-quoted key in brackets
                segments.add(matcher.group(2));
            } else if (matcher.group(3) != null) {
                // Unquoted index or key in brackets
                segments.add(matcher.group(3));
            } else if (matcher.group(4) != null) {
                // Dot-separated key
                segments.add(matcher.group(4));
            }
        }

        return segments;
    }
}
