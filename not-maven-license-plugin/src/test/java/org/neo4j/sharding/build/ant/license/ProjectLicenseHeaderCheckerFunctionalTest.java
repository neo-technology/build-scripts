package org.neo4j.sharding.build.ant.license;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.Assert.fail;

public class ProjectLicenseHeaderCheckerFunctionalTest
{
    @Test
    public void shouldFailOnEncounterWithJavaFileWithoutLicense() throws Exception
    {
        File targetDir = new File("target");
        File generatedSourcesDir = new File(targetDir.getAbsolutePath() + File.separator + "generated-sources");
        generatedSourcesDir.mkdirs();

        createDummyJavaProject(generatedSourcesDir, "org.neo4j.sharding");

        File buildFile = createBuildFile(targetDir, generatedSourcesDir);

        Project project = new Project();
        project.setUserProperty("ant.file", buildFile.getAbsolutePath());
        project.init();

        ProjectHelper helper = ProjectHelper.getProjectHelper();
        project.addReference("ant.projectHelper", helper);
        helper.parse(project, buildFile);

        try
        {
            project.executeTarget(project.getDefaultTarget());
            fail("Should have caused a build exception on finding a Java file without a header");
        }
        catch (BuildException e)
        {

        }
    }

    private File createBuildFile(File targetDir, File generatedSourcesDir) throws IOException
    {

        System.out.println(generatedSourcesDir.getAbsolutePath());

        File buildFile = new File(targetDir.getAbsolutePath() + File.separator + "build.xml");

        FileWriter writer = new FileWriter(buildFile);
        writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                             "<project name=\"not-maven-license-plugin-functional-tests\" default=\"run\">\n" +
                             "  <property name=\"target.dir\" location=\"" + targetDir.getAbsolutePath() + "\"/>\n" +
                             "  <property name=\"generated.sources.dir\" location=\"" + generatedSourcesDir.getAbsolutePath() + "\"/>\n" +
                             "  <typedef name=\"line\" classname=\"org.neo4j.sharding.build.ant.license.Line\"/>\n" +
                             "  <typedef name=\"license\" classname=\"org.neo4j.sharding.build.ant.license.License\"/>\n" +
                             "  <taskdef name=\"check-license\" classname=\"org.neo4j.sharding.build.ant.license.ProjectLicenseHeaderChecker\" >\n" +
                             "    <classpath>\n" +
                             "      <fileset dir=\"${target.dir}/classes\">\n" +
                             "        <include name=\"**/*.class\"/>\n" +
                             "      </fileset>\n" +
                             "    </classpath>\n" +
                             "  </taskdef>\n" +
                             "  <target name=\"run\">\n" +
                             "    <check-license>\n" +
                             "      <license>\n" +
                             "        <line>Copyright (C) 2012 Neo Technology</line>\n" +
                             "        <line>All rights reserved</line>\n" +
                             "      </license>\n" +
                             "      <fileset dir=\"${generated.sources.dir}\" includes=\"**/*.java\"/>\n" +
                             "    </check-license>\n" +
                             "  </target>\n" +
                             "</project>"
        );
        writer.close();

        return buildFile;
    }

    private void createDummyJavaProject(File outDir, String packageName) throws IOException
    {
        System.out.println(outDir.getAbsolutePath());
        for (int i = 0; i < 3; i++)
        {
            createHeaderlessJavaFile(new File(outDir.getPath() + File.separator + toDirectory(packageName)),
                                     packageName);
        }
    }


    private void createHeaderlessJavaFile(File outDir, String packageName) throws IOException
    {
        outDir.mkdirs();

        String randomClassname = myRandomClassname();

        File javaFile = new File(outDir.getAbsolutePath() + File.separator + randomClassname + ".java");


        System.out.println(javaFile.getAbsolutePath());

        FileWriter writer = new FileWriter(javaFile);
        writer.write("package " + packageName + ";\n\npublic class " + randomClassname + " {}");
        writer.close();

    }

    private File toDirectory(String packageName)
    {
        return new File(packageName.replace(".", File.separator));
    }

    private String myRandomClassname()
    {
        String s = RandomStringUtils.randomAlphabetic(6).toLowerCase();
        return "My" + s;
    }
}
