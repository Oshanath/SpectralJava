package org.wso2.spectral.ruleset;

import org.snakeyaml.engine.v2.api.Load;
import org.snakeyaml.engine.v2.api.LoadSettings;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Ruleset {
    public final Map<String, Rule> rules = new HashMap<>();

    public Ruleset(InputStream inputStream) {
        LoadSettings settings = LoadSettings.builder().build();
        Load yamlLoader = new Load(settings);

        Object yamlData = yamlLoader.loadFromInputStream(inputStream);

        // TODO: Assert valid ruleset

        if (yamlData instanceof Map) {
            Map<String, Object> yamlMap = (Map<String, Object>) yamlData;
            Map<String, Object> rules = (Map<String, Object>) yamlMap.get("rules");

            for (Map.Entry<String, Object> entry : rules.entrySet()) {
                String ruleName = entry.getKey();
                Rule rule = new Rule((Map<String, Object>) entry.getValue());
                this.rules.put(ruleName, rule);
            }
        }
        else {
            throw new RuntimeException("Invalid YAML format");
        }
    }
}
