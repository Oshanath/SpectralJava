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

import org.wso2.spectral.SpectralException;
import org.wso2.spectral.document.LintTarget;
import org.wso2.spectral.functions.FunctionName;
import org.wso2.spectral.functions.LintFunction;

import java.util.List;
import java.util.Map;

@FunctionName("length")
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
