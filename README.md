Server Corrispettivi per Test HW RT
==================




Information   | Value
------------- | --------
Component     | Server Corrispettivi per Test HW RT
Istitution    | ISTI-CNR
Responsible   | Giorgio O. Spagnolo <spagnolo at isti.cnr.it>
Status project| [![Build Status](https://travis-ci.org/imatesiu/TestHWCorrispettivi.svg?branch=corrispettiviV7)](https://github.com/imatesiu/TestHWCorrispettivi/) [![Coverage Status](https://coveralls.io/repos/github/imatesiu/TestHWCorrispettivi/badge.svg?branch=corrispettiviV7)](https://coveralls.io/github/imatesiu/TestHWCorrispettivi?branch=corrispettiviV7)



# Summary


# How it works?

This component expose REST API

The component is ready to be packaged as a WAR to be deployed on an Application Server like jetty.

The service is available at `https://localhost/v1/dispositivi/corrispettivi`.

# How to install?

  * Windows/Linux
    * Install Java Sdk 1.8 
    * Install Maven 
    * Set the path and environment variables 
  
  * Download repository
  * Run ./run.sh or run.bat

# CURL Test
 * To send DatiCorrispettiviType XML to component, the reply is a EsitoOperazioneType:  
`curl -X POST -H "Content-Type: application/XML" --data @DatiCorrispettiviType.xml https://localhost/v1/dispositivi/corrispettivi/`

* To send Init of :  
`curl -X GET https://localhost/v1/dispositivi/corrispettivi/init/{matricola or ip}?grantot={grantotale}`

* To get Status of :  
`curl -X GET https://localhost/v1/dispositivi/corrispettivi/info/{matricola or ip}`

* To send Clear:  
`curl -X GET https://localhost/v1/dispositivi/corrispettivi/clear/{matricola or ip}`

* To send Clear all:  
`curl -X GET https://localhost/v1/dispositivi/corrispettivi/clearall`

* To set Error Channel 
`curl -X GET https://localhost/v1/dispositivi/corrispettivi/set/{key}` sostituire `{key}`con:
  * `SuperoLimite` per avere (429),
  *  `NonAutorizzato` per avere  (403),
  *  `InputNonValido` (406),
  *  `DispositivoNNValido` (409),
  *  `ErrorContentType` per avere (415),
  *  `fuoriorario` per avere (500),
  *  `Default` per avere (500),
  *  `Null` per ripristinare;

* To set Error XML
`curl -X GET https://localhost/v1/dispositivi/corrispettivi/setxml/{key}` sostituire `{key}`con i codici errore xml implementati che sono: `100, 101, 110, 111, 112, 200, 201, 202, 203, 204, 206, 208, 209, 212, 700, 701, 705, 706, 711`


# Note
Server Certificate Authority in `/script/serverca.crt` [link](https://raw.githubusercontent.com/imatesiu/TestHWCorrispettivi/2.4.2_C10/script/serverca.crt)
