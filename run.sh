#!/usr/bin/env bash
mvn keytool:clean keytool:generateKeyPair keytool:exportCertificate
mvn jetty:run 

# tomcat7:run
