import requests
import sys
import os
import logging

logging.basicConfig(level=logging.DEBUG)


def send_post(content):
	session = requests.Session()
	#response = requests.post('http://localhost:9090/dispositivi/corrispettivi/',data=content,headers={"Content-Type": "application/xml"}, verify=False)
	pem = 'serverca.crt'
	#response = requests.post('https://v-apid-ivaservizi.agenziaentrate.gov.it/v1/dispositivi/corrispettivi/',data=content,headers={"Content-Type": "application/xml"}, verify=False)
	response = session.post('https://winspa.isti.cnr.it/dispositivi/corrispettivi/',data=content,headers={"Content-Type": "application/xml","Connection": "Keep-Alive"}, verify=False)
	print response.headers
	print response.text
	assert response.status_code == 200

content = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?> <ns2:DatiCorrispettivi xmlns:ns2=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0\">    <Trasmissione>        <Progressivo>56</Progressivo>        <Formato>COR10</Formato>    </Trasmissione>    <DataOraRilevazione>2017-03-17T15:38:16</DataOraRilevazione>    <DatiRT>        <Riepilogo>            <IVA>                <AliquotaIVA>10.00</AliquotaIVA>                <Imposta>1.00</Imposta>            </IVA>            <Ammontare>10.00</Ammontare>        </Riepilogo>    </DatiRT></ns2:DatiCorrispettivi>"

c1 = open(sys.argv[1],'r')
r = c1.read()
send_post(r)