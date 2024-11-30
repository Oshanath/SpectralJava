package org.wso2.spectral.ruleset;

import java.util.Map;

public class RuleThen {
    public String field;
    private String function;
    private Map<String, Object> functionOptions;

    public RuleThen(Map<String, Object> ruleThenData) {
        this.field = (String) ruleThenData.get("field");
        this.function = (String) ruleThenData.get("function");
        this.functionOptions = (Map<String, Object>) ruleThenData.get("functionOptions");
    }
}
