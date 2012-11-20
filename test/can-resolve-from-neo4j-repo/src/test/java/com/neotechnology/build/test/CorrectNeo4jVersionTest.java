package com.neotechnology.build.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.neo4j.kernel.Neo4jKernelVersion;

public class CorrectNeo4jVersionTest
{
    @Test
    public void hasSnapshotVersionOfNeo4j()
    {
        assertTrue( Neo4jKernelVersion.getVersionString().startsWith( "1.8" ) );
    }
}
