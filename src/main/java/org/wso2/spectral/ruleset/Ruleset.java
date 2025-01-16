/*
 *  Copyright (c) 2025, WSO2 LLC. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 LLC. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.spectral.ruleset;

import org.wso2.spectral.SpectralException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static org.wso2.spectral.ruleset.RulesetAliasDefinition.resolveAliasGiven;

public class Ruleset {
    public final Map<String, Rule> rules;
    public final HashMap<String, RulesetAliasDefinition> aliases;
    public boolean hasComplexAliases;
    public ArrayList<Format> formats;
    public ArrayList<Ruleset> extendsRulesets;

    public Ruleset(Map<String, Object> datamap) throws SpectralException{
        this.rules = new HashMap<>();
        this.aliases = new HashMap<>();
        this.hasComplexAliases = false;

        assertValidRuleset(datamap);
        Map<String, Object> rules = (Map<String, Object>) datamap.get("rules");

        // Read aliases
        if(datamap.containsKey("aliases")) {
            Map<String, Object> aliases = (Map<String, Object>) datamap.get("aliases");
            for (Map.Entry<String, Object> entry : aliases.entrySet()) {
                RulesetAliasDefinition alias = new RulesetAliasDefinition(entry.getKey(), entry.getValue());
                this.aliases.put(entry.getKey(), alias);
                if(alias.isComplexAlias()) {
                    this.hasComplexAliases = true;
                }
            }
        }

        resolveAliasesInAliases();

        // Read rules
        for (Map.Entry<String, Object> entry : rules.entrySet()) {
            String ruleName = entry.getKey();
            Rule rule = new Rule(ruleName, (Map<String, Object>) entry.getValue(), this.aliases);
            this.rules.put(ruleName, rule);
        }

        // Read formats
        if(datamap.containsKey("formats")) {
            this.formats = Format.getFormatListFromObject((ArrayList<String>) datamap.get("formats"));
        }

        // TODO: Read extends

        // TODO: Read overrides

        // TODO: Merge Rules

    }

    private void resolveAliasesInAliases() {
        for (RulesetAliasDefinition alias : this.aliases.values()) {
            if (!alias.isComplexAlias()) {
                ArrayList<String> resolvedGiven = new ArrayList<>();
                for (String given : alias.given) {
                    if (given.startsWith("#")) {
                        resolvedGiven.addAll(resolveAliasGiven(given, this.aliases));
                    }
                    else {
                        resolvedGiven.add(given);
                    }
                }
                alias.given = resolvedGiven;
            }
            else{
                for (RulesetAliasTarget target: alias.targets) {
                    ArrayList<String> resolvedGiven = new ArrayList<>();
                    for (String given: target.given) {
                        if (given.startsWith("#")) {
                            resolvedGiven.addAll(resolveAliasGiven(given, this.aliases));
                        }
                    }
                    target.given = resolvedGiven;
                }
            }
        }
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
