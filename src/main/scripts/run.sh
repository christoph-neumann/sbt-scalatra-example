#!/bin/bash

# Find the application home directory and switch to it
app_home=$(cd `dirname "$0"`; pwd)
cd "$app_home"

# Options to pass to the JVM
java_opts="-server -Xmx256m -XX:MaxPermSize=128m"

# Find all the jars and put them on the classpath
for jar in `ls ${app_home}/lib/*.jar`; do
	classpath="$classpath:$jar"
done

java $java_opts -cp "$classpath" app.Main $*
