package org.wso2.spectral;

import org.wso2.spectral.document.Document;
import org.wso2.spectral.ruleset.Ruleset;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        try {
            InputStream rulesetStream = new FileInputStream("/Users/oshanath/spectral/.spectral.yaml");
            Ruleset ruleset = new Ruleset(rulesetStream);

            InputStream documentStream = new FileInputStream("/Users/oshanath/spectral/opeapitest.yaml");
            Document document = new Document(documentStream);
            document.lint(ruleset);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SpectralException e) {
            throw new RuntimeException(e);
        }
    }
}