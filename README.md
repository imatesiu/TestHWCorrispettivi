Server Corrispettivi per Test HW RT
==================




Information   | Value
------------- | --------
Component     | Server Corrispettivi per Test HW RT
Istitution    | ISTI-CNR
Responsible   | Giorgio O. Spagnolo <spagnolo at isti.cnr.it>
Status project| [![Build Status](https://travis-ci.org/imatesiu/TestHWCorrispettivi.svg?branch=v2.4.1)](https://github.com/imatesiu/TestHWCorrispettivi/) [![Coverage Status](https://coveralls.io/repos/github/imatesiu/TestHWCorrispettivi/badge.svg)](https://coveralls.io/github/imatesiu/TestHWCorrispettivi)




# Summary


# How it works?

This component expose REST API

The component is ready to be packaged as a WAR to be deployed on an Application Server like jetty.

The service is available at `https://localhost/dispositivi/corrispettivi`.

# How to install?

  * Windows/Linux
    * Install Java Sdk 1.8 
    * Install Maven 
    * Set the path and environment variables 
  
  * Download repository
  * Run ./run.sh or run.bat

# CURL Test
 * To send DatiCorrispettiviType XML to component, the reply is a EsitoOperazioneType:  
`curl -X POST -H "Content-Type: application/XML" --data @DatiCorrispettiviType.xml https://localhost/dispositivi/corrispettivi/`

* To send Init of :  
`curl -X GET https://localhost/dispositivi/corrispettivi/init/{matricola or ip}?grantot={grantotale}`

* To get Status of :  
`curl -X GET https://localhost/dispositivi/corrispettivi/info/{matricola or ip}`

* To send Clear:  
`curl -X GET https://localhost/dispositivi/corrispettivi/clear/{matricola or ip}`

* To send Clear all:  
`curl -X GET https://localhost/dispositivi/corrispettivi/clearall`


# Note
Server Certificate Authority in `/script/serverca.crt` [link](https://raw.githubusercontent.com/imatesiu/TestHWCorrispettivi/2.4.2_C10/script/serverca.crt)
