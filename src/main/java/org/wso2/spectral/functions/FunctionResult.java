package org.wso2.spectral.functions;

public class FunctionResult {
    public boolean passed;
    public String path;
    public String message;

    public FunctionResult(boolean passed, String path, String message) {
        this.passed = passed;
        this.path = path;
        this.message = message;
    }

    public String toString() {
        return "FunctionResult{" +
                "passed=" + passed +
                ", path='" + path + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
