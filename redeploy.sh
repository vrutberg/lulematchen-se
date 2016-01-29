#!/bin/sh

/opt/jetty/bin/jetty.sh stop
rm /opt/jetty/webapps/ROOT.war
cd /opt/lulematchen-se
git reset --hard HEAD
git pull
mvn clean install
cp -v src/main/resources/log4j-prod.properties /opt/jetty/resources/log4j.properties
cp -v target/lulematchen.war /opt/jetty/webapps/ROOT.war
/opt/jetty/bin/jetty.sh start
