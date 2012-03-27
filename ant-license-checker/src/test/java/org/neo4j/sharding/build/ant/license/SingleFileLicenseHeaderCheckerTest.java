package org.neo4j.sharding.build.ant.license;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;
import org.neo4j.test.TargetDirectory;

public class SingleFileLicenseHeaderCheckerTest
{
    private static final String NEWLINE = System.getProperty("line.separator");

    private License license = new License();

    public SingleFileLicenseHeaderCheckerTest()
    {
        Line l1 = new Line();
        l1.addText("Copyright (C) 2012 Neo Technology");

        Line l2 = new Line();
        l2.addText("All rights reserved");

        license.addLine(l1);
        license.addLine(l2);
    }


    @Test
    public void shouldDetectMissingLicenseHeaders() throws Exception
    {
        SingleFileLicenseHeaderChecker checker = new SingleFileLicenseHeaderChecker(javaFileWithMissingHeader(),license);
        checker.check();
        assertTrue(checker.failed());
    }

    @Test
    public void shouldPassConformantLicenseHeaders() throws Exception
    {
        File file = javaFileWithGoodHeader();
        SingleFileLicenseHeaderChecker checker = new SingleFileLicenseHeaderChecker(file, license);
        checker.check();
        assertTrue(checker.passed());
    }

    @Test
    public void shouldFailOnOutdatedLicenseHeaders() throws Exception
    {
        SingleFileLicenseHeaderChecker checker = new SingleFileLicenseHeaderChecker(javaFileWithOutOfDateHeader(),
                                                                                    license);
        checker.check();
        assertTrue(checker.failed());
    }

    private File javaFileWithOutOfDateHeader() throws IOException
    {
        File file = TargetDirectory.forTest(SingleFileLicenseHeaderCheckerTest.class).file("WithHeaders.java");

        FileWriter writer = new FileWriter(file);
        writer.write(toJavaHeader(license.toString().replace("2012",
                                                             "2011")) + "\n\npackage org.neo4j.headers;\n\npublic class WithHeaders {}");
        writer.close();

        return file;
    }

    private String toJavaHeader(String licenseText)
    {

        String[] lines = licenseText.split(NEWLINE);

        StringBuffer javaHeader = new StringBuffer();

        javaHeader.append("/**");
        javaHeader.append(NEWLINE);

        for (String line : lines)
        {
            javaHeader.append(" * ");
            javaHeader.append(line);
            javaHeader.append(NEWLINE);
        }

        javaHeader.append(" */");
        javaHeader.append(NEWLINE);
        javaHeader.append(NEWLINE);

        return javaHeader.toString();
    }

    private File javaFileWithGoodHeader() throws IOException
    {
        File file = TargetDirectory.forTest(this.getClass()).file("WithHeaders.java");

        FileWriter writer = new FileWriter(file);
        writer.write(toJavaHeader(license.toString()) + "\n\npackage org.neo4j.headers;\n\npublic class WithHeaders {}");
        writer.close();

        return file;
    }

    private File javaFileWithMissingHeader() throws IOException
    {
        File file = TargetDirectory.forTest(this.getClass()).file("HeaderlessFile.java");

        FileWriter writer = new FileWriter(file);
        writer.write("\n\npackage org.neo4j.headerless;\n\npublic class HeaderLess {}");
        writer.close();

        return file;
    }

}
              