#!/usr/bin/env bash
java_home="C:\Program Files\Java\jdk1.8.0_171"
M2_HOME='C:\maven-3.5.4'
export M2_HOME
M2=${M2_HOME}/bin
export M2
mvn=${M2}/mvn.cmd
export JAVA_HOME=${java_home}
lib='../lib'
${mvn} install:install-file -Dfile=${lib}/randoop-all-4.1.0.jar -DpomFile=${lib}/randoop-all-4.0.3.pom
#${mvn} install:install-file -Dfile=${lib}/covered-class-4.1.0.jar
#{mvn} install:install-file -Dfile=${lib}/replacecall-4.1.0.jar
