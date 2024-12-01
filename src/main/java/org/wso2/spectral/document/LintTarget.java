package org.wso2.spectral.document;

import java.util.ArrayList;

public class LintTarget {
    public ArrayList<String> jsonPath;
    public final Object value;

    public LintTarget(ArrayList<String> jsonPath, Object value) {
        this.jsonPath = jsonPath;
        this.value = value;
    }
}
