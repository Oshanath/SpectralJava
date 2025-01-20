package org.wso2.spectral;

import org.wso2.spectral.document.Document;
import org.wso2.spectral.functions.FunctionResult;
import org.wso2.spectral.ruleset.Ruleset;
import org.wso2.spectral.ruleset.file.type.JsonRuleset;
import org.wso2.spectral.ruleset.file.type.YamlRuleset;
import org.wso2.spectral.ruleset.validator.RulesetValidationResult;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

/**
 * Validator class to validate documents and rulesets.
 */
public class Validator {
    public static List<FunctionResult> validateDocument(File documentFile, File rulesetFile) throws
            IOException {

        String extension = rulesetFile.getName().substring(rulesetFile.getName().lastIndexOf(".") + 1);
        InputStream rulesetStream = Files.newInputStream(rulesetFile.toPath());

        Ruleset ruleset;
        if (extension.equals(Constants.YAML_EXTENSION)) {
            ruleset = new YamlRuleset(rulesetStream);
        } else {
            ruleset = new JsonRuleset(rulesetStream);
        }

        InputStream documentStream = Files.newInputStream(documentFile.toPath());
        Document document = new Document(documentStream);
        return document.lint(ruleset);
    }

    public static List<RulesetValidationResult> validateRuleset(File rulesetFile) throws IOException {
        InputStream rulesetStream = Files.newInputStream(rulesetFile.toPath());
        Ruleset ruleset;
        String extension = rulesetFile.getName().substring(rulesetFile.getName().lastIndexOf(".") + 1);
        if (extension.equals(Constants.YAML_EXTENSION)) {
            ruleset = new YamlRuleset(rulesetStream);
        } else {
            ruleset = new JsonRuleset(rulesetStream);
        }
        return ruleset.validate();
    }
}
