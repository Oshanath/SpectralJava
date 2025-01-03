package org.wso2.spectral.functions.core;

import org.wso2.spectral.document.LintTarget;
import org.wso2.spectral.functions.LintFunction;

import java.util.Map;

public class Alphabetical extends LintFunction {

    public Alphabetical(Map<String, Object> options) {
        super(options);
    }

    public boolean execute(LintTarget target) {
        return target.value.toString().matches("^[a-zA-Z\\s.,!?;:'\"-]*$");
    }

}
