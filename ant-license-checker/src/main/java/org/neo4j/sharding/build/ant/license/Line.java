package org.neo4j.sharding.build.ant.license;

public class Line
{
    private String line;

    public Line()
    {

    }

    public void addText(String line)
    {
        this.line = line;
    }
    
    public String toString() {
        return line;
    }
}
