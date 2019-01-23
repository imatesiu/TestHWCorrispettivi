package isti.cnr.sse.rest.impl.firma;

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
import java.util.Date;
import java.util.GregorianCalendar;

import javax.net.ssl.X509TrustManager;


import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
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

import cnr.isti.sse.data.corrispettivi.messaggi.AttivaDispositivoType;
import cnr.isti.sse.data.corrispettivi.messaggi.ErroriType;
import cnr.isti.sse.data.corrispettivi.messaggi.EsitoOperazioneType;
import cnr.isti.sse.data.corrispettivi.messaggi.EsitoRichiestaCertificatoDispositivoType;
import cnr.isti.sse.data.corrispettivi.messaggi.EventoDispositivoType;
import cnr.isti.sse.data.corrispettivi.DatiCorrispettiviType;

public class CreateError {
	
	private String URL = "https://v-apid-ivaservizi.agenziaentrate.gov.it";
	private String basepath = "/v1/";
	
	public void Corrispettivi() {
		
String url = "dispositivi/corrispettivi/";
		
		String corrispettivi = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" + 
				"<ns3:DatiCorrispettivi xmlns:ns2=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:ns3=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0\" versione=\"COR10\">\n" + 
				"    <Trasmissione>\n" + 
				"        <Progressivo>37</Progressivo>\n" + 
				"        <Formato>COR10</Formato>\n" + 
				"        <Dispositivo>\n" + 
				"            <Tipo>MC</Tipo>\n" + 
				"            <IdDispositivo>BBBB0002</IdDispositivo>\n" + 
				"        </Dispositivo>\n" + 
				"    </Trasmissione>\n" + 
				" <PeriodoInattivo>\n" + 
				"    <Dal>2018-03-03T15:56:00</Dal>\n" + 
				"    <Al>2018-03-08T15:59:00</Al>\n" + 
				"  </PeriodoInattivo>"+
				"    <DataOraRilevazione>2017-10-23T08:47:43</DataOraRilevazione>\n" + 
				"    <DatiRT>\n" + 
				"        <Riepilogo>\n" + 
				"            <Natura>N4</Natura>\n" + 
				"            <Ammontare>0.00</Ammontare>\n" + 
				"            <TotaleAmmontareResi>0.00</TotaleAmmontareResi>\n" + 
				"            <TotaleAmmontareAnnulli>0.00</TotaleAmmontareAnnulli>\n" + 
				"        </Riepilogo>\n" + 
				"    </DatiRT>\n" + 
				"<Segnalazione><Matricola>0001ab01</Matricola><DataOra>2017-11-06T10:17:26</DataOra>"
				+ "<Codice>02</Codice><Note>errore quadratura contatori</Note></Segnalazione>"+
				"<InterventoTecnico><CFTecnico>SCNDNL89D06E253J</CFTecnico>\n" + 
				"<IdIVALaboratorio><IdPaese>IT</IdPaese><IdCodice>02566448514</IdCodice>\n" + 
				"</IdIVALaboratorio><DataOra>2017-11-10T12:43:26</DataOra><Codice>06</Codice>\n" + 
				"<Note>HW Init</Note></InterventoTecnico>"+
				"</ns3:DatiCorrispettivi>";		 
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
	        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	        marshaller.marshal(AttivaDispositivo, dosigndocument);
	        

			String result = SignReply.Sign(dosigndocument, "dispositivo");
			
			String EsitoOperazione = this.post(result, url);
			
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
				FILENAME = "received_error_response2/RT_corrispettivi_Error"+error.getErrore().get(0).getCodice()+".xml";
			else
				 FILENAME = "received_error_response2/RT_corrispettivi_noError.xml";

			BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));
			

			bw.write(EsitoOperazione);
			bw.close();
		
		} catch (JAXBException | ParserConfigurationException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Evento() {
		
		String url = "dispositivi/evento/";
				
				String evento = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><r:EventoDispositivo xmlns:r=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0\" versione=\"1.0\">\n" + 
						"<Evento>DISATTIVAZIONE</Evento><DataOra>2017-09-26T12:00:54</DataOra><Dettaglio><Codice>00603</Codice></Dettaglio>\n" + 
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
			        

					String result = SignReply.Sign(dosigndocument, "dispositivo");
					
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
			AttivaDispositivo.getTecnicoVerificatore().setCodiceFiscale("CHCFNC64E01L117X");
			
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
	        
	        

			String result = SignReply.Sign(dosigndocument, "dispositivo");
			
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
