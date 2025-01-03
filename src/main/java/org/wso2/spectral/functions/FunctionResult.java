package org.wso2.spectral.functions;

import org.wso2.spectral.ruleset.Rule;

public class FunctionResult {
    public boolean passed;
    public String path;
    public String message;
    public Rule rule;

    public FunctionResult(boolean passed, String path, String message, Rule rule) {
        this.passed = passed;
        this.path = path;
        this.message = message;
        this.rule = rule;
    }

    public String toString() {
        return "Rule: " + rule.name + " {\n" +
                "\tpassed=" + passed +
                "\n\tpath='" + path + '\'' +
                "\n\tmessage='" + message + '\'' +
                "\n}";
    }
}
