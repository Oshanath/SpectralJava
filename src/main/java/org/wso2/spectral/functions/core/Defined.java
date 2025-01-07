package org.wso2.spectral.functions.core;

import org.wso2.spectral.document.LintTarget;
import org.wso2.spectral.functions.LintFunction;

import java.util.Map;

public class Defined extends LintFunction {

    public Defined() {
        super(null);
    }

    public boolean execute(LintTarget target) {
        return target.value != null;
    }
}
