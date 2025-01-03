package org.wso2.spectral.functions.core;

import org.wso2.spectral.SpectralException;
import org.wso2.spectral.document.LintTarget;
import org.wso2.spectral.functions.LintFunction;

import java.util.List;
import java.util.Map;

public class Length extends LintFunction {

    public Length(Map<String, Object> options) {
        super(options);
    }

    public boolean execute(LintTarget target) {
        int length;

        if (target.value instanceof String) {
            length = ((String) target.value).length();
        } else if (target.value instanceof List) {
            length = ((List) target.value).size();
        } else if (target.value instanceof Map) {
            length = ((Map) target.value).size();
        } else {
            return true;
        }

        if (options.containsKey("min") && options.containsKey("max")) {
            int min = (int) options.get("min");
            int max = (int) options.get("max");
            return length >= min && length <= max;
        }
       else  if (options.containsKey("min")) {
            int min = (int) options.get("min");
            return length >= min;
        }
        else if (options.containsKey("max")) {
            int max = (int) options.get("max");
            return length <= max;
        }
        else {
            return false;
        }
    }
}
