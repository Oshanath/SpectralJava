package org.wso2.spectral.ruleset;

import org.wso2.spectral.SpectralException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RulesetAliasTarget {
    private ArrayList<String> formats;
    private ArrayList<String> given;

    public RulesetAliasTarget (Object targetObject) throws SpectralException {
        if (targetObject instanceof Map) {
            HashMap<String, Object> targetMap = (HashMap<String, Object>) targetObject;
            this.formats = (ArrayList<String>) targetMap.get("formats");
            this.given = (ArrayList<String>) targetMap.get("given");
        }
    }
}
