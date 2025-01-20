///*
// *  Copyright (c) 2025, WSO2 LLC. (http://www.wso2.org) All Rights Reserved.
// *
// *  WSO2 LLC. licenses this file to you under the Apache License,
// *  Version 2.0 (the "License"); you may not use this file except
// *  in compliance with the License.
// *  You may obtain a copy of the License at
// *
// *    http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing,
// * software distributed under the License is distributed on an
// * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// * KIND, either express or implied.  See the License for the
// * specific language governing permissions and limitations
// * under the License.
// */
//package org.wso2.spectral;
//
//import org.wso2.spectral.document.Document;
//import org.wso2.spectral.functions.FunctionResult;
//import org.wso2.spectral.ruleset.file.type.YamlRuleset;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.util.ArrayList;
//
///**
// * Main class to run the Spectral tool
// */
//public class Main {
//
//    public static void main(String[] args) {
//        try {
////            InputStream rulesetStream = new FileInputStream("/Users/oshanath/spectral/ruleset.yaml");
////            YamlRuleset ruleset = new YamlRuleset(rulesetStream);
//
//            InputStream rulesetStream = new FileInputStream("/Users/oshanath/spectral/rulesets/APIM Team Best " +
//                    "Practices.yaml");
//            YamlRuleset ruleset = new YamlRuleset(rulesetStream);
//
////            InputStream rulesetStream = new FileInputStream("/Users/oshanath/spectral/rulesets/OWASP Top 10.json");
////            Ruleset ruleset = new JsonRuleset(rulesetStream);
//
////            InputStream rulesetStream = new FileInputStream("/Users/oshanath/spectral/rulesets/WSO2 API Design
////            Guidelines.json");
////            Ruleset ruleset = new JsonRuleset(rulesetStream);
//
////            InputStream rulesetStream = new FileInputStream("/Users/oshanath/spectral/rulesets/WSO2 Style
////            Guidelines.json");
////            Ruleset ruleset = new JsonRuleset(rulesetStream);
//
//            InputStream documentStream = new FileInputStream("/Users/oshanath/spectral/opeapitest.yaml");
//            Document document = new Document(documentStream);
//            ArrayList<FunctionResult> results = document.lint(ruleset);
//            boolean allPassed = true;
//            for (FunctionResult result : results) {
//                if (!result.passed) {
//                    allPassed = false;
//                }
//            }
//            int passCount = 0;
//            int failCount = 0;
//            for (FunctionResult result : results) {
//                if (result.passed) {
//                    passCount++;
//                } else {
//                    failCount++;
//                }
//            }
//        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (SpectralException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
