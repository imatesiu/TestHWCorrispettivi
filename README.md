Server Corrispettivi per Test HW RT
==================




Information   | Value
------------- | --------
Component     | Server Corrispettivi per Test HW RT
Istitution    | ISTI-CNR
Responsible   | Giorgio O. Spagnolo <spagnolo at isti.cnr.it>
Status project| [![Build Status](https://travis-ci.org/imatesiu/TestHWCorrispettivi.svg?branch=master)](https://github.com/imatesiu/TestHWCorrispettivi/)




# Summary

# How to install, build and run?
To install:
* For Ubuntu:
  * Install maven `sudo apt-get install maven`
  * Install Java 8 `sudo add-apt-repository ppa:webupd8team/java`
`sudo apt-get update`
`sudo apt-get install oracle-java8-installer`

* For Windows:
  * Install [Maven](https://maven.apache.org/download.cgi) ([How to](https://maven.apache.org/install.html))
  * Install [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

To build and run into TestHWCorrispettivi directory:

* `mvn install`
* `mvn jetty:run`

# How it works?

This component expose REST API

The component is ready to be packaged as a WAR to be deployed on an Application Server like jetty.

The service is available at `localhost:9090/dispositivi/corrispettivi`.



# CURL Test
 * To send DatiCorrispettiviType XML to component, the reply is a EsitoOperazioneType:  
`curl -X POST -H "Content-Type: application/XML" --data @DatiCorrispettiviType.xml http://localhost:9090/dispositivi/corrispettivi/`

* To send Init of :  
`curl -X GET http://localhost:9090/dispositivi/corrispettivi/init/{ip}?grantot={grantotale}`

* To get Status of :  
`curl -X GET http://localhost:9090/dispositivi/corrispettivi/info/{ip}`

* To send Clear:  
`curl -X GET http://localhost:9090/dispositivi/corrispettivi/clear/{ip}`

* To send Clear all:  
`curl -X GET http://localhost:9090/dispositivi/corrispettivi/clearall`


# Note
