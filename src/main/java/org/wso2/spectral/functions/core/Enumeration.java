package org.wso2.spectral.functions.core;

import org.wso2.spectral.document.LintTarget;
import org.wso2.spectral.functions.LintFunction;

import java.util.Map;

public class Enumeration extends LintFunction {

    public Enumeration(Map<String, Object> options) {
        super(options);
    }

    public boolean execute(LintTarget target) {
        String[] values = (String[]) options.get("values");
        for (String value : values) {
            if (target.value.equals(value)) {
                return true;
            }
        }
        return false;
    }
}
