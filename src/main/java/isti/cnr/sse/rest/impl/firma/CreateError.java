package isti.cnr.sse.rest.impl.firma;

import static org.junit.Assert.assertNotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import cnr.isti.sse.data.corrispettivi.DatiCorrispettiviType;
import cnr.isti.sse.data.corrispettivi.doccommercialilotteria.DocCommercialiLotteriaType;
import cnr.isti.sse.data.corrispettivi.doccommercialilotteria.messaggi.DocCommercialiLotteriaEsitoType;
import cnr.isti.sse.data.corrispettivi.doccommercialilotteria.messaggi.ErroreType;
import cnr.isti.sse.data.corrispettivi.doccommercialilotteria.messaggi.EsitoType;
import cnr.isti.sse.data.corrispettivi.doccommercialilotteria.messaggi.SegnalazioniDocCommType;
import cnr.isti.sse.data.corrispettivi.messaggi.AttivaDispositivoType;
import cnr.isti.sse.data.corrispettivi.messaggi.ErroriType;
import cnr.isti.sse.data.corrispettivi.messaggi.EsitoOperazioneType;
import cnr.isti.sse.data.corrispettivi.messaggi.EsitoRichiestaCertificatoDispositivoType;
import cnr.isti.sse.data.corrispettivi.messaggi.EventoDispositivoType;
import cnr.isti.sse.data.corrispettivi.messaggi.RichiestaCertificatoDispositivoType;

public class CreateError {
	
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(CreateError.class);
	
