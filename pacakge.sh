#!/usr/bin/env bash
JARNAME="HooverDriver.jar"
(cd src && rm `find . -name "*.class"`);
javac `find ./src -name "*.java"`;
(cd src && jar cmvf META-INF/MANIFEST.MF $JARNAME `find . -name "*.class"`);
mv src/$JARNAME .;
