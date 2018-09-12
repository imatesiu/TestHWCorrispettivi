#!/usr/bin/env bash
mvn clean
mvn install
mvn jetty:run -Dorg.eclipse.jetty.util.log.class=org.eclipse.jetty.util.log.StdErrLog  -Dorg.eclipse.jetty.LEVEL=DEBUG

# tomcat7:run
