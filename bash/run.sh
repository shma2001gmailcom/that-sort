#!/bin/bash

java_home='/usr/lib/jvm/java-8-openjdk-amd64'
M2_HOME='/usr/share/maven'
export M2_HOME
M2=${M2_HOME}/bin
export M2
mvn=${M2}/mvn
export JAVA_HOME=${java-home}
cd ../
if [[ -d "./target" ]]; then
    rm -r ./target;
fi

${mvn} clean install
cd target
java -jar that-sort.jar
