package org.wso2.spectral.ruleset;

import org.snakeyaml.engine.v2.api.Load;
import org.snakeyaml.engine.v2.api.LoadSettings;
import org.wso2.spectral.SpectralException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Ruleset {
    public final Map<String, Rule> rules;
    public final ArrayList<RulesetAliasDefinition> aliases;
    public boolean hasComplexAliases;
    public ArrayList<String> formats;
    public ArrayList<Ruleset> extendsRulesets;

    public Ruleset(Map<String, Object> datamap) throws SpectralException{
        this.rules = new HashMap<>();
        this.aliases = new ArrayList<>();
        this.hasComplexAliases = false;

        assertValidRuleset(datamap);
        Map<String, Object> rules = (Map<String, Object>) datamap.get("rules");

        // Read rules
        for (Map.Entry<String, Object> entry : rules.entrySet()) {
            String ruleName = entry.getKey();
            Rule rule = new Rule(ruleName, (Map<String, Object>) entry.getValue());
            this.rules.put(ruleName, rule);
        }

        // Read aliases
        if(datamap.containsKey("aliases")) {
            Map<String, Object> aliases = (Map<String, Object>) datamap.get("aliases");
            for (Map.Entry<String, Object> entry : aliases.entrySet()) {
                RulesetAliasDefinition alias = new RulesetAliasDefinition(entry.getKey(), entry.getValue());
                this.aliases.add(alias);
                if(alias.isComplexAlias()) {
                    this.hasComplexAliases = true;
                }
            }
        }

        // TODO: Read extends

        // TODO: Read overrides

        // TODO: Merge Rules

    }

    private void assertValidRuleset(Object yamlData) throws SpectralException{
        if(!(yamlData instanceof  Map)) {
            throw new SpectralException("Invalid ruleset definition. Invalid YAML format");
        }
        Map<String, Object> yamlMap = (Map<String, Object>) yamlData;
        if(!yamlMap.containsKey("rules") && !yamlMap.containsKey("extends") && !yamlMap.containsKey("overrides")) {
            throw new SpectralException("Invalid ruleset definition. Ruleset must have rules or extends or overrides defined");
        }

        // TODO: Validate aliases

        // TODO: Validate by format

        // TODO: Validate function options
    }
}
