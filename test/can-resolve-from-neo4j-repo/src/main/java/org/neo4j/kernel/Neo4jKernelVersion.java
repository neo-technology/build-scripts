package org.neo4j.kernel;

public class Neo4jKernelVersion {
    public static String getVersionString() {
	return org.neo4j.kernel.Version.getKernel().getVersion();
    }
}