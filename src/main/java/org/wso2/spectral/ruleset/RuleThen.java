package org.wso2.spectral.ruleset;

import org.wso2.spectral.functions.core.Alphabetical;
import org.wso2.spectral.functions.core.Casing;
import org.wso2.spectral.functions.core.ContainsKey;
import org.wso2.spectral.functions.core.DoNothing;
import org.wso2.spectral.functions.LintFunction;
import org.wso2.spectral.functions.core.Length;
import org.wso2.spectral.functions.core.Pattern;
import org.wso2.spectral.functions.core.Schema;
import org.wso2.spectral.functions.core.Truthy;

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
        else if (this.function.equals("pattern")) {
            this.lintFunction = new Pattern(functionOptions);
        }
        else if (this.function.equals("alphabetical")) {
            this.lintFunction = new Alphabetical(functionOptions);
        }
        else if (this.function.equals("containsKey")) {
            this.lintFunction = new ContainsKey(functionOptions);
        }
        else if (this.function.equals("length")) {
            this.lintFunction = new Length(functionOptions);
        }
        else if (this.function.equals("casing")) {
            this.lintFunction = new Casing(functionOptions);
        }
        else if (this.function.equals("schema")) {
            this.lintFunction = new Schema(functionOptions);
        }
        else {
            this.lintFunction = new DoNothing();
        }
    }
}
