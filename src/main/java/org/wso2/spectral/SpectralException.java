package org.wso2.spectral;

public class SpectralException extends Exception {
    public SpectralException(String message) {
        super(message);
    }

    public SpectralException(String message, Throwable cause) {
        super(message, cause);
    }
}
