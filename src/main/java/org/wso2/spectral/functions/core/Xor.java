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

import java.util.ArrayList;
import java.util.Map;

@FunctionName("xor")
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
