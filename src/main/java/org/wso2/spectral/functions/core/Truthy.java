package org.wso2.spectral.functions.core;

import org.wso2.spectral.document.LintTarget;
import org.wso2.spectral.functions.LintFunction;

import java.util.List;
import java.util.Map;

public class Truthy extends LintFunction {

    public Truthy(Map<String, Object> options) {
        super(options);
    }

    public boolean execute(LintTarget target) {
        if (target.value instanceof String) {
            return !((String) target.value).isEmpty();
        }
        else if (target.value instanceof List) {
            return !((List) target.value).isEmpty();
        }
        else if (target.value instanceof Map) {
            return !((Map) target.value).isEmpty();
        }
        else {
            return target.value != null;
        }
    }
}
