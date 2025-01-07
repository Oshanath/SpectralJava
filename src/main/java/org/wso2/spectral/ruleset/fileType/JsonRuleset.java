package org.wso2.spectral.ruleset.fileType;

import com.jayway.jsonpath.JsonPath;
import org.wso2.spectral.SpectralException;
import org.wso2.spectral.ruleset.Ruleset;

import java.io.InputStream;
import java.util.Map;

public class JsonRuleset extends Ruleset {
    public JsonRuleset(InputStream rulesetStream) throws SpectralException {
        super((Map<String, Object>) JsonPath.parse(rulesetStream).json());
    }
}
