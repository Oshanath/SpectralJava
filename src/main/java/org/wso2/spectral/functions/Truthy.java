package org.wso2.spectral.functions;

import org.wso2.spectral.document.LintTarget;

import java.util.List;
import java.util.Map;

public class Truthy extends LintFunction {

    public Truthy(Map<String, Object> options) {
        super(options);
    }

    public FunctionResult execute(LintTarget target) {

        boolean result;

        if (target.value instanceof String) {
            result = !((String) target.value).isEmpty();
        }
        else if (target.value instanceof List) {
            result = !((List) target.value).isEmpty();
        }
        else if (target.value instanceof Map) {
            result = !((Map) target.value).isEmpty();
        }
        else {
            result = target.value != null;
        }

        FunctionResult functionResult = new FunctionResult();
        functionResult.result = result;
        functionResult.isFailure = !result;
        return functionResult;
    }
}
