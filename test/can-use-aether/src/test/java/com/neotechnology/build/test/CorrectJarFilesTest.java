/*
 * Copyright (C) 2013 Neo Technology
 * All rights reserved
 */

package com.neotechnology.build.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class CorrectJarFilesTest
{
    @Test
    public void mainClassesAreInTheCorrectJarFile()
    {
        assertEquals( "using-aether-1.0-SNAPSHOT.jar",
                JarFile.name( JarFile.class ) );
    }

    @Test
    public void testClassesAreInTheCorrectJarFile()
    {
        assertEquals( "using-aether-1.0-SNAPSHOT-test.jar",
                JarFile.name( CorrectJarFilesTest.class ) );
    }
}