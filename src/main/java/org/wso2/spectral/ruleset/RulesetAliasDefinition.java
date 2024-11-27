package org.wso2.spectral.ruleset;

import org.wso2.spectral.SpectralException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class to represent a ruleset alias definition.
 * An alias definition can be a simple alias or a complex alias.
 * A simple alias is a list of givens
 * A complex alias is a list of targets and a description
 */
public class RulesetAliasDefinition {
    private final String name;
    private String description;
    private ArrayList<RulesetAliasTarget> targets;
    private boolean isComplexAlias;
    private ArrayList<String> given;

    public RulesetAliasDefinition(String name, Object aliasObject) throws SpectralException{
        this.name = name;

        if (aliasObject instanceof List) {
            isComplexAlias = false;
            this.given = (ArrayList<String>) aliasObject;
        }
        else if (aliasObject instanceof Map) {
            isComplexAlias = true;
            Map<String, Object> aliasMap = (Map<String, Object>) aliasObject;
            this.description = (String) aliasMap.get("description");
            this.targets = new ArrayList<>();
            ArrayList<Object> targets = (ArrayList<Object>) aliasMap.get("targets");
            for (Object target : targets) {
                RulesetAliasTarget aliasTarget = new RulesetAliasTarget(target);
                this.targets.add(aliasTarget);
            }
        }
        else {
            throw new SpectralException("Invalid alias definition.");
        }
    }

    public boolean isComplexAlias() {
        return isComplexAlias;
    }
}

