package org.wso2.spectral.functions.core;

import com.google.gson.Gson;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.wso2.spectral.document.LintTarget;
import org.wso2.spectral.functions.LintFunction;

import java.util.Map;

public class Schema extends LintFunction {

    public Schema(Map<String, Object> options) {
        super(options);
    }

    public boolean execute(LintTarget target) {

        String targetString = new Gson().toJson(target.value);
        JSONObject targetObject = new JSONObject(targetString);

        String schema = new Gson().toJson(options.get("schema"));
        JSONObject schemaObject = new JSONObject(schema);
        org.everit.json.schema.Schema everitSchema = SchemaLoader.load(schemaObject);

        try{
            everitSchema.validate(targetObject);
        } catch (org.everit.json.schema.ValidationException e) {
            return false;
        }
        return true;
    }

}
