package org.wso2.spectral.functions.core;

import org.wso2.spectral.document.LintTarget;
import org.wso2.spectral.functions.LintFunction;

import java.util.Map;
import java.util.regex.Pattern;

public class Casing extends LintFunction {

    private final String flat = "[a-z][a-z{__DIGITS__}]*";
    private final String camel = "[a-z][a-z{__DIGITS__}]*(?:[A-Z{__DIGITS__}](?:[a-z{__DIGITS__}]+|$))*";
    private final String pascal = "[A-Z][a-z{__DIGITS__}]*(?:[A-Z{__DIGITS__}](?:[a-z{__DIGITS__}]+|$))*";
    private final String kebab = "[a-z][a-z{__DIGITS__}]*(?:-[a-z{__DIGITS__}]+)*";
    private final String cobol = "[A-Z][A-Z{__DIGITS__}]*(?:-[A-Z{__DIGITS__}]+)*";
    private final String snake = "[a-z][a-z{__DIGITS__}]*(?:_[a-z{__DIGITS__}]+)*";
    private final String macro = "[A-Z][A-Z{__DIGITS__}]*(?:_[A-Z{__DIGITS__}]+)*";

    private final String digitPattern = "0-9";

    public Casing(Map<String, Object> options) {
        super(options);
    }

    public boolean execute(LintTarget target) {
        String targetString = (String) target.value;

        if(targetString.length() == 1 &&
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
