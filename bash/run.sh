#!/bin/sh
javahome="C:/work/Java/jdk1.6.0_45"
M2_HOME='C:/work/maven/'
export M2_HOME
M2=${M2_HOME}/bin
export M2
mvn=${M2_HOME}'bin/mvn.bat'
export JAVA_HOME=${javahome}
cd ../
${mvn} clean install -X
cd target
java -jar that-sort.jar
