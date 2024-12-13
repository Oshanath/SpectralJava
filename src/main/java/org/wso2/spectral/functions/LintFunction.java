package org.wso2.spectral.functions;

import org.wso2.spectral.InvalidFunctionOptionsException;
import org.wso2.spectral.document.LintTarget;

import java.util.Map;

public abstract class LintFunction {

    public Map<String, Object> options;

    public abstract boolean execute(LintTarget target);

    public LintFunction(Map<String, Object> options) {
        this.options = options;
    }
}
