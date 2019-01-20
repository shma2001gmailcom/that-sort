#!/bin/sh
javahome='/opt/java8-oracle/jdk1.8.0_181'
M2_HOME='/usr/bin/mvn'
export M2_HOME
M2=${M2_HOME}\\bin
export M2
mvn=${M2}\\mvn.cmd
export JAVA_HOME=${javahome}
cd ../
${mvn} clean install
cd target
java -jar that-sort.jar
