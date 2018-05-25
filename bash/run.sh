#!/bin/sh

M2_HOME='/usr/share/maven'
export M2_HOME
mvn=${M2_HOME}/bin/mvn
cd ../
${mvn} clean install
rm -r dist
mkdir dist
cd target
cp that-sort.jar ../dist
cp -r lib ../dist
cp -r config ../dist
cd ../dist
java -jar that-sort.jar
