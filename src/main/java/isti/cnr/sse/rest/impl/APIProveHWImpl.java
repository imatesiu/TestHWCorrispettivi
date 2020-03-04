package isti.cnr.sse.rest.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
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
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.glassfish.grizzly.utils.Pair;

import cnr.isti.sse.data.corrispettivi.DatiCorrispettiviType;
import cnr.isti.sse.data.corrispettivi.messaggi.EsitoOperazioneType;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import isti.cnr.sse.rest.impl.error.ErrorHttp;
import isti.cnr.sse.rest.impl.error.ErrorXML;
import isti.cnr.sse.rest.util.Beep;
import isti.cnr.sse.rest.util.Utility;


@OpenAPIDefinition(info = @Info(title = "APID Service", version = "0.1"), servers = {@Server(url="/v1/dispositivi")
})
@Consumes(MediaType.APPLICATION_XML)
// @Produces(MediaType.APPLICATION_XML)
@Path("/corrispettivi")
public class APIProveHWImpl {

	// @Inject
	// TokenPersistence em;

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(APIProveHWImpl.class);
	
	private static Map<String, RT> map = new HashMap<>(); /*Utility.deserialize();


    @PreDestroy
    public void reset () {
    	Utility.serialize(map);
    }*/

	
	private static ErrorHttp flag = ErrorHttp.Null;
	
	private static Integer ErrorType = 9999;


	@Hidden
	@Path("/clearall")
	@GET
	public String clearall() {
		
		map = new HashMap<>();
		
		log.info("Clear All\n\r");
		return "<html><body>OK</body></html>";
	}
	@Hidden
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
	@Hidden
	@Path("/init/{key}")
	@GET
	public String init(@PathParam("key") String key, @QueryParam("grantot") BigDecimal grantotale, 
			@QueryParam("desc") String desc, @QueryParam("z") int z) {
		if (map.containsKey(key)) {
	
			RT rt = map.get(key);
			rt.setGT((grantotale));
			rt.setZ(z);
			rt.setTotaleRicevuto(new BigDecimal(0));
			rt.setZricevuti(0);
			rt.setStarttime(new Date());
			rt.setDescrizione(desc);
			rt.setProgressivo(z+1);
			rt.setUnCloded();
			log.info("Init: " + key);
			log.info("Grantotale " + grantotale);
			log.info("");
			return "<html><body>OK,  Init: " + key + " Grantotale " + grantotale + "</body></html>";
		} else {
			RT rt = new RT(key, new Date(),grantotale, z+1);
			rt.setDescrizione(desc);
			rt.setZ(z);
			rt.setUnCloded();
			map.put(key,rt);
			log.info("Init " + key);
			log.info("Grantotale " + grantotale);
			return "<html><body>Elemento non presente, creato Init: " + key + " Grantotale: " + grantotale
					+ "</body></html>";
		}

	}
	
	@Path("/set/{key:.*}")
	@GET
	public String fuoriorario( @Parameter(schema = @Schema(implementation = ErrorHttp.class))@PathParam("key") String key) {
		
			
			flag = ErrorHttp.get(key);
			
			return "<html><body>OK, "+flag+"</body></html>";
		

	}
	
	@Path("/setxml/{key:.*}")
	@GET
	public String setXml(@Parameter(schema = @Schema(implementation = ErrorXML.class))@PathParam("key") String key) {
		
		ErrorXML err = ErrorXML.get(key);
			if(key!=null)
				ErrorType = err.getValue();
			else
				ErrorType = 9999;
			
			return "<html><body>OK, "+ErrorType+"</body></html>";
		

	}
	@Hidden
	@Path("/info/{key:.*}")
	@GET
	public String info(@PathParam("key") String key) {
		if (map.containsKey(key)) {
			RT rt = map.get(key);
			BigDecimal grantotale = rt.getGT();
			int num = rt.getZricevuti();
			Integer diff = rt.getTimediff();
			// map.put(key, new BigDecimal(0));
			// ricevuti.put(key, 0);
			log.info("Info for: " + key);
			log.info("Corrispettivi Ricevuti in totale: " + rt.getTotaleRicevuto());
			log.info("Grantotale " + grantotale);
			log.info("Doc. Ricevuti in totale: " + rt.getZ());			
			log.info("Doc. Ricevuti: " + num);
			log.info("TimeDiff: " + diff);
			log.info("");
			return rt.getInfo();
		} else {
			return "<html><body>Elemento non presente, " + key + "</body></html>";
		}

	}

