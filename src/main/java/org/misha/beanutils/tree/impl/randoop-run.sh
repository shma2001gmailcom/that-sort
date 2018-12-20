#!/usr/bin/env bash
java_home="C:\\Program Files\\Java\\jdk1.8.0_171"
export JAVA_HOME=${java_home}
RANDOOP_JAR='C:\\Users\\mikhail_shevelin\\workspace\\that-sort\\lib\\randoop-all-4.1.0.jar'
DIST='C:\\Users\\mikhail_shevelin\\workspace\\that-sort\\target\\that-sort.jar'

java -ea -classpath "${RANDOOP_JAR};${DIST}"  randoop.main.Main gentests --flaky-test-behavior=DISCARD --classlist=myclasses.txt --time-limit=160