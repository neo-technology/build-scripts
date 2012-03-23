# License Checker task

This is a simple implementation of an Ant task that checks for a string in a given set of files. We use it
to ensure our licensing is prominent in all our source.

# Usage:

<target name="check.licenses">
  <typedef name="line" classname="org.neo4j.sharding.build.ant.license.Line"/>
  <typedef name="license" classname="org.neo4j.sharding.build.ant.license.License"/>
  <taskdef name="check-license" classname="org.neo4j.sharding.build.ant.license.ProjectLicenseHeaderChecker" >
    <classpath>
      <fileset dir="lib">
        <include name="license-checker.jar"/>
      </fileset>
    </classpath>
  </taskdef>
  <check-license>
    <license>
      <line>Copyright (C) 2012 Neo Technology</line>
      <line>All rights reserved</line>
    </license>
    <fileset dir="src/main/java" includes="**/*.java"/>
    <fileset dir="src/main/resource" includes="**/*.properties"/>
  </check-license>
</target>

