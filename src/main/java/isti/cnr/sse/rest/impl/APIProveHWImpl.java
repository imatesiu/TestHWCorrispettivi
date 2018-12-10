package isti.cnr.sse.rest.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.IOUtils;
import org.glassfish.grizzly.utils.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import cnr.isti.sse.data.corrispettivi.DatiCorrispettiviType;
import cnr.isti.sse.data.corrispettivi.messaggi.EsitoOperazioneType;
import cnr.isti.sse.data.corrispettivi.messaggi.EsitoRichiestaCertificatoDispositivoType;
import isti.cnr.sse.rest.impl.firma.SignReply;

@Consumes(MediaType.APPLICATION_XML)
// @Produces(MediaType.APPLICATION_XML)
@Path("/corrispettivi")
public class APIProveHWImpl {

	// @Inject
	// TokenPersistence em;

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(APIProveHWImpl.class);

	private static Map<String, BigDecimal> map = new HashMap<>();

	private static Map<String, Pair<Integer, Date>> timediff = new HashMap<>();

	private static Map<String, Integer> ricevuti = new HashMap<>();

	@Path("/clearall")
	@GET
	public String clearall() {

		map = new HashMap<>();
		ricevuti = new HashMap<>();
		timediff = new HashMap<>();
		log.info("Clear All\n\r");
		return "<html><body>OK</body></html>";
	}

	@Path("/clear/{key:.*}")
	@GET
	public String clear(@PathParam("key") String key) {
		if (map.containsKey(key)) {
			map.put(key, new BigDecimal(0));
			ricevuti.put(key, 0);
			timediff.put(key, new Pair<>(0, new Date()));
			log.info("Clear " + key);
			return "<html><body>OK</body></html>";
		} else
			return "<html><body>Elemento non presente</body></html>";
	}

	@Path("/init/{key:.*}")
	@GET
	public String init(@PathParam("key") String key, @QueryParam("grantot") int grantotale) {
		if (map.containsKey(key)) {
			map.put(key, new BigDecimal(grantotale));
			ricevuti.put(key, 0);
			timediff.put(key, new Pair<>(0, new Date()));
			log.info("Init: " + key);
			log.info("Grantotale " + grantotale);
			log.info("");
			return "<html><body>OK,  Init: " + key + " Grantotale " + grantotale + "</body></html>";
		} else {
			map.put(key, new BigDecimal(grantotale));
			ricevuti.put(key, 0);
			timediff.put(key, new Pair<>(0, new Date()));
			log.info("Init " + key);
			log.info("Grantotale " + grantotale);
			return "<html><body>Elemento non presente, creato Init: " + key + " Grantotale: " + grantotale
					+ "</body></html>";
		}

	}

	@Path("/info/{key:.*}")
	@GET
	public String info(@PathParam("key") String key) {
		if (map.containsKey(key)) {
			BigDecimal grantotale = map.get(key);
			int num = ricevuti.get(key);
			Integer diff = timediff.get(key).getFirst();
			// map.put(key, new BigDecimal(0));
			// ricevuti.put(key, 0);
			log.info("Info for: " + key);
			log.info("Grantotale " + grantotale);
			log.info("Ricevuti in totale: " + num);
			log.info("TimeDiff: " + diff);
			log.info("");
			return "<html><body>OK,  Info for: " + key + " Grantotale: " + grantotale + " Ricevuti in totale: " + num
					+ " tempo in sencodi:" + diff + " </body></html>";
		} else {
			return "<html><body>Elemento non presente, " + key + "</body></html>";
		}

	}

