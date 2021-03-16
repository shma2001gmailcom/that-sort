#!/bin/sh
javahome='/home/mshevelin/jdk/jdk1.8.0_211'
M2_HOME='/usr/share/maven'
export M2_HOME
M2=${M2_HOME}/bin
export M2
mvn=${M2}/mvn
export JAVA_HOME=${javahome}
cd ../
rm -r ./target
${mvn} clean install
cd target
java -jar that-sort.jar
