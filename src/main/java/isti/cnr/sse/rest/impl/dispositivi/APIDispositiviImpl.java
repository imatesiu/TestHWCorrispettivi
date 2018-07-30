package isti.cnr.sse.rest.impl.dispositivi;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateCrtKey;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import java.security.cert.CertificateFactory;


import org.apache.commons.io.IOUtils;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;
import org.bouncycastle.crypto.params.RSAPrivateCrtKeyParameters;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.glassfish.grizzly.utils.Pair;
import org.w3c.dom.Document;

import cnr.isti.sse.data.corrispettivi.messaggi.AttivaDispositivoType;
import cnr.isti.sse.data.corrispettivi.messaggi.EsitoRichiestaCertificatoDispositivoType;
import cnr.isti.sse.data.corrispettivi.messaggi.RichiestaCertificatoDispositivoType;
import isti.cnr.sse.rest.impl.APIProveHWImpl;
import isti.cnr.sse.rest.impl.Utility;
import isti.cnr.sse.rest.impl.firma.SignReply;
import sun.misc.BASE64Encoder;

@Consumes(MediaType.APPLICATION_XML)
//@Produces(MediaType.APPLICATION_XML)
@Path("/")
public class APIDispositiviImpl {

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(APIDispositiviImpl.class);
	
	private static String SIGNATURE_ALGORITHM = "SHA256withRSA";


