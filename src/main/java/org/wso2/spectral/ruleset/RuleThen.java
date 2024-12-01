package org.wso2.spectral.ruleset;

import org.wso2.spectral.functions.DoNothing;
import org.wso2.spectral.functions.LintFunction;
import org.wso2.spectral.functions.Truthy;

import java.util.Map;

public class RuleThen {
    public String field;
    private String function;
    private Map<String, Object> functionOptions;
    public LintFunction lintFunction;

    public RuleThen(Map<String, Object> ruleThenData) {
        this.field = (String) ruleThenData.get("field");
        this.function = (String) ruleThenData.get("function");
        this.functionOptions = (Map<String, Object>) ruleThenData.get("functionOptions");

        if (this.function.equals("truthy")) {
            this.lintFunction = new Truthy(functionOptions);
        }
        else {
            this.lintFunction = new DoNothing();
        }
    }
}
