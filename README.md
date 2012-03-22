Build scripts used by Neo Technology.

These scripts require Ant 1.8.

Default usage:

    <project name="..." default="complete">
      <property name="commons.xml" location="lib/build/commons.xml"/>
      <available file="${commons.xml}" property="commons.url"
                 value="file:${commons.xml}"/>
      <property name="commons.url" value="https://raw.github.com/neo-technology/build-scripts/master/commons.xml"/>
      <import><url url="${commons.url}"/></import>

      <!-- any <using lib="..."/> goes here -->

    </project>