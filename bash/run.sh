#!/bin/sh
java_home="C:\Program Files\Java\jdk1.8.0_171"
M2_HOME='C:\maven-3.5.4'
export M2_HOME
M2=${M2_HOME}/bin
export M2
mvn=${M2}/mvn.cmd
export JAVA_HOME=${java_home}
cd ../
${mvn} clean install -X
cd target
java -jar that-sort.jar
