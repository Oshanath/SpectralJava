package org.wso2.spectral.functions.core;

import org.wso2.spectral.document.LintTarget;
import org.wso2.spectral.functions.LintFunction;

public class Undefined extends LintFunction {

    public Undefined() {
        super(null);
    }

    public boolean execute(LintTarget target) {
        return target.value == null;
    }
}