	@Hidden
	@Path("/rt/{key:.*}")
	@GET
	public RT rtinfo(@PathParam("key") String key) {
		if (map.containsKey(key)) {
			RT rt = map.get(key);
			BigDecimal grantotale = rt.getGT();
			int num = rt.getZricevuti();
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
	@Hidden
	@Path("/allrt/")
	@GET
	public Collection<RT> allrtinfo() {
		
			log.info("");
			return map.values();
		

	}
	@Hidden
	@Path("/allrtopen/")
	@GET
	public Collection<RT> allrtopen() {

		log.info("");
		Collection<RT> crt = new ArrayList<>();
		for(RT rt: map.values()){
			if(!rt.isCloded()){
				crt.add(rt);
			}
		}
		return crt;


	}
	@Hidden
	@Path("/stop/{key:.*}")
	@GET
	public String rtstop(@PathParam("key") String key) {
		if (map.containsKey(key)) {
			RT rt = map.get(key);
			BigDecimal grantotale = rt.getGT();
			int num = rt.getZricevuti();
			Integer diff = rt.getTimediff();
			rt.setCloded();
			log.info("Info for: " + key);
			log.info("Grantotale " + grantotale);
			log.info("Ricevuti in totale: " + num);
			log.info("TimeDiff: " + diff);
			log.info("");
			Utility.writeRT(rt);
			return "OK";
		} else {
			return "NonEsiste";
		}

	}
	

	@Path("/")
	@POST
	@ApiResponse(
	        responseCode = "200",
	        content = @Content(
	            mediaType = MediaType.APPLICATION_XML,
	            		schema = @Schema(implementation = EsitoOperazioneType.class)
	        ),
	        description = "."
	    )
	@RequestBody(content = @Content(
			mediaType = MediaType.APPLICATION_XML,
			schema = @Schema(implementation = DatiCorrispettiviType.class)
			),
	description = "." )
	public String putListMisuratoriFiscale2(String Corri, @Context HttpServletRequest request, @Context HttpServletResponse response)
			throws JAXBException {// DatiCorrispettiviType Corrispettivi,
		// @Context HttpServletRequest request){
		response.setHeader("Connection", "Close");
		log.info("****************Corrispettivi********************");
		
		log.info("Message from: "+request.getRemoteAddr());
		
		if(request.getRequestURL().lastIndexOf("https")==-1) {
			log.error("**** CONESSIONE NON SSL ***** ");
			if(request.getRequestURL().indexOf("9090")>-1) {
				log.error("**** USATA PORTA 9090 ***** ");
			}
			log.error("**** *** *** *** ***** ");
		}
		
		JAXBContext jaxbContext = JAXBContext.newInstance(DatiCorrispettiviType.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Utility.validateXmlCorr(unmarshaller);	
		
		if(!flag.equals(ErrorHttp.Null)){
			try{
				InputStream is = APIProveHWImpl.class.getClassLoader().getResourceAsStream("response.err.tracciato.xml");
				String text = IOUtils.toString(is, StandardCharsets.UTF_8.name());
				
				if(ErrorType!=9999) {
					text = Utility.getResource(ErrorType);
					
				}			
				
					throw new WebApplicationException(Response.status(flag.getValue()).entity(text).build());

			} catch (IOException e) {
				e.printStackTrace();
				log.error(e);
			}
			
		}else {
			try{
				if(ErrorType!=9999) {
					String error = Utility.getResource(ErrorType);
					throw new WebApplicationException(Response.status(Status.OK).entity(error).build());

				}
			} catch (IOException e) {
				
				log.error("codice errore sconosciuto");
			}
		}
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
			boolean flagCA = Utility.checkCASpe(Corrispettivi);
			if(!flagCA)
				Utility.checkCAProd(Corrispettivi);
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
			aggiornadiff(now, ipAddress, Corrispettivi.getTrasmissione().getProgressivo());
			Utility.testInfoCorri(Corrispettivi);
			testProgressivo(Corrispettivi, ipAddress, map);

			int num = aggiornaricevuti(ipAddress);
			Utility.writeTo(Corri, ipAddress, num);
			Utility.calc(Corrispettivi, ipAddress, map);

			EsitoOperazioneType esito = new EsitoOperazioneType();
			esito.setIdOperazione(String.valueOf(num));
			esito.setVersione("1.0");
			Beep.tone(1000, 300, ipAddress);

			if(pair.getSecond() & flagCA){
				InputStream is = APIProveHWImpl.class.getClassLoader().getResourceAsStream("response.xml");
				String text = IOUtils.toString(is, StandardCharsets.UTF_8.name());
				return text;
			}else{
				InputStream is = APIProveHWImpl.class.getClassLoader().getResourceAsStream("response.err.firma.xml");
				String text = IOUtils.toString(is, StandardCharsets.UTF_8.name());
				throw new WebApplicationException(Response.status(406).entity(text).build());
			}
			// return "<?xml version=\"1.0\" encoding=\"UTF-8\"
			// standalone=\"yes\"?><EsitoOperazione
			// xmlns=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0\"
			// versione=\"1.0\"><IdOperazione>"+num+"</IdOperazione></EsitoOperazione>";



		} catch (IOException  | JAXBException e) {
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
	private void aggiornadiff(Date now, String key, long l) {
		if (map.containsKey(key)) {
			RT rt = map.get(key);
			Date oldtime =  rt.getWorkingtime();
			int diff = (int) ((now.getTime() - oldtime.getTime()) / 1000);
			rt.setTimediff(diff);
			rt.setWorkingtime(now);
			log.info("diff_time: " + diff);
		} else {
			RT rt = new RT(key,now);
			rt.setProgressivo(Long.valueOf(l).intValue());
			map.put(key, rt);
			log.info("diff_time: " + 0);
		}

	}
	
private void testProgressivo(DatiCorrispettiviType corrispettivi, String key, Map<String, RT> map){
		
		if(map.containsKey(key)){
			RT r = map.get(key);
			long progressivoricevuto = corrispettivi.getTrasmissione().getProgressivo();
			Integer progr = Long.valueOf(progressivoricevuto).intValue();
			
			if(!(r.getProgressivo().toString().equals(progr.toString()))){
				Beep.tone(5000, 1000);
				r.setProgressivo(Integer.sum(progr,1));
				log.info("************************ATTENZIONE HAI SALTATO UN PROGRESSIVO*********************");
				log.info("************************ATTENZIONE HAI SALTATO UN PROGRESSIVO*********************");
				log.info("************************ATTENZIONE HAI SALTATO UN PROGRESSIVO*********************");
			}else{
				Integer rprog = Integer.sum(r.getProgressivo(),1);
				r.setProgressivo(rprog);
			}
		}else{
			
		}
		
	}

	private int aggiornaricevuti(String key) {
		if (map.containsKey(key)) {
			RT rt = map.get(key);
			int res = rt.getZricevuti() + 1;
			log.info("totale ricevuti da " + key + ": " + res);
			rt.setZricevuti(res);
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


/*	private Pair<String,Boolean> getMatricola(DatiCorrispettiviType d, String corri) {
		String matricola = null;
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
					log.error("Signature failed core validation");
					boolean sv = signature.getSignatureValue().validate(valContext);
					System.out.println("signature validation status: " + sv);
					if (sv == false) {
						// Check the validation status of each Reference.
						Iterator i = signature.getSignedInfo().getReferences().iterator();
						for (int j = 0; i.hasNext(); j++) {
							boolean refValid = ((Reference) i.next()).validate(valContext);
							System.out.println("ref[" + j + "] validity status: " + refValid);
							if(refValid==true){
								validFlag = true;
							}
						}
					}else{

						validFlag = true;
					}
				} else {
					System.out.println("Signature passed core validation");
				}

				Principal principal = cert.getSubjectDN();
				String name = principal.getName();
				matricola = new String();
				matricola = name.substring(3, 14);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.err.println("Signature failed core validation");
				log.error("FINAL: Signature failed core validation",e);
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
*/
}
