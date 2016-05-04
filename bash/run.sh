#!/bin/sh
cd ../
mvn clean install
cd target
java -jar that-sort.jar
