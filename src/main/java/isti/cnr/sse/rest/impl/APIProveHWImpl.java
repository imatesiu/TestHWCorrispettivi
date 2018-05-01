package isti.cnr.sse.rest.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureException;
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
import org.xml.sax.InputSource;

import cnr.isti.sse.data.corrispettivi.DatiCorrispettiviType;
import cnr.isti.sse.data.corrispettivi.messaggi.EsitoOperazioneType;

@Consumes(MediaType.APPLICATION_XML)
// @Produces(MediaType.APPLICATION_XML)
@Path("/corrispettivi")
public class APIProveHWImpl {

	// @Inject
	// TokenPersistence em;

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(APIProveHWImpl.class);
	
	private static Map<String, RT> map = new HashMap<>();



	@Path("/clearall")
	@GET
	public String clearall() {

		map = new HashMap<>();
		
		log.info("Clear All\n\r");
		return "<html><body>OK</body></html>";
	}

	@Path("/clear/{key:.*}")
	@GET
	public String clear(@PathParam("key") String key) {
		if (map.containsKey(key)) {
			map.remove(key);
		
			log.info("Clear " + key);
			return "<html><body>OK</body></html>";
		} else
			return "<html><body>Elemento non presente</body></html>";
	}

	@Path("/init/{key}")
	@GET
	public String init(@PathParam("key") String key, @QueryParam("grantot") BigDecimal grantotale, 
			@QueryParam("desc") String desc, @QueryParam("z") int z) {
		if (map.containsKey(key)) {
	
			RT rt = map.get(key);
			rt.setGT((grantotale));
			rt.setZ(z);
			log.info("Init: " + key);
			log.info("Grantotale " + grantotale);
			log.info("");
			return "<html><body>OK,  Init: " + key + " Grantotale " + grantotale + "</body></html>";
		} else {
			RT rt = new RT(key, new Date(),(grantotale));
			rt.setDescrizione(desc);
			rt.setZ(z);
			map.put(key,rt);
			
			
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
			RT rt = map.get(key);
			BigDecimal grantotale = rt.getGT();
			int num = rt.getKricevuti();
			Integer diff = rt.getTimediff();
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
	
	@Path("/rt/{key:.*}")
	@GET
	public RT rtinfo(@PathParam("key") String key) {
		if (map.containsKey(key)) {
			RT rt = map.get(key);
			BigDecimal grantotale = rt.getGT();
			int num = rt.getKricevuti();
			Integer diff = rt.getTimediff();
			// map.put(key, new BigDecimal(0));
			// ricevuti.put(key, 0);
			log.info("Info for: " + key);
			log.info("Grantotale " + grantotale);
			log.info("Ricevuti in totale: " + num);
			log.info("TimeDiff: " + diff);
			log.info("");
			return rt;
		} else {
			return null;
		}

	}
	
	@Path("/stop/{key:.*}")
	@GET
	public String rtstop(@PathParam("key") String key) {
		if (map.containsKey(key)) {
			RT rt = map.get(key);
			BigDecimal grantotale = rt.getGT();
			int num = rt.getKricevuti();
			Integer diff = rt.getTimediff();
			rt.setCloded();
			log.info("Info for: " + key);
			log.info("Grantotale " + grantotale);
			log.info("Ricevuti in totale: " + num);
			log.info("TimeDiff: " + diff);
			log.info("");
			return "OK";
		} else {
			return "NonEsiste";
		}

	}
	
	@Path("/")
	@POST
	public String putListMisuratoriFiscale2(String Corri, @Context HttpServletRequest request)
			throws JAXBException {// DatiCorrispettiviType Corrispettivi,
		// @Context HttpServletRequest request){
		JAXBContext jaxbContext = JAXBContext.newInstance(DatiCorrispettiviType.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		try {
			StringReader reader = new StringReader(Corri);
			DatiCorrispettiviType Corrispettivi = (DatiCorrispettiviType) unmarshaller.unmarshal(reader);

			Date now = new Date();
			String timeStamp = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(now);
			Pair<String, Boolean> pair = getMatricola(Corrispettivi, Corri);
			String ipAddress = pair.getFirst();
			if (ipAddress == null) {
				ipAddress = request.getHeader("X-FORWARDED-FOR");
			}

			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}
			log.info("received form: " + ipAddress + " " + timeStamp);


			// is client behind something?
			aggiornadiff(now, ipAddress);

			int num = aggiornaricevuti(ipAddress);
			Utility.writeTo(Corri, ipAddress, num);
			Utility.calc(Corrispettivi, ipAddress, map);

			EsitoOperazioneType esito = new EsitoOperazioneType();
			esito.setIdOperazione(String.valueOf(num));
			esito.setVersione("1.0");
			Beep.tone(1000, 300, ipAddress);
			
			if(pair.getSecond()){
				InputStream is = APIProveHWImpl.class.getClassLoader().getResourceAsStream("response.xml");
				String text = IOUtils.toString(is, StandardCharsets.UTF_8.name());
				return text;
			}else{
				InputStream is = APIProveHWImpl.class.getClassLoader().getResourceAsStream("response.err.firma.xml");
				String text = IOUtils.toString(is, StandardCharsets.UTF_8.name());
				return text;
			}
			// return "<?xml version=\"1.0\" encoding=\"UTF-8\"
			// standalone=\"yes\"?><EsitoOperazione
			// xmlns=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0\"
			// versione=\"1.0\"><IdOperazione>"+num+"</IdOperazione></EsitoOperazione>";



		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}
		try{
			InputStream is = APIProveHWImpl.class.getClassLoader().getResourceAsStream("response.xml");
			String text = IOUtils.toString(is, StandardCharsets.UTF_8.name());
			return text;
		} catch (Exception e) {
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
		if (map.containsKey(key)) {
			RT rt = map.get(key);
			Date oldtime =  rt.getWorkingtime();
			int diff = (int) ((now.getTime() - oldtime.getTime()) / 1000);
			rt.setTimediff(diff);
			rt.setWorkingtime(now);
			log.info("diff_time: " + diff);
		} else {
			RT rt = new RT(key,now);
			map.put(key, rt);
			log.info("diff_time: " + 0);
		}

	}

	private int aggiornaricevuti(String key) {
		if (map.containsKey(key)) {
			RT rt = map.get(key);
			int res = rt.getKricevuti() + 1;
			log.info("totale ricevuti da " + key + ": " + res);
			rt.setKricevuti(res);
			return res;
		} /*else {
			ricevuti.put(key, 1);
			log.info("totale ricevuti da " + key + ": 1");
			return 1;
		}*/
		return 1;
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

	private Pair<String,Boolean> getMatricola(DatiCorrispettiviType d, String corri) {
		String matricola = "";
		boolean validFlag = false;
		if (d.getSignature() != null) {
			byte[] certificate = d.getSignature().getKeyInfo().getX509Data().getX509Certificate();
			CertificateFactory fact = null;
			try {
				fact = CertificateFactory.getInstance("X.509");

				X509Certificate cert = (X509Certificate) fact
						.generateCertificate(new ByteArrayInputStream(certificate));

				PublicKey publicKey = cert.getPublicKey();

				Document doc = convertStringToDocument(corri);// marshallToDocument(d,DatiCorrispettiviType.class);

				NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");

				if (nl.getLength() == 0) {
					throw new Exception("Cannot find Signature element");
				}

				DOMValidateContext valContext = new DOMValidateContext(publicKey, nl.item(0));

				XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

				XMLSignature signature = fac.unmarshalXMLSignature(valContext);

				validFlag = signature.validate(valContext);

				// Check core validation status.
				if (validFlag == false) {
					Beep.tone(1000, 300,1600);
					Beep.tone(1000, 300,1600);
					Beep.tone(1000, 300,1600);
					System.err.println("Signature failed core validation");
					boolean sv = signature.getSignatureValue().validate(valContext);
					System.out.println("signature validation status: " + sv);
					if (sv == false) {
						// Check the validation status of each Reference.
						Iterator i = signature.getSignedInfo().getReferences().iterator();
						for (int j = 0; i.hasNext(); j++) {
							boolean refValid = ((Reference) i.next()).validate(valContext);
							System.out.println("ref[" + j + "] validity status: " + refValid);
						}
					}
				} else {
					System.out.println("Signature passed core validation");
				}

				Principal principal = cert.getSubjectDN();
				String name = principal.getName();
				matricola = name.substring(3, 14);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		Pair <String,Boolean> pair = new Pair<>();
		pair.setFirst(matricola);
		pair.setSecond(validFlag);
		return pair;
	}

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
