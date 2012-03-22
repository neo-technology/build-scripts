package org.neo4j.sharding.build.ant.license;

import java.util.regex.Pattern;

public class NeoTechLicenseHeaderRegex
{
    private StringBuffer licenseText = new StringBuffer();

    public NeoTechLicenseHeaderRegex(License license)
    {
        licenseText.append("^.*(\\n?).+");
        for(Line line : license.getLines()) {
            appendLine(line.toString());
        }
    }

    public NeoTechLicenseHeaderRegex appendLine(String line)
    {
        line = line.replace("(C)", "\\(C\\)").trim();
        licenseText.append(line);
        licenseText.append(".*(\\n*).*"); // start of next line postamble
        return this;
    }

    public Pattern getRegex()
    {
        return Pattern.compile(licenseText.toString(), Pattern.DOTALL);
    }
}
