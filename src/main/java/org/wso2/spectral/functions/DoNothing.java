package org.wso2.spectral.functions;

import org.wso2.spectral.document.LintTarget;

public class DoNothing extends LintFunction{

        public DoNothing() {
            super(null);
        }

        public FunctionResult execute(LintTarget target) {
            return new FunctionResult();
        }
}
