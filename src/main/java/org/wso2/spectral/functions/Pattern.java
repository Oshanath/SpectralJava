package org.wso2.spectral.functions;

import org.wso2.spectral.InvalidFunctionOptionsException;
import org.wso2.spectral.document.LintTarget;

import java.util.Map;

public class Pattern extends LintFunction {

    public Pattern(Map<String, Object> options) {
        super(options);
    }

    public boolean execute(LintTarget target) {
        Object match = options.get("match");
        return target.value.toString().matches((String) match);
    }
}