	@POST
	public String postCensimentoRT(String censimento, @Context HttpServletRequest request, @Context HttpServletResponse response)
			throws JAXBException {// DatiCorrispettiviType Corrispettivi,
		// @Context HttpServletRequest request){
		response.setHeader("Connection", "Close");
		JAXBContext jaxbContext = JAXBContext.newInstance(RichiestaCertificatoDispositivoType.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		if(censimento.length()==0){
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
			StringReader reader = new StringReader(censimento);
			
			RichiestaCertificatoDispositivoType CensimentoDispositivo = (RichiestaCertificatoDispositivoType) unmarshaller.unmarshal(reader);
			

			Date now = new Date();
			String timeStamp = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(now);
			Pair<String, Boolean> pair = Utility.getMatricola(CensimentoDispositivo, censimento);
			String ipAddress = pair.getFirst();
			if (ipAddress == null) {
				ipAddress = request.getHeader("X-FORWARDED-FOR");
			}

			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}
			log.info("received form: " + ipAddress + " " + timeStamp);


			
			
			X509Certificate cert = createCertificate(CensimentoDispositivo.getCsr());
			
			String pem = convertToPem(cert);
			
			

			if(true){
				EsitoRichiestaCertificatoDispositivoType esito = new EsitoRichiestaCertificatoDispositivoType();
				int x = (int) Math.random() * 10;
				esito.setIdOperazione(String.valueOf(x));
				esito.setVersione("1.0");
				esito.setCertificato(pem);
				
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		        DocumentBuilder db = dbf.newDocumentBuilder();
		        Document dosigndocument = db.newDocument();
		        
		        // Marshal the Object to a Document
		        JAXBContext jc = JAXBContext.newInstance(EsitoRichiestaCertificatoDispositivoType.class);
		        Marshaller marshaller = jc.createMarshaller();
		        marshaller.marshal(esito, dosigndocument);
		        
				SignReply.Sign(dosigndocument);
				return jaxbObjectToXML(esito);
			}else{
				InputStream is = APIProveHWImpl.class.getClassLoader().getResourceAsStream("response.err.firma.xml");
				String text = IOUtils.toString(is, StandardCharsets.UTF_8.name());
				throw new WebApplicationException(Response.status(406).entity(text).build());
			}


		} catch (IOException  | JAXBException | ParserConfigurationException e) {
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

	}


	@Consumes(MediaType.APPLICATION_XML)
	@PUT
	public String putAttivazioneRT(String attivazione, @Context HttpServletRequest request, @Context HttpServletResponse response)
			throws JAXBException {// DatiCorrispettiviType Corrispettivi,
		response.setHeader("Connection", "Close");

		JAXBContext jaxbContext = JAXBContext.newInstance(AttivaDispositivoType.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		if(attivazione.length()==0){
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
			StringReader reader = new StringReader(attivazione);
			AttivaDispositivoType AttivaDispositivo = (AttivaDispositivoType) unmarshaller.unmarshal(reader);

			Date now = new Date();
			String timeStamp = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(now);
			Pair<String, Boolean> pair = Utility.getMatricola(AttivaDispositivo, attivazione);
			String ipAddress = pair.getFirst();
			if (ipAddress == null) {
				ipAddress = request.getHeader("X-FORWARDED-FOR");
			}

			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}
			log.info("received form: " + ipAddress + " " + timeStamp);

			if(pair.getSecond()){
				InputStream is = APIProveHWImpl.class.getClassLoader().getResourceAsStream("response.xml");
				String text = IOUtils.toString(is, StandardCharsets.UTF_8.name());
				return text;
			}else{
				InputStream is = APIProveHWImpl.class.getClassLoader().getResourceAsStream("response.err.firma.xml");
				String text = IOUtils.toString(is, StandardCharsets.UTF_8.name());
				throw new WebApplicationException(Response.status(406).entity(text).build());
			}


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
	}


	private X509Certificate createCertificate(byte[] Csr){
		try {
			String caFile = "";
			KeyStore caKs = KeyStore.getInstance("JKS");
			
			InputStream is = APIProveHWImpl.class.getClassLoader().getResourceAsStream("jetty-server-ssl.jks");
			caKs.load(is, "jetty8".toCharArray());
			
			Key key = caKs.getKey("server", "jetty8".toCharArray());
	        if (key == null) {
	            throw new RuntimeException("Got null key from keystore!"); 
	        }
	        
	        RSAPrivateCrtKey privKey = (RSAPrivateCrtKey) key;
	        RSAPrivateCrtKeyParameters caPrivateKey = new RSAPrivateCrtKeyParameters(privKey.getModulus(), privKey.getPublicExponent(), privKey.getPrivateExponent(),
	                privKey.getPrimeP(), privKey.getPrimeQ(), privKey.getPrimeExponentP(), privKey.getPrimeExponentQ(), privKey.getCrtCoefficient());
	        // and get the certificate
	        X509Certificate caCert = (X509Certificate) caKs.getCertificate("serverca");
	        if (caCert == null) {
	            throw new RuntimeException("Got null cert from keystore!"); 
	        }
	        caCert.verify(caCert.getPublicKey());
	        
	        Date notBefore = Date.from(LocalDateTime.now()
	                .minusDays(1)
	                .toInstant(ZoneOffset.UTC));

	        Date notAfter = Date.from(LocalDateTime.now()
	                .plusDays(365)
	                .toInstant(ZoneOffset.UTC));

	        // This should be a serial number that the CA keeps track of
	        BigInteger serial = createSerial();

	        // These are the details of the CA
	        X500Name issuer = new X500Name(caCert.getSubjectX500Principal().getName());
	        
	        try {
	        	PKCS10CertificationRequest csrHolder = new PKCS10CertificationRequest(Csr);

	            // Blanket grant the subject as requested in the CSR
	            // A real CA would want to vet this.
	            X500Name subject = csrHolder.getSubject();

	            X509v3CertificateBuilder certificateGenerator = new X509v3CertificateBuilder(
	                    issuer,
	                    serial,
	                    notBefore,
	                    notAfter,
	                    subject,
	                    csrHolder.getSubjectPublicKeyInfo()
	            );

	            JcaX509ExtensionUtils extUtils = new JcaX509ExtensionUtils();
	            certificateGenerator.addExtension(Extension.subjectKeyIdentifier, false,
	            		csrHolder.getSubjectPublicKeyInfo());
	            certificateGenerator.addExtension(Extension.authorityKeyIdentifier, false,
	                    extUtils.createAuthorityKeyIdentifier(caCert));
	            certificateGenerator.addExtension(Extension.basicConstraints, true,
	                    new BasicConstraints(false));

	            ContentSigner contentSigner = createContentSigner(privKey);

	            X509CertificateHolder holder = certificateGenerator.build(contentSigner);
	            CertificateFactory certificateFactory = getCertificateFactory();
	            try (ByteArrayInputStream is2 = new ByteArrayInputStream(holder.toASN1Structure().getEncoded())) {
	                return (X509Certificate) certificateFactory.generateCertificate(is2);
	            }
	        } catch (CertificateException |
	                NoSuchAlgorithmException |
	                NoSuchProviderException |
	                IOException e) {
	            throw new RuntimeException(e);
	        }
	        
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null; 

	}
	
	private static String jaxbObjectToXML(EsitoRichiestaCertificatoDispositivoType customer) {
	    String xmlString = "";
	    try {
	        JAXBContext context = JAXBContext.newInstance(EsitoRichiestaCertificatoDispositivoType.class);
	        Marshaller m = context.createMarshaller();

	        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE); // To format XML
	        StringWriter sw = new StringWriter();
	       
	        m.marshal(customer, sw);
	        xmlString = sw.toString();

	    } catch (JAXBException e) {
	        e.printStackTrace();
	    }

	    return xmlString;
	}
	
	 private BigInteger createSerial() {
	        SecureRandom secureRandom = new SecureRandom();
	        byte[] serialValue = new byte[8];
	        secureRandom.nextBytes(serialValue);
	        return new BigInteger(serialValue);
	    }
	 
	 private CertificateFactory getCertificateFactory() throws CertificateException, NoSuchProviderException {
	        return CertificateFactory.getInstance("X.509");
	    }
	 
	 private ContentSigner createContentSigner(PrivateKey privateKey, String algorithm) {
	        try {
	            JcaContentSignerBuilder csBuilder = new JcaContentSignerBuilder(algorithm);
	                   // .setProvider("CNR");
	            return csBuilder.build(privateKey);
	        } catch (OperatorCreationException e) {
	            throw new RuntimeException(e);
	        }
	    }

	    private ContentSigner createContentSigner(PrivateKey privateKey) {
	        return createContentSigner(privateKey, SIGNATURE_ALGORITHM);
	    }
	    
	    protected static String convertToPem(X509Certificate x509cert)  {
	    	
	    	StringWriter sw = new StringWriter();
	        try {
	           // sw.write("-----BEGIN CERTIFICATE-----\n");
	            sw.write(DatatypeConverter.printBase64Binary(x509cert.getEncoded()).replaceAll("(.{64})", "$1\n"));
	           // sw.write("\n-----END CERTIFICATE-----\n");
	        } catch (CertificateEncodingException e) {
	            e.printStackTrace();
	        }
	        return sw.toString();
	    	
	    	/*
	    	String pemCert = "";
	    	try {
	    		BASE64Encoder  encoder = new BASE64Encoder();
	    		String cert_begin ="";// "-----BEGIN CERTIFICATE-----\n";
	    		String end_cert = "";//"-----END CERTIFICATE-----";

	    		byte[] derCert;

	    		derCert = x509cert.getEncoded();

	    		String pemCertPre = new String(encoder.encode(derCert));
	    		pemCert = cert_begin + pemCertPre + end_cert;
	    		
	    	} catch (java.security.cert.CertificateEncodingException e) {
	    		// TODO Auto-generated catch block
	    		e.printStackTrace();
	    	}
	    	return pemCert;*/
	    }

}
