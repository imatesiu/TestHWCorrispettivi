#!/usr/bin/env bash
mvn clean
mvn install
mvn jetty:run -Dorg.eclipse.jetty.util.log.class=org.eclipse.jetty.util.log.StdErrLog  -Dorg.eclipse.jetty.LEVEL=DEBUG -Djdk.tls.ephemeralDHKeySize=2048 -Djdk.tls.server.defaultDHEParameters=2048

# tomcat7:run
