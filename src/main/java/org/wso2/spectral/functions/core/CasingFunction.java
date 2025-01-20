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
import java.util.regex.Pattern;

/**
 * Function to check the casing of a string
 */
@FunctionName("casing")
public class CasingFunction extends LintFunction {

    private static final String flat = "[a-z][a-z{__DIGITS__}]*";
    private static final String camel = "[a-z][a-z{__DIGITS__}]*(?:[A-Z{__DIGITS__}](?:[a-z{__DIGITS__}]+|$))*";
    private static final String pascal = "[A-Z][a-z{__DIGITS__}]*(?:[A-Z{__DIGITS__}](?:[a-z{__DIGITS__}]+|$))*";
    private static final String kebab = "[a-z][a-z{__DIGITS__}]*(?:-[a-z{__DIGITS__}]+)*";
    private static final String cobol = "[A-Z][A-Z{__DIGITS__}]*(?:-[A-Z{__DIGITS__}]+)*";
    private static final String snake = "[a-z][a-z{__DIGITS__}]*(?:_[a-z{__DIGITS__}]+)*";
    private static final String macro = "[A-Z][A-Z{__DIGITS__}]*(?:_[A-Z{__DIGITS__}]+)*";

    private static final String digitPattern = "0-9";

    public CasingFunction(Map<String, Object> options) {
        super(options);
    }

    public boolean execute(LintTarget target) {
        String targetString = (String) target.value;

        if (targetString.length() == 1 &&
                options.containsKey("separator") &&
                (boolean) options.get("separator.allowLeading") &&
                targetString.equals(((Map<String, Object>) options.get("separator")).get("char"))) {
            return true;
        }

        return targetString.matches(getPattern(options));
    }

    private String getPattern(Map<String, Object> options) {
        String baseCase = (String) options.get("type");

        boolean allowdigits = false;
        if (options.containsKey("disallowDigits")) {
            allowdigits = !(boolean) options.get("disallowDigits");
        }

        String basePattern;

        if (baseCase.equals("flat")) {
            basePattern = flat;
        } else if (baseCase.equals("camel")) {
            basePattern = camel;
        } else if (baseCase.equals("pascal")) {
            basePattern = pascal;
        } else if (baseCase.equals("kebab")) {
            basePattern = kebab;
        } else if (baseCase.equals("cobol")) {
            basePattern = cobol;
        } else if (baseCase.equals("snake")) {
            basePattern = snake;
        } else if (baseCase.equals("macro")) {
            basePattern = macro;
        } else {
            throw new RuntimeException("Invalid case type");
        }

        String pattern = basePattern.replace("{__DIGITS__}", allowdigits ? digitPattern : "");

        if (!options.containsKey("separator")) {
            return "^" + pattern + "$";
        }

        Map<String, Object> separator = (Map<String, Object>) options.get("separator");
        String separatorChar = separator.get("char").toString();
        boolean allowLeading = (boolean) separator.get("allowLeading");

        String separatorPattern = "[" + Pattern.quote(separatorChar) + "]";
        String leadingSeparatorPattern = allowLeading ? separatorPattern + "?" : "";

        return "^" + leadingSeparatorPattern + pattern + "(?:" + separatorPattern + pattern + ")*$";
    }
}
