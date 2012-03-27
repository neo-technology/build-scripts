package org.neo4j.sharding.build.ant.license;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.tools.ant.BuildException;

public class SingleFileLicenseHeaderChecker
{

    private File file;
    private License license;
    private boolean checkerPassed = false;

    public SingleFileLicenseHeaderChecker(File file, License license)
    {
        this.file = file;
        this.license = license;
    }

    public boolean passed()
    {
        return checkerPassed;
    }

    public boolean failed()
    {
        return !passed();
    }

    public SingleFileLicenseHeaderChecker check()
    {
        NeoTechLicenseHeaderRegex regex = new NeoTechLicenseHeaderRegex(license);

        try
        {
            FileReader reader = new FileReader(file);
            char[] licenseChars = new char[enoughCharsForLicenseAndCommentMarkers()];
            reader.read(licenseChars);
            reader.close();

            String s = String.valueOf(licenseChars);
            
            Pattern pattern = regex.getRegex();

            checkerPassed = pattern.matcher(s).matches();
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new BuildException(e);
        }
        catch(PatternSyntaxException e) {
            // Do nothing, the regex failed to match
            throw new BuildException(e);
        }

        return this;
    }

    private int enoughCharsForLicenseAndCommentMarkers()
    {
        return license.length() * 2;
    }
}
