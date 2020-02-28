#!/usr/bin/env bash
#mvn keytool:clean keytool:generateKeyPair keytool:exportCertificate
mvn clean jetty:run -Djdk.tls.ephemeralDHKeySize=2048 -Djdk.tls.server.defaultDHEParameters=2048


# tomcat7:run
