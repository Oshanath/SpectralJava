/*
 *  Copyright (c) 2025, WSO2 LLC. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 LLC. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.spectral.functions.core;

import org.wso2.spectral.document.LintTarget;
import org.wso2.spectral.functions.FunctionName;
import org.wso2.spectral.functions.LintFunction;

import java.util.Map;
import java.util.regex.PatternSyntaxException;

@FunctionName("pattern")
public class Pattern extends LintFunction {

    public Pattern(Map<String, Object> options) {
        super(options);
    }

    public boolean execute(LintTarget target) {
        Object match = options.get("match");
        Object notMatch = options.get("notMatch");

        if (target.value == null) {
            return false;
        }
        if (!(target.value instanceof String)) {
            return true;
        }

        try {
            if (match != null)
                return target.value.toString().matches((String) match);
            else if (notMatch != null)
                return !target.value.toString().matches((String) notMatch);
            else
                throw new RuntimeException("Pattern function requires either match or notMatch options");
        } catch (PatternSyntaxException e) {
            System.out.println("Invalid regex pattern: " + e.getDescription());
            return false;
        }
    }
}
