package org.wso2.spectral.ruleset;

import org.wso2.spectral.DiagnosticSeverity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Rule {
    public String description;
    public String message;
    private DiagnosticSeverity severity;
    private boolean resolved;
    public List<RuleThen> then;
    public List<String> given;
    private List<String> formats;
    private boolean enabled;

    public Rule(Map<String, Object> ruleData) {
        Object descriptionObject = ruleData.get("description");
        Object messageObject = ruleData.get("message");
        Object severityObject = ruleData.get("severity");
        Object resolvedObject = ruleData.get("resolved");
        Object thenObject = ruleData.get("then");
        Object givenObject = ruleData.get("given");
        Object formatsObject = ruleData.get("formats");

        // TODO: Read enabled
        this.enabled = true;

        if (descriptionObject instanceof String) {
            this.description = (String) descriptionObject;
        }
        else {
            this.description = "";
        }

        if (messageObject instanceof String) {
            this.message = (String) messageObject;
        }
        else {
            this.message = "";
        }

        if (severityObject instanceof String) {
            this.severity = DiagnosticSeverity.valueOf(((String) severityObject).toUpperCase());
        }
        else {
            this.severity = DiagnosticSeverity.ERROR;
        }

        if (resolvedObject instanceof Boolean) {
            this.resolved = (Boolean) resolvedObject;
        }
        else {
            this.resolved = false;
        }

        if (formatsObject instanceof List) {
            this.formats = (List<String>) formatsObject;
        }
        else {
            this.formats = new ArrayList<>();
        }

        if (thenObject instanceof List) {
            this.then = new ArrayList<>();
            for (Object thenItem : (List<Object>) thenObject) {
                if (thenItem instanceof Map) {
                    this.then.add(new RuleThen((Map<String, Object>) thenItem));
                }
                else {
                    throw new RuntimeException("Invalid rule then or missing");
                }
            }
        }
        else if (thenObject instanceof Map) {
            this.then = new ArrayList<>();
            this.then.add(new RuleThen((Map<String, Object>) thenObject));
        }
        else {
            throw new RuntimeException("Invalid rule then or missing");
        }

        if (givenObject instanceof List) {
            this.given = (List<String>) givenObject;
        }
        else if (givenObject instanceof String) {
            this.given = new ArrayList<>();
            this.given.add((String) givenObject);
        }
        else {
            throw new RuntimeException("Invalid rule given or missing");
        }
    }

}
