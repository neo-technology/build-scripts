package org.neo4j.sharding.build.ant.license;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;

public class ProjectLicenseHeaderChecker extends Task
{
    private ArrayList<FileSet> fileSets = new ArrayList<FileSet>();
    private License license = new License();

    public void addLicense(License license)
    {
        this.license = license;
    }

    public void addFileSet(FileSet fileSet)
    {
        this.fileSets.add(fileSet);
    }

    public void execute() throws BuildException
    {
        HashSet<String> includedFiles = new HashSet<String>();
        for (FileSet fs : fileSets)
        {
            DirectoryScanner ds = fs.getDirectoryScanner(getProject());
            for (String file : ds.getIncludedFiles())
            {
                includedFiles.add(ds.getBasedir().getAbsolutePath() + File.separator + file);
            }
        }

        checkIfLicenseHeaderIsInEachFile(includedFiles);
    }

    private void checkIfLicenseHeaderIsInEachFile(HashSet<String> includedFiles)
    {
        for (String includedFile : includedFiles)

        {
            if (new SingleFileLicenseHeaderChecker(new File(includedFile), license).check().failed())
            {
                throw new BuildException(String.format(
                        "File [%s] does not contain licensing information required licensing information" + System.getProperty(
                                "line.separator") + "%s",
                        includedFile,
                        license.toString()));
            }
        }
    }
}