	@Path("/")
	@POST
	public String putListMisuratoriFiscale2(String Corri, @Context HttpServletRequest request, @Context HttpServletResponse response)
			throws JAXBException {// DatiCorrispettiviType Corrispettivi,
		// @Context HttpServletRequest request){
		response.setHeader("Connection", "Close");
		JAXBContext jaxbContext = JAXBContext.newInstance(DatiCorrispettiviType.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		if(Corri.length()==0){
			try{
				InputStream is = APIProveHWImpl.class.getClassLoader().getResourceAsStream("response.err.tracciato.xml");
				String text = IOUtils.toString(is, StandardCharsets.UTF_8.name());
				throw new WebApplicationException(Response.status(406).entity(text).build());
			} catch (IOException e) {
				e.printStackTrace();
				log.error(e);
			}
		}
		try {
			StringReader reader = new StringReader(Corri);
			DatiCorrispettiviType Corrispettivi = (DatiCorrispettiviType) unmarshaller.unmarshal(reader);

			Date now = new Date();
			String timeStamp = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(now);
			Pair<String, Boolean> pair = Utility.getMatricola(Corrispettivi, Corri);
			String ipAddress = pair.getFirst();
			if (ipAddress == null) {
				ipAddress = request.getHeader("X-FORWARDED-FOR");
			}

			if (ipAddress == null) {
				if(request.getRemoteAddr()!=null){
					ipAddress = request.getRemoteAddr();
				}else{
					ipAddress = "255.255.255.255";
				}
			}
			log.info("received form: " + ipAddress + " " + timeStamp);


			// is client behind something?
			aggiornadiff(now, ipAddress);

			int num = aggiornaricevuti(ipAddress);
			Utility.writeTo(Corri, ipAddress, num);
			Utility.calc(Corrispettivi, ipAddress, map);

			/*EsitoOperazioneType esito = new EsitoOperazioneType();
			esito.setIdOperazione(String.valueOf(num));
			esito.setVersione("1.0");*/
			Beep.tone(1000, 300, ipAddress);

			if(pair.getSecond()){
				EsitoOperazioneType esito = new EsitoOperazioneType();
				int x = 33333;
				esito.setIdOperazione(String.valueOf(x));
				esito.setVersione("1.0");
			
				
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		        DocumentBuilder db = dbf.newDocumentBuilder();
		        Document dosigndocument = db.newDocument();
		        
		        // Marshal the Object to a Document
		        JAXBContext jc = JAXBContext.newInstance(EsitoOperazioneType.class);
		        Marshaller marshaller = jc.createMarshaller();
		        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		        marshaller.marshal(esito, dosigndocument);
		        
		        
				String result = SignReply.Sign(dosigndocument);
				return result;//jaxbObjectToXML(esito);
				
				
				/*InputStream is = APIProveHWImpl.class.getClassLoader().getResourceAsStream("response.xml");
				String text = IOUtils.toString(is, StandardCharsets.UTF_8.name());
				return text;*/
			}else{
				InputStream is = APIProveHWImpl.class.getClassLoader().getResourceAsStream("response.err.firma.xml");
				String text = IOUtils.toString(is, StandardCharsets.UTF_8.name());
				throw new WebApplicationException(Response.status(406).entity(text).build());
			}
			// return "<?xml version=\"1.0\" encoding=\"UTF-8\"
			// standalone=\"yes\"?><EsitoOperazione
			// xmlns=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0\"
			// versione=\"1.0\"><IdOperazione>"+num+"</IdOperazione></EsitoOperazione>";



		} catch (Exception  e) {
			e.printStackTrace();
			log.error(e);
		}
		try{
			InputStream is = APIProveHWImpl.class.getClassLoader().getResourceAsStream("response.err.tracciato.xml");
			String text = IOUtils.toString(is, StandardCharsets.UTF_8.name());
			throw new WebApplicationException(Response.status(406).entity(text).build());
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
		/*int x = (int) Math.random() * 10;
		EsitoOperazioneType esito = new EsitoOperazioneType();
		esito.setIdOperazione(String.valueOf(x));
		esito.setVersione("1.0");
		return esito;*/// "<ns2:EsitoOperazione
		// xmlns:ns2=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0\"
		// versione=\"1.0\"><IdOperazione>0</IdOperazione></ns2:EsitoOperazione>";

	}

	/*	@Path("/v2")
	@POST
	public EsitoOperazioneType putListMisuratoriFiscale(String Corri, @Context HttpServletRequest request)
			throws JAXBException {// DatiCorrispettiviType Corrispettivi,
									// @Context HttpServletRequest request){
		JAXBContext jaxbContext = JAXBContext.newInstance(DatiCorrispettiviType.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(Corri);
		DatiCorrispettiviType Corrispettivi = (DatiCorrispettiviType) unmarshaller.unmarshal(reader);

		Date now = new Date();
		String timeStamp = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(now);
		String ipAddress = getMatricola(Corrispettivi, Corri);
		if (ipAddress == null) {
			ipAddress = request.getHeader("X-FORWARDED-FOR");
		}

		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		log.info("received form: " + ipAddress + " " + timeStamp);
		try {

			// is client behind something?
			aggiornadiff(now, ipAddress);

			int num = aggiornaricevuti(ipAddress);
			Utility.writeTo(Corri, ipAddress, num);
			Utility.calc(Corrispettivi, ipAddress, map);

			EsitoOperazioneType esito = new EsitoOperazioneType();
			esito.setIdOperazione(String.valueOf(num));
			esito.setVersione("1.0");
			Beep.tone(1000, 300, ipAddress);
			return esito;
			// return "<?xml version=\"1.0\" encoding=\"UTF-8\"
			// standalone=\"yes\"?><EsitoOperazione
			// xmlns=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0\"
			// versione=\"1.0\"><IdOperazione>"+num+"</IdOperazione></EsitoOperazione>";

		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		int x = (int) Math.random() * 10;
		EsitoOperazioneType esito = new EsitoOperazioneType();
		esito.setIdOperazione(String.valueOf(x));
		esito.setVersione("1.0");

		return esito;// "<ns2:EsitoOperazione
						// xmlns:ns2=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0\"
						// versione=\"1.0\"><IdOperazione>0</IdOperazione></ns2:EsitoOperazione>";

	}
	 */
	private void aggiornadiff(Date now, String key) {
		if (timediff.containsKey(key)) {
			Date oldtime = (Date) timediff.get(key).getSecond();
			int diff = (int) ((now.getTime() - oldtime.getTime()) / 1000);
			timediff.put(key, new Pair<>(diff, now));
			log.info("diff_time: " + diff);
		} else {
			timediff.put(key, new Pair<>(0, now));
			log.info("diff_time: " + 0);
		}

	}

	private int aggiornaricevuti(String key) {
		if (ricevuti.containsKey(key)) {
			int res = ricevuti.get(key) + 1;
			log.info("totale ricevuti da " + key + ": " + res);
			ricevuti.put(key, res);
			return res;
		} else {
			ricevuti.put(key, 1);
			log.info("totale ricevuti da " + key + ": 1");
			return 1;
		}

	}

	/*
	 * @Path("/v1")
	 * 
	 * @POST public String putListMisuratoriFiscale(String
	 * Corrispettivi, @Context HttpServletRequest request){
	 * 
	 * 
	 * String timeStamp = new
	 * SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(new Date()); int x =
	 * (int)Math.random() * 10; EsitoOperazioneType esito = new
	 * EsitoOperazioneType(); esito.setIdOperazione(String.valueOf(x));
	 * esito.setVersione("1.0"); //is client behind something? String ipAddress
	 * = request.getHeader("X-FORWARDED-FOR"); if (ipAddress == null) {
	 * ipAddress = request.getRemoteAddr(); } log.info("received form: "
	 * +ipAddress+ " "+timeStamp); int num = aggiornaricevuti(ipAddress);
	 * Utility.writeTo(Corrispettivi, ipAddress, num); // return
	 * "<ns2:EsitoOperazione xmlns:ns2=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0\" versione=\"1.0\"><IdOperazione>"
	 * +num+"</IdOperazione></ns2:EsitoOperazione>";
	 * //"<EsitoOperazione versione=\"1.0\"><IdOperazione>0</IdOperazione></EsitoOperazione>"
	 * ; }
	 */

	
	private static Document convertStringToDocument(String xmlStr) {
		try {

			DOMResult output = new DOMResult();
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(new StreamSource(new StringReader(xmlStr)), output);

			return (Document) output.getNode();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private <T> Document marshallToDocument(T object, Class<T> type) {
		try {
			JAXBContext context = JAXBContext.newInstance(type);
			Marshaller marshaller = context.createMarshaller();
			DOMResult domResult = new DOMResult();
			marshaller.marshal(object, domResult);
			return (Document) domResult.getNode();
		} catch (JAXBException e) {
			// Rethrow as runtime
			throw new RuntimeException("Unable to marshall JAXB SAML object to DOM for signing.", e);
		}
	}

}
