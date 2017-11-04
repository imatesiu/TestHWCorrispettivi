#!/usr/bin/env bash
mvn keytool:clean keytool:generateKeyPair
mvn jetty:run 

# tomcat7:run
