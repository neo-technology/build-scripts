package org.neo4j.sharding.build.ant.license;

import static org.junit.Assert.assertTrue;

import java.util.regex.Pattern;

import org.junit.Test;

public class NeoTechLicenseHeaderRegexTest
{
    private final License license = new License();

    public NeoTechLicenseHeaderRegexTest()
    {
        Line l1 = new Line();
        l1.addText("Copyright (C) 2012 Neo Technology");

        Line l2 = new Line();
        l2.addText("All rights reserved");

        license.addLine(l1);
        license.addLine(l2);
    }

    @Test
    public void shouldMatchJavaHeader()
    {
        String message = "/**\n" +
                " * Copyright (C) 2012 Neo Technology, http://neotechnology.com\n" +
                " * All rights reserved\n" +
                " */";

        Pattern pattern = Pattern.compile(new NeoTechLicenseHeaderRegex(license).getRegex().toString());
        pattern.matcher(message).matches();

        assertTrue(pattern.matcher(message).matches());
    }

    @Test
    public void shouldMatchPropertyFileHeader()
    {
        String message = "# Copyright (C) 2012 Neo Technology, http://neotechnology.com\n" +
                "# All rights reserved";

        Pattern pattern = Pattern.compile(new NeoTechLicenseHeaderRegex(license).getRegex().toString());
        pattern.matcher(message).matches();

        assertTrue(pattern.matcher(message).matches());
    }
}
