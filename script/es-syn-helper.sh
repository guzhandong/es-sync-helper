#!/usr/bin/env bash

#change jdk path
#export JAVA_HOME=
#export PATH=$PATH:$JAVA_HOME/bin

base_home= cd `dirname $0`; pwd

java -jar $base_home/es-syn-com.guzhandong.helper.jar $@
es-syn-com.guzhandong.helper.sh  index   alias