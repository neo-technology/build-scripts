package org.neo4j.sharding.build.ant.license;

import java.util.ArrayList;

public class License
{
    private ArrayList<Line> lines = new ArrayList<Line>();

    public License(){}

    public void addLine(Line line)
    {
        lines.add(line);
    }

    public ArrayList<Line> getLines()
    {
        return lines;
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();

        for (Line line : lines)
        {
            sb.append(line.toString());
            sb.append(System.getProperty("line.separator"));
        }

        return sb.toString();
    }

    public int length()
    {
        int length = 0;

        for (Line l : lines)
        {
            length += l.toString().length();
        }

        return length;
    }
}
