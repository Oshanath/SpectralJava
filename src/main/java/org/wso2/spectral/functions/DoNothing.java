package org.wso2.spectral.functions;

import org.wso2.spectral.document.LintTarget;

public class DoNothing extends LintFunction{

        public DoNothing() {
            super(null);
        }

        public boolean execute(LintTarget target) {
            return true;
        }
}
