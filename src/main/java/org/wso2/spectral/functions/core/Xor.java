package org.wso2.spectral.functions.core;

import org.wso2.spectral.document.LintTarget;
import org.wso2.spectral.functions.LintFunction;

import java.util.ArrayList;
import java.util.Map;

public class Xor extends LintFunction {

    public Xor(Map<String, Object> options) {
        super(options);
    }

    public boolean execute(LintTarget target) {
        ArrayList<String> properties = (ArrayList<String>) options.get("properties");
        int count = 0;
        for (String property : properties) {
            if (target.value instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) target.value;
                if (map.containsKey(property)) {
                    count++;
                }
            }
            else {
                throw new RuntimeException("Xor function can only be used with objects");
            }
        }

        return count == 1;
    }
}
