package org.wso2.spectral;

import org.wso2.spectral.document.Document;
import org.wso2.spectral.functions.FunctionResult;
import org.wso2.spectral.ruleset.Ruleset;
import org.wso2.spectral.ruleset.fileType.JsonRuleset;
import org.wso2.spectral.ruleset.fileType.YamlRuleset;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            InputStream rulesetStream = new FileInputStream("/Users/oshanath/spectral/ruleset.yaml");
            YamlRuleset ruleset = new YamlRuleset(rulesetStream);

            InputStream documentStream = new FileInputStream("/Users/oshanath/spectral/opeapitest.yaml");
            Document document = new Document(documentStream);
            ArrayList<FunctionResult> results = document.lint(ruleset);
            boolean allPassed = true;
            for (FunctionResult result : results) {
                if(!result.passed){
                    System.out.println(result);
                    allPassed = false;
                }
            }
            if(allPassed){
                System.out.println("All rules passed");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SpectralException e) {
            throw new RuntimeException(e);
        }
    }
}