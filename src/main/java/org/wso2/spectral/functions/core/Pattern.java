package org.wso2.spectral.functions.core;

import org.wso2.spectral.document.LintTarget;
import org.wso2.spectral.functions.LintFunction;

import java.util.Map;

public class Pattern extends LintFunction {

    public Pattern(Map<String, Object> options) {
        super(options);
    }

    public boolean execute(LintTarget target) {
        // TODO: Implement notMatch
        Object match = options.get("match");
        return target.value.toString().matches((String) match);
    }
}
