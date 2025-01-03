package org.wso2.spectral.functions.core;

import org.wso2.spectral.document.LintTarget;
import org.wso2.spectral.functions.LintFunction;

import java.util.Map;

public class ContainsKey extends LintFunction {

    public ContainsKey(Map<String, Object> options) {
        super(options);
    }

    public boolean execute(LintTarget target) {
        if (target.value instanceof Map) {
            Object key = options.get("key");
            return ((Map) target.value).containsKey(key);
        }
        else {
            return false;
        }
    }
}
