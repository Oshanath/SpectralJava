package org.wso2.spectral.ruleset.fileType;

import org.snakeyaml.engine.v2.api.Load;
import org.snakeyaml.engine.v2.api.LoadSettings;
import org.wso2.spectral.SpectralException;
import org.wso2.spectral.ruleset.Ruleset;

import java.io.InputStream;
import java.util.Map;

public class YamlRuleset extends Ruleset{
    public YamlRuleset(InputStream rulesetStream) throws SpectralException {
        super((Map<String, Object>) (new Load(LoadSettings.builder().build())).loadFromInputStream(rulesetStream));
    }
}
