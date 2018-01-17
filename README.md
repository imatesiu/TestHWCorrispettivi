Server Corrispettivi per Test HW RT
==================




Information   | Value
------------- | --------
Component     | Server Corrispettivi per Test HW RT
Istitution    | ISTI-CNR
Responsible   | Giorgio O. Spagnolo <spagnolo at isti.cnr.it>
Status project| [![Build Status](https://travis-ci.org/imatesiu/TestHWCorrispettivi.svg?branch=master)](https://github.com/imatesiu/TestHWCorrispettivi/)




# Summary


# How it works?

This component expose REST API

The component is ready to be packaged as a WAR to be deployed on an Application Server like jetty.

The service is available at `localhost:9090/dispositivi/corrispettivi`.



# CURL Test
 * To send DatiCorrispettiviType XML to component, the reply is a EsitoOperazioneType:  
`curl -X POST -H "Content-Type: application/XML" --data @DatiCorrispettiviType.xml http://localhost:9090/dispositivi/corrispettivi/`

* To send Init of :  
`curl -X GET http://localhost:9090/dispositivi/corrispettivi/init/{matricola or ip}?grantot={grantotale}`

* To get Status of :  
`curl -X GET http://localhost:9090/dispositivi/corrispettivi/info/{matricola or ip}`

* To send Clear:  
`curl -X GET http://localhost:9090/dispositivi/corrispettivi/clear/{matricola or ip}`

* To send Clear all:  
`curl -X GET http://localhost:9090/dispositivi/corrispettivi/clearall`


# Note
