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
package org.wso2.spectral.ruleset;

import org.wso2.spectral.functions.core.Alphabetical;
import org.wso2.spectral.functions.core.Casing;
import org.wso2.spectral.functions.core.ContainsKey;
import org.wso2.spectral.functions.core.Defined;
import org.wso2.spectral.functions.LintFunction;
import org.wso2.spectral.functions.core.Falsy;
import org.wso2.spectral.functions.core.Length;
import org.wso2.spectral.functions.core.Pattern;
import org.wso2.spectral.functions.core.Schema;
import org.wso2.spectral.functions.core.Truthy;
import org.wso2.spectral.functions.core.Undefined;
import org.wso2.spectral.functions.core.Xor;

import java.util.Map;

public class RuleThen {
    public String field;
    private String function;
    private Map<String, Object> functionOptions;
    public LintFunction lintFunction;

    public RuleThen(Map<String, Object> ruleThenData) {
        this.field = (String) ruleThenData.get("field");
        this.function = (String) ruleThenData.get("function");
        this.functionOptions = (Map<String, Object>) ruleThenData.get("functionOptions");

        if (this.function.equals("truthy")) {
            this.lintFunction = new Truthy();
        }
        else if (this.function.equals("falsy")) {
            this.lintFunction = new Falsy();
        }
        else if (this.function.equals("pattern")) {
            this.lintFunction = new Pattern(functionOptions);
        }
        else if (this.function.equals("alphabetical")) {
            this.lintFunction = new Alphabetical(functionOptions);
        }
        else if (this.function.equals("containsKey")) {
            this.lintFunction = new ContainsKey(functionOptions);
        }
        else if (this.function.equals("length")) {
            this.lintFunction = new Length(functionOptions);
        }
        else if (this.function.equals("casing")) {
            this.lintFunction = new Casing(functionOptions);
        }
        else if (this.function.equals("schema")) {
            this.lintFunction = new Schema(functionOptions);
        }
        else if (this.function.equals("defined")) {
            this.lintFunction = new Defined();
        }
        else if (this.function.equals("undefined")) {
            this.lintFunction = new Undefined();
        }
        else if (this.function.equals("xor")) {
            this.lintFunction = new Xor(functionOptions);
        }
        else {
            throw new RuntimeException("Unknown function: " + this.function);
        }
    }
}
