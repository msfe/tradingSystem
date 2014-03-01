#!/bin/csh
set tomcatpath=$HOME/tomcat/lib
set webapppath=$HOME/tomcat/webapps/ROOT/WEB-INF/classes
javac -cp $tomcatpath/servlet-api.jar $webapppath/*.java $webapppath/bean/*.java
