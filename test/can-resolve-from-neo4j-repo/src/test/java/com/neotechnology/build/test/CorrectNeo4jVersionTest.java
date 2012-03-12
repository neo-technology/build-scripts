package com.neotechnology.build.test;

import org.neo4j.kernel.Neo4jKernelVersion;
import org.junit.*;
import static org.junit.Assert.*;

public class CorrectNeo4jVersionTest {
    public @Test void hasSnapshotVersionOfNeo4j() {
	assertTrue(Neo4jKernelVersion.getVersionString().startsWith("1.7-SNAPSHOT"));
    }
}