	private String URL = "https://v-apid-ivaservizi.agenziaentrate.gov.it";//"https://v-apid-ivaservizi.agenziaentrate.gov.it";//sseapid.isti.cnr.it";//
	private String basepath = "/v1/";
	
	
	public void Censimento() {
		
String url = "dispositivi/";

		String CSR = "MIICpjCCAY4CAQAwYTELMAkGA1UEBhMCSVQxFDASBgNVBAMMCzcyTUFBMDAwMDAx\n" + 
				"MRAwDgYDVQQHDAdWZW5lemlhMQwwCgYDVQQKDANSQ0gxDjAMBgNVBAgMBUl0YWx5\n" + 
				"MQwwCgYDVQQLDANSQ0gwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC+\n" + 
				"dQQzTKPsX7tcMFc6WhUyQhwHjIO9lkOa25ugYe0nVZxX8ZMVrozIAvspW9g4Q693\n" + 
				"7Cb7T5Tf2y1ySY37FBdyg76R3zVNEB9T8TrPgln58p2+u5WxlfU51jbp1k2NbWns\n" + 
				"GX+W+T4bDU7J/kYuPBOJCxMgzBSMCOIYD65SlxQCIrlCUpXoM0orZjK9ApyUiVE+\n" + 
				"vHzCM69BSBUP2SZerx+L5jZbTZI7QXeG6zZNczuec2V6xlhFLHDXwOViYI3GAe1d\n" + 
				"Prha4cTO7KRnn3Ck/hOXad+QmccQb+dLUwh08rv+3DqG+gemGPvUsNCbPSfRAtit\n" + 
				"WB+2zfb5BcR2Yfx8M0PtAgMBAAGgADANBgkqhkiG9w0BAQsFAAOCAQEAAb4zD9M2\n" + 
				"a95UOfSfy5fFka9Aw1DyCmlHFRgAtpLLLBzRW6k3kXcenlv654IhtjkeJpJaf2rM\n" + 
				"Gb2QayjtI84dk+v4ggfJJVJYBwQhBENT6m1EnBZGvvvT0Ea/Fufdd17HSVW1l385\n" + 
				"Ubfg5+hf+nfgsSV+jT7ISRfABEkAqVmEpZ9C5K4m7yyIINB6fXzoys4S+l9lEoRi\n" + 
				"FHSAwAacFQCm0DViah+ZyKc9OjXMBqqStcCemwc+BkNynBWNnk8tnLc4aF3Y0iOk\n" + 
				"IsSC+tgMogeVOcmRDjBNslUqnVP1CLPiu16cIPFg7uEBMsLXPKSnJshnz+l3Ae9f\n" + 
				"ZOpXDTCwV+XRFw==";

		String corrispettivi = "<p:RichiestaCertificatoDispositivo xmlns:p=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" versione=\"1.0\" xsi:schemaLocation=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0 CorrispettiviMessaggiTypes_v1.0.xsd\">\n" + 
				"<Csr>"+CSR+"</Csr>\n" + 
				"<Dispositivo>\n" + 
				"<Tipo>RT</Tipo>\n" + 
				"<MarchioFabbricante>rch</MarchioFabbricante>\n" + 
				"<Modello>test</Modello>\n" + 
				"<RifApprovazioneDispositivo>\n" + 
				"<Numero>1</Numero>\n" + 
				"<Data>2017-01-01</Data>\n" + 
				"</RifApprovazioneDispositivo>\n" + 
				"</Dispositivo>\n" + 
				"<TecnicoVerificatore>\n" + 
				"<CodiceFiscale>TFNGNN71M14H931Z</CodiceFiscale>\n" + 
				"<PIvaSocieta>\n" + 
				"<IdPaese>IT</IdPaese>\n" + 
				"<IdCodice>12312312312</IdCodice>\n" + 
				"</PIvaSocieta>\n" + 
				"</TecnicoVerificatore>\n" + 
				"</p:RichiestaCertificatoDispositivo>";		 
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(RichiestaCertificatoDispositivoType.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(corrispettivi);
			RichiestaCertificatoDispositivoType AttivaDispositivo = (RichiestaCertificatoDispositivoType) unmarshaller.unmarshal(reader);

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        Document dosigndocument = db.newDocument();
	        
	        // Marshal the Object to a Document
	        JAXBContext jc = JAXBContext.newInstance(RichiestaCertificatoDispositivoType.class);
	        Marshaller marshaller = jc.createMarshaller();
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	        marshaller.marshal(AttivaDispositivo, dosigndocument);
	        

			String result = SignReply.Sign(dosigndocument, "sogeirch");
			String invio = result.substring(0, 1694)+result.substring(result.length()-79, result.length());
			String EsitoOperazione = this.post(result, url);
			
			jaxbContext = JAXBContext.newInstance(EsitoRichiestaCertificatoDispositivoType.class);
			unmarshaller = jaxbContext.createUnmarshaller();
			reader = new StringReader(EsitoOperazione);
			
			EsitoRichiestaCertificatoDispositivoType TEsitoOperazione = (EsitoRichiestaCertificatoDispositivoType) unmarshaller.unmarshal(reader);
			
			System.out.println(EsitoOperazione);
			
			File theDir = new File("received_error_response2");

			// if the directory does not exist, create it
			if (!theDir.exists()) {
				theDir.mkdir();
			}
			assertNotNull(TEsitoOperazione);
			
			ErroriType error = TEsitoOperazione.getErrori();
			String FILENAME = "";
			if(error!=null) 
				FILENAME = "received_error_response2/RT_corrispettivi_Error"+error.getErrore().get(0).getCodice()+".xml";
			else
				 FILENAME = "received_error_response2/RT_corrispettivi_noError.xml";

			BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));
			

