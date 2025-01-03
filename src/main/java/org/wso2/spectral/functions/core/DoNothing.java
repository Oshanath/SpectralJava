package org.wso2.spectral.functions.core;

import org.wso2.spectral.document.LintTarget;
import org.wso2.spectral.functions.LintFunction;

public class DoNothing extends LintFunction {

        public DoNothing() {
            super(null);
        }

        public boolean execute(LintTarget target) {
            return true;
        }
}
