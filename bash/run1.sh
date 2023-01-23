#!/bin/sh

cmd_line="java -jar that-sort.jar"
cd ../target
${cmd_line} </dev/null > .1 2>&1