			bw.write(EsitoOperazione);
			bw.close();
		
			
			System.out.println();
		} catch (JAXBException | ParserConfigurationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	public void Corrispettivi() {

		String url = "dispositivi/corrispettivi/";

		DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		LocalDateTime now = LocalDateTime.now();
		String formattedDate = now.truncatedTo(ChronoUnit.SECONDS).format(formatter);

		String corrispettivi = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" + 
				"<ns3:DatiCorrispettivi xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns3=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0\" versione=\"COR10\">\n" + 
				"    <Trasmissione>\n" + 
				"        <Progressivo>38</Progressivo>\n" + 
				"        <Formato>COR10</Formato>\n" + 
				//	"        <Dispositivo>\n" + 
				//	"            <Tipo>MC</Tipo>\n" + 
				//	"            <IdDispositivo>BBBB0002</IdDispositivo>\n" + 
				//	"        </Dispositivo>\n" + 
				"    </Trasmissione>\n" + 
				"    <DataOraRilevazione>2019-10-23T08:47:43</DataOraRilevazione>\n" + 
				"    <DatiRT>\n" + 
				"        <Riepilogo>\n" + 
				"            <Natura>N4</Natura>\n" + 
				"            <Ammontare>01.00</Ammontare>\n" + 
				"<ImportoParziale>01.00</ImportoParziale>\n"+
				"        </Riepilogo>\n"+
				"<Totali>\n" + 
				"<NumeroDocCommerciali>1</NumeroDocCommerciali>\n" + 
				
				"</Totali>\n" + 


				"    </DatiRT>\n" + 
				//		"<Segnalazione><Matricola>0001ab01</Matricola><DataOra>2017-11-06T10:17:26</DataOra>"
				//		+ "<Codice>06</Codice><Note>errore quadratura contatori</Note></Segnalazione>"+
				
				"</ns3:DatiCorrispettivi>";	/*"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" + 
				"<ns3:DatiCorrispettivi xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns3=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0\" versione=\"COR10\">\n" + 
				"    <Trasmissione>\n" + 
				"        <Progressivo>38</Progressivo>\n" + 
				"        <Formato>COR10</Formato>\n" + 
				//	"        <Dispositivo>\n" + 
				//	"            <Tipo>MC</Tipo>\n" + 
				//	"            <IdDispositivo>BBBB0002</IdDispositivo>\n" + 
				//	"        </Dispositivo>\n" + 
				"    </Trasmissione>\n" + 
				"    <DataOraRilevazione>2019-10-23T08:47:43</DataOraRilevazione>\n" + 
				"    <DatiRT>\n" + 
				"        <Riepilogo>\n" + 
				"            <Natura>N4</Natura>\n" + 
				"            <Ammontare>100.00</Ammontare>\n" + 
				"<ImportoParziale>80.00</ImportoParziale>\n"+
				"            <TotaleAmmontareResi>10.00</TotaleAmmontareResi>\n" + 
				"            <TotaleAmmontareAnnulli>10.00</TotaleAmmontareAnnulli>\n" + 
				"<NonRiscossoOmaggio>10.00</NonRiscossoOmaggio>\n" +
				"        </Riepilogo>\n"+
				"<Totali>\n" + 
				"<NumeroDocCommerciali>1</NumeroDocCommerciali>\n" + 
				"<PagatoContanti>100.00</PagatoContanti>\n" + 
				"<PagatoElettronico>80.00</PagatoElettronico>\n" + 
				"</Totali>\n" + 


				"    </DatiRT>\n" + 
				//		"<Segnalazione><Matricola>0001ab01</Matricola><DataOra>2017-11-06T10:17:26</DataOra>"
				//		+ "<Codice>06</Codice><Note>errore quadratura contatori</Note></Segnalazione>"+
				"<InterventoTecnico><CFTecnico>SCNDNL89D06E253J</CFTecnico>\n" + 
				"<IdIVALaboratorio><IdPaese>IT</IdPaese><IdCodice>02566448514</IdCodice>\n" + 
				"</IdIVALaboratorio><DataOra>2019-10-23T08:47:43</DataOra><Codice>07</Codice>\n" + 
				"<Note>Versione 1.0 prv. xxx</Note>"+
				"<NumeroSw>1.0</NumeroSw>"+
				"<DataRelease>2019-10-23T08:47:43</DataRelease>" +
				"+</InterventoTecnico>"+
				"</ns3:DatiCorrispettivi>";		*/ 
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(DatiCorrispettiviType.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(corrispettivi);
			DatiCorrispettiviType AttivaDispositivo = (DatiCorrispettiviType) unmarshaller.unmarshal(reader);

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document dosigndocument = db.newDocument();

			// Marshal the Object to a Document
			JAXBContext jc = JAXBContext.newInstance(DatiCorrispettiviType.class);
			Marshaller marshaller = jc.createMarshaller();
			//marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(AttivaDispositivo, dosigndocument);


			String result = SignReply.Sign(dosigndocument, "sogeirchm");
			String invio = result.substring(0, 1694)+result.substring(result.length()-79, result.length());
			String EsitoOperazione = this.post(result, url);

			jaxbContext = JAXBContext.newInstance(EsitoOperazioneType.class);
			unmarshaller = jaxbContext.createUnmarshaller();
			reader = new StringReader(EsitoOperazione);


			System.out.println(EsitoOperazione);
			if(EsitoOperazione.length()>0) {
				EsitoOperazioneType TEsitoOperazione = (EsitoOperazioneType) unmarshaller.unmarshal(reader);


				File theDir = new File("received_error_response2");

				// if the directory does not exist, create it
				if (!theDir.exists()) {
					theDir.mkdir();
				}
				ErroriType error = TEsitoOperazione.getErrori();
				String FILENAME = "";
				if(error!=null) 
					FILENAME = "received_error_response2/RT_corrispettivi_Error"+error.getErrore().get(0).getCodice()+".xml";
				else
					FILENAME = "received_error_response2/RT_corrispettivi_noError.xml";

				BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));


				bw.write(EsitoOperazione);
				bw.close();
			}

			System.out.println();
		} catch (JAXBException | ParserConfigurationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Evento() {
		
		String url = "dispositivi/evento/";
				
				String evento = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><r:EventoDispositivo xmlns:r=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0\" versione=\"1.0\">\n" + 
						"<Evento>FUORI_SERVIZIO</Evento><DataOra>2019-09-26T12:00:54</DataOra><Dettaglio><Codice>00600</Codice></Dettaglio>\n" + 
						"</r:EventoDispositivo>";		 
				try {
					JAXBContext jaxbContext = JAXBContext.newInstance(EventoDispositivoType.class);
					Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
					StringReader reader = new StringReader(evento);
					EventoDispositivoType EventoDispositivo = (EventoDispositivoType) unmarshaller.unmarshal(reader);

					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			        DocumentBuilder db = dbf.newDocumentBuilder();
			        Document dosigndocument = db.newDocument();
			        
			        // Marshal the Object to a Document
			        JAXBContext jc = JAXBContext.newInstance(EventoDispositivoType.class);
			        Marshaller marshaller = jc.createMarshaller();
			        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			        marshaller.marshal(EventoDispositivo, dosigndocument);
			        

					String result = SignReply.Sign(dosigndocument, "sogeidn");
					
					String EsitoOperazione = this.put(result, url);
					
					jaxbContext = JAXBContext.newInstance(EsitoOperazioneType.class);
					unmarshaller = jaxbContext.createUnmarshaller();
					reader = new StringReader(EsitoOperazione);
					
					EsitoOperazioneType TEsitoOperazione = (EsitoOperazioneType) unmarshaller.unmarshal(reader);
					
					System.out.println(EsitoOperazione);
					
					File theDir = new File("received_error_response2");

					// if the directory does not exist, create it
					if (!theDir.exists()) {
						theDir.mkdir();
					}
					ErroriType error = TEsitoOperazione.getErrori();
					String FILENAME = "";
					if(error!=null) 
						FILENAME = "received_error_response2/RT_eventi_Error"+error.getErrore().get(0).getCodice()+".xml";
					else
						 FILENAME = "received_error_response2/RT_eventi_noError.xml";
					
					BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));
					

					bw.write(EsitoOperazione);
					bw.close();
				
				} catch (JAXBException | ParserConfigurationException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
	public void Attivazione() {
		String url = "dispositivi/";
		
		String attivazione = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
				"<r:AttivaDispositivo xmlns:r=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0\" "
				+ "versione=\"1.0\"><Gestore><IdPaese>IT</IdPaese><IdCodice>07511580156</IdCodice></Gestore>"
				+ "<TecnicoVerificatore><CodiceFiscale>CHCFNC64E01L117X</CodiceFiscale><PIvaSocieta>"
				+ "<IdPaese>IT</IdPaese><IdCodice>04210780963</IdCodice></PIvaSocieta></TecnicoVerificatore>"
				+ "<InformazioniAddizionali><RT><MatricolaRegistratoreDiCassa>EA99000102</MatricolaRegistratoreDiCassa>"
				+ "</RT></InformazioniAddizionali></r:AttivaDispositivo>";		 
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(AttivaDispositivoType.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			StringReader reader = new StringReader(attivazione);
			AttivaDispositivoType AttivaDispositivo = (AttivaDispositivoType) unmarshaller.unmarshal(reader);
			//AttivaDispositivo.getGestore().setIdCodice("07511580156");
			//AttivaDispositivo.getTecnicoVerificatore().setCodiceFiscale("CHCFNC64E01L117X");
			
			 DateFormat format = new SimpleDateFormat("M/d/yyyy");
			 String dateStr = "03/01/2018";
			 Date date = format.parse(dateStr);

			 GregorianCalendar gregory = new GregorianCalendar();
			 gregory.setTime(date);

			 XMLGregorianCalendar calendar = DatatypeFactory.newInstance()
			         .newXMLGregorianCalendar(
			             gregory);
			
			//AttivaDispositivo.getInformazioniAddizionali().getRT().setDataMessaInServizio(calendar);
			
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        DocumentBuilder db = dbf.newDocumentBuilder();
	        Document dosigndocument = db.newDocument();
	        
	        // Marshal the Object to a Document
	        JAXBContext jc = JAXBContext.newInstance(AttivaDispositivoType.class);
	        Marshaller marshaller = jc.createMarshaller();
	        //marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	        marshaller.marshal(AttivaDispositivo, dosigndocument);
	        
	        

			String result = SignReply.Sign(dosigndocument, "sogeirchm");
			
			String EsitoOperazione = this.put(result, url);
			
			jaxbContext = JAXBContext.newInstance(EsitoOperazioneType.class);
			unmarshaller = jaxbContext.createUnmarshaller();
			reader = new StringReader(EsitoOperazione);
			
			EsitoOperazioneType TEsitoOperazione = (EsitoOperazioneType) unmarshaller.unmarshal(reader);
			
			System.out.println(EsitoOperazione);
			
			File theDir = new File("received_error_response2");

			// if the directory does not exist, create it
			if (!theDir.exists()) {
				theDir.mkdir();
			}
			ErroriType error = TEsitoOperazione.getErrori();
			String FILENAME = "";
			if(error!=null) 
				FILENAME = "received_error_response2/RT_Attivazione_Error"+error.getErrore().get(0).getCodice()+".xml";
			else
				 FILENAME = "received_error_response2/RT_Attivazione_noError.xml";

			BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));
			

			bw.write(EsitoOperazione);
			bw.close();
		
		} catch (JAXBException | ParserConfigurationException | IOException | ParseException | DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void Lotteria() {
		String url = "dispositivi/lotteria/corrispettivi/";
		
		
		String docs = "";
		
		
		
		for(int i=1; i<=0; i++) {
		String rpadded = String.format("%04d" , i);
		String reso = "  <DocumentoCommerciale>\n" + 
				"    <IdCliente>1234567890123456</IdCliente>\n" + 
				"    <DataOra>2019-11-12T23:00:12</DataOra>\n" + 
				"    <NumeroProgressivo>1290-"+rpadded+"</NumeroProgressivo>\n" + 
				"    <Ammontare>99999999999.99</Ammontare>\n" + 
				"    <ResoAnnullo>\n" + 
				"      <Tipologia>R</Tipologia>\n" + 
				"      <DataOra>2019-11-07T12:12:12</DataOra>\n" + 
				"      <Progressivo>1279-"+rpadded+"</Progressivo>\n" + 
				"      <Dispositivo>\n" + 
				"        <MatrTrasm>str12340000</MatrTrasm>\n" + 
				"        <MatrCassa>str12340</MatrCassa>\n" + 
				"      </Dispositivo>\n" + 
				"    </ResoAnnullo>\n" + 
				"  </DocumentoCommerciale>";
		
			docs += reso;
		
		}
		for(int i=1; i<=1; i++) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
			LocalDateTime now = LocalDateTime.now();
			String formattedDate = now.truncatedTo(ChronoUnit.SECONDS).format(formatter);
			System.out.println(formattedDate);
			String padded = String.format("%04d" , i);
			//"<DocumentoCommerciale><IdCliente>7</IdCliente><DataOra>2019-12-05T15:15:35</DataOra><NumeroProgressivo>0099-0010</NumeroProgressivo><Ammontare>47.74</Ammontare><Vendita><DatiPagamento><Tipo>PC</Tipo><Importo>20.00</Importo></DatiPagamento><DatiPagamento><Tipo>PE</Tipo><Importo>10.00</Importo></DatiPagamento><DatiPagamento><Tipo>NR</Tipo><Importo>17.74</Importo></DatiPagamento></Vendita></DocumentoCommerciale>";/*
			String doc = "  <DocumentoCommerciale>\n" + 
					"    <IdCliente>1234567890123456</IdCliente>\n" + 
					"    <DataOra>"+formattedDate+"</DataOra>\n" + 
				//	"    <DataOra>2019-11-16T23:00:12</DataOra>\n" + 

					"    <NumeroProgressivo>1289-"+padded+"</NumeroProgressivo>\n" + 
					"    <Ammontare>47.74</Ammontare>\n" + 
					"    <Vendita>\n" + 
					"                  <DatiPagamento>\n" + 
					"                  <Tipo>PC</Tipo>\n" + 
					"                  <Importo>10.00</Importo>\n" + 
					"                  </DatiPagamento>\n" + 
					"                  <DatiPagamento>\n" + 
					"                          <Tipo>PE</Tipo>\n" + 
					"                          <Importo>17.74</Importo>\n" + 
					"                        </DatiPagamento>\n" + 
					"                        <DatiPagamento>\n" + 
					"                          <Tipo>NR</Tipo>\n" + 
					"                          <Importo>20.00</Importo>\n" + 
					"                        </DatiPagamento>\n" + 
					"                      </Vendita>\n" +   
					"                    </DocumentoCommerciale>\n" ;
		
			docs +=doc ;
			
		}
		
		
		String lotteria = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" + 
				"<r:DocCommercialiLotteria xmlns:r=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/v1.0\" versione=\"1.0\">\n" + 
				"  <DatiTrasmissione>\n" + 
				"    <Formato>DCL10</Formato>\n" + 
				//" <Intestazione>\n"
				 "<Denominazione>"
				 + "aaaaa</Denominazione>\n"
				//+ "<Comune>Pisa</Comune>\n"
				//+ "</Intestazione> \n" +
				+" <IdCassa>33331234</IdCassa>"+
				"  </DatiTrasmissione>\n" + 
				docs+
				
				"</r:DocCommercialiLotteria>";
		
		
		
		
		try {
			
		/*	lotteria = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><p:DocCommercialiLotteria xmlns:p=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/v1.0\" xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" versione=\"1.0\" xsi:schemaLocation=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/v1.0\">\n" + 
					"	<DatiTrasmissione>\n" + 
					"		<Formato>DCL10</Formato>\n" + 
					"		<Denominazione>prova sperimentatori</Denominazione>\n" + 
					"		<IdCassa>cassa001</IdCassa>\n" + 
					"	</DatiTrasmissione>\n" + 
					"	<DocumentoCommerciale>\n" + 
					"		<IdCliente>BCCGNM4</IdCliente>\n" + 
					"		<DataOra>2019-09-10T11:01:00</DataOra>\n" + 
					"		<NumeroProgressivo>1001-8037</NumeroProgressivo>\n" + 
					"		<Ammontare>47.74</Ammontare>\n" + 
					"		<Vendita>\n" + 
					"			<DatiPagamento>\n" + 
					"				<Tipo>PC</Tipo>\n" + 
					"				<Importo>10.00</Importo>\n" + 
					"			</DatiPagamento>\n" + 
					"			<DatiPagamento>\n" + 
					"				<Tipo>PE</Tipo>\n" + 
					"				<Importo>17.74</Importo>\n" + 
					"			</DatiPagamento>\n" + 
					"			<DatiPagamento>\n" + 
					"				<Tipo>NR</Tipo>\n" + 
					"				<Importo>20.00</Importo>\n" + 
					"			</DatiPagamento>\n" + 
					"		</Vendita>\n" + 
					"	</DocumentoCommerciale>\n" + 
					"</p:DocCommercialiLotteria>";*/
		//	String	lotteria = "";
			
		//	lotteria = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + 
		//			"<p:DocCommercialiLotteria xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:p=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/v1.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" versione=\"1.0\" xsi:schemaLocation=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/v1.0 DocCommercialiLotteriaTypes_v1.0.xsd http://www.w3.org/2000/09/xmldsig# xmldsig-core-schema.xsd\"><DatiTrasmissione><Formato>DCL10</Formato><Denominazione>A</Denominazione></DatiTrasmissione><DocumentoCommerciale><IdCliente>8</IdCliente><DataOra>2019-12-05T15:24:43</DataOra><NumeroProgressivo>0100-0001</NumeroProgressivo><Ammontare>47.74</Ammontare><Vendita><DatiPagamento><Tipo>PC</Tipo><Importo>20.00</Importo></DatiPagamento><DatiPagamento><Tipo>PE</Tipo><Importo>10.00</Importo></DatiPagamento><DatiPagamento><Tipo>NR</Tipo><Importo>17.74</Importo></DatiPagamento></Vendita></DocumentoCommerciale>";
			
			
		JAXBContext jaxbContext = JAXBContext.newInstance(DocCommercialiLotteriaType.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		StringReader reader = new StringReader(lotteria);//.replaceAll("\\n", "").trim());
		DocCommercialiLotteriaType CommercialiLotteria = (DocCommercialiLotteriaType) unmarshaller.unmarshal(reader);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document dosigndocument = db.newDocument();
        
        // Marshal the Object to a Document
        JAXBContext jc = JAXBContext.newInstance(DocCommercialiLotteriaType.class);
        Marshaller marshaller = jc.createMarshaller();
        //marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(CommercialiLotteria, dosigndocument);
        

		String result = SignReply.Sign(dosigndocument, "sogeirchm");
		System.out.println(result);
		String EsitoOperazione = this.post(result, url);
		
		jaxbContext = JAXBContext.newInstance(DocCommercialiLotteriaEsitoType.class);
		unmarshaller = jaxbContext.createUnmarshaller();
		reader = new StringReader(EsitoOperazione);
		
		byte[] utf8Bytes = result.getBytes("UTF-8");
		System.out.println("Dimesione File: "+utf8Bytes.length);
		
		DocCommercialiLotteriaEsitoType TEsitoOperazione = (DocCommercialiLotteriaEsitoType) unmarshaller.unmarshal(reader);
		
		System.out.println(EsitoOperazione);
		
		File theDir = new File("received_error_response2");

		// if the directory does not exist, create it
		if (!theDir.exists()) {
			theDir.mkdir();
		}
		String codice = "";
		String desc  = "";
		EsitoType error = TEsitoOperazione.getEsito();
		
		if(TEsitoOperazione.getListaErrori()!=null) {
			List<cnr.isti.sse.data.corrispettivi.doccommercialilotteria.messaggi.ErroriType> list = TEsitoOperazione.getListaErrori();

			if(list!=null) {
				if(!list.isEmpty()) {
					codice = list.get(0).getCodice();
					if(list.get(0).getErrore()!=null)
						if(!list.get(0).getErrore().isEmpty())
						codice = list.get(0).getErrore().get(0).getCodice();
						desc = list.get(0).getErrore().get(0).getDescrizione();
				}

			}
		}

		if(TEsitoOperazione.getSegnalazioniDocComm()!=null) {
			SegnalazioniDocCommType lists = TEsitoOperazione.getSegnalazioniDocComm();
			if(lists.getSegnalazione()!=null) {
				if(!lists.getSegnalazione().isEmpty())
					codice =  lists.getSegnalazione().get(0).getErrori().getCodice();
					//desc =  lists.getSegnalazione().get(0).getErrori().getDescrizione();
			}
		}
		
		if(codice!="") {
			System.out.println("Errore: " +codice);
			System.out.println("Errore: " +desc);
		}
		
		System.out.println("Esito: " +error);

		String FILENAME = "";
		if(error!=null) 
			FILENAME = "received_error_response2/RT_corrispettivi_Error"+error+"."+codice+".xml";
		else
			 FILENAME = "received_error_response2/RT_corrispettiviLotteria_noError.xml";

		BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));
		

		bw.write(EsitoOperazione);
		bw.close();
	
		
		System.out.println();
		
		}catch (JAXBException | ParserConfigurationException | IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Client ignoreSSLClient()  {
		try{
			SSLContext sslcontext = SSLContext.getInstance("TLS");

			sslcontext.init(null,  new TrustManager[]{new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException{}
				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException{}
				public X509Certificate[] getAcceptedIssuers()
				{
					return new X509Certificate[0];
				}

			}}, new java.security.SecureRandom());


			/*	    return ClientBuilder.newBuilder()
	                        .sslContext(sslcontext)
	                        .hostnameVerifier((s1, s2) -> true)
	                        .build();*/

			HostnameVerifier allowAll = new HostnameVerifier() 
			{
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			return ClientBuilder.newBuilder().sslContext(sslcontext).hostnameVerifier(allowAll).build();
		}catch (Exception e) {
			// TODO: handle exception

		}
		return ClientBuilder.newClient();
	}


	public Response sendGet(String path, String... args){
		Client client = ignoreSSLClient();
		WebTarget target = null;
		if(args!=null){
			 target = client.target(URL).path(basepath+path).queryParam(args[0], args[1]).queryParam(args[2], args[3]).queryParam(args[4], args[5]);
		}else{
			 target = client.target(URL).path(basepath+path);
		}
			
		Response allID =  target.request(MediaType.APPLICATION_XML).get();

		return allID;
	}


	public String post(String content, String path){

		Client client =  ignoreSSLClient();
		WebTarget target = client.target(URL).path(basepath+path);

		Entity<String> entity = Entity.entity(content,MediaType.APPLICATION_XML);

		Response response =  target.request().post(entity);
		
	    log.trace(	response.getStatus());
	    log.trace(response.getStatusInfo());

		String reply = response.readEntity(String.class);

		return reply;
		//FacesContext context = FacesContext.getCurrentInstance();
		//context.getExternalContext().getRequestMap().put("rest", id);

	}
	
	public String put(String content, String path){

		Client client =  ignoreSSLClient();
		WebTarget target = client.target(URL).path(basepath+path);

		Entity<String> entity = Entity.entity(content,MediaType.APPLICATION_XML);

		Response response =  target.request().put(entity);

		String reply = response.readEntity(String.class);

		return reply;
		//FacesContext context = FacesContext.getCurrentInstance();
		//context.getExternalContext().getRequestMap().put("rest", id);

	}



}
