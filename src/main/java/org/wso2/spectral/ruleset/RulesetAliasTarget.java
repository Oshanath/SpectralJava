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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * This class represents the target of a ruleset alias which contains the list of formats it should apply to and the
 * given path.
 */
public class RulesetAliasTarget {
    private ArrayList<Format> formats;
    public ArrayList<String> given;

    public RulesetAliasTarget (Object targetObject) {
        if (targetObject instanceof Map) {
            Map<String, Object> targetMap = (Map<String, Object>) targetObject;
            this.formats = Format.getFormatListFromObject((List<String>) targetMap.get("formats"));
            this.given = (ArrayList<String>) targetMap.get("given");
        }
    }
}
