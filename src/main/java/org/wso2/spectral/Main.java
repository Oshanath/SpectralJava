package org.wso2.spectral;

import org.wso2.spectral.ruleset.Ruleset;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        try {
            InputStream inputStream = new FileInputStream("/Users/oshanath/spectral/owasp_top_10.yaml");
            Ruleset ruleset = new Ruleset(inputStream);
            System.out.println(ruleset.rules);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}