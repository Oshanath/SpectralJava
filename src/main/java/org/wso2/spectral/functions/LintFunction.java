package org.wso2.spectral.functions;

import org.wso2.spectral.document.LintTarget;

import java.util.Map;

public abstract class LintFunction {

    private Map<String, Object> options;

    public abstract FunctionResult execute(LintTarget target);

    public LintFunction(Map<String, Object> options) {
        this.options = options;
    }
}
