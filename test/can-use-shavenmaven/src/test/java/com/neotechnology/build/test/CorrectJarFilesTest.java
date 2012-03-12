package com.neotechnology.build.test;

import org.junit.Test;
import static org.junit.Assert.*;

public class CorrectJarFilesTest {
    @Test public void mainClassesAreInTheCorrectJarFile() {
	assertEquals( "can-use-shavenmaven-SNAPSHOT.jar",
		      JarFile.name( JarFile.class ) );
    }

    @Test public void testClassesAreInTheCorrectJarFile() {
	assertEquals( "can-use-shavenmaven-SNAPSHOT-test.jar",
		      JarFile.name( CorrectJarFilesTest.class ) );
    }
}