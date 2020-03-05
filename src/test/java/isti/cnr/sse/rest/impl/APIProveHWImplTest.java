package isti.cnr.sse.rest.impl;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.Iterator;


import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import org.glassfish.jersey.message.internal.ReaderWriter;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.ServletDeploymentContext;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import cnr.isti.sse.data.corrispettivi.DatiCorrispettiviType;
import cnr.isti.sse.data.corrispettivi.messaggi.EsitoOperazioneType;
import isti.cnr.sse.jsf.TipoProve;
import isti.cnr.sse.rest.impl.firma.CreateError;
import isti.cnr.sse.rest.util.Beep;

public class APIProveHWImplTest extends JerseyTest {

	@Override
	protected TestContainerFactory getTestContainerFactory() {
		return new GrizzlyWebTestContainerFactory();
	}

	@Override
	protected DeploymentContext configureDeployment() {
		forceSet(TestProperties.CONTAINER_PORT, "0");
		return ServletDeploymentContext.forServlet(new ServletContainer(new ResourceConfig(APIProveHWImpl.class)))
				.build();

	}

	@Override
	protected Application configure() {
		return new ResourceConfig(APIProveHWImpl.class);
	}

	@Test
	public void test() throws JAXBException, IOException, URISyntaxException {
		
		
		//CreateError err = new CreateError();
		//err.Evento();
		
		//
		
		//err.Corrispettivi();
		//err.Lotteria();
		//err.Lotteria();
		//err.Censimento();
		//err.Evento();
		//err.Evento();
		//err.Attivazione();

		

		//sendinit();sendrtall();
		// for(int i = 0 ; i<10; i++){
	//String nameFilexml = "CC/RT_192.168.1.133_13_04_2017__15_54_46_16.xml";//
		String nameFilexml = "rt_lug_2020.xml";//

		runTest(nameFilexml);
		sendgetinfo();sendgetstop();
		sendgetclear();

	/*	nameFilexml = "CC/c1.xml";
		runTest(nameFilexml);

 	nameFilexml = "CC/c1.xml";
		runTest(nameFilexml);
				nameFilexml = "CC/c2.xml";
		runTest(nameFilexml);
		nameFilexml = "test_corrispettivi.xml";
		runTest(nameFilexml);

		nameFilexml = "CC/RT_192.168.1.166_07_04_2017__10_16_26_3.xml";
		runTest(nameFilexml);

		nameFilexml = "CC/RT_192.168.1.166_07_04_2017__10_16_45_4.xml";
		runTest(nameFilexml);

		nameFilexml = "CC/RT_192.168.1.166_07_04_2017__10_17_04_5.xml";
		runTest(nameFilexml);

		nameFilexml = "CC/RT_192.168.1.166_07_04_2017__10_17_24_6.xml";
		runTest(nameFilexml);*/
		//sendgetinfo();
		/*
		 * nameFilexml = "corrispettivo.xml"; runTest(nameFilexml); if(i==8){
		 * sendgetclear(); }
		 */

		// }

	}

	private void sendgetclear() {
		Response response = target("/corrispettivi/clear/127.0.0.1").request(MediaType.APPLICATION_XML).get();
	}
	
	private void sendgetstop() {
		Response response = target("/corrispettivi/stop/80M08002493").request(MediaType.APPLICATION_XML).get();
	}
	
	private void sendinit() {
		Response response = target("/corrispettivi/init/80M08002493").queryParam("grantot", "28.8").queryParam("desc",TipoProve.Termiche.toString()).queryParam("z","33").request(MediaType.APPLICATION_XML).get();
		System.out.println(response);
	}
	
	private void sendrt() {
		Response response = target("/corrispettivi/rt/80M08002493").request(MediaType.APPLICATION_XML).get();
		System.out.println(response);
	}
	
	private void sendrtall() {
		Response response = target("/corrispettivi/allrt/").request(MediaType.APPLICATION_XML).get();
		System.out.println(response);
		Collection<RT> res = response.readEntity(new GenericType<Collection<RT>>() {
		});
		System.out.println(res);
	}

	private void sendgetinfo() {
		Response response = target("/corrispettivi/info/127.0.0.1").request(MediaType.APPLICATION_XML).get();
	}

	private void runTest(String nameFilexml) throws JAXBException, IOException, URISyntaxException {
		
		//String nameFilexmlresp = "resp.txt";//
		//InputStream is2 = APIProveHWImplTest.class.getClassLoader().getResourceAsStream(nameFilexmlresp);
		
	//	JAXBContext jaxbContextr = JAXBContext.newInstance(EsitoOperazioneType.class);
	//	Unmarshaller jaxbUnmarshaller12 = jaxbContextr.createUnmarshaller();
	//	EsitoOperazioneType collaborativeContentInput2 = (EsitoOperazioneType) jaxbUnmarshaller12.unmarshal(is2);
	//	byte[] certificate2 = collaborativeContentInput2.getSignature().getKeyInfo().getX509Data().getX509Certificate();
		
	//	String theString2 = IOUtils.toString(new FileInputStream(new File(APIProveHWImplTest.class.getClassLoader().getResource(nameFilexmlresp).toURI())), "UTF-8");
		
	//	statusCertificateAndSignature(certificate2,theString2);
		
		InputStream is = APIProveHWImplTest.class.getClassLoader().getResourceAsStream(nameFilexml);
		assertNotNull(is);
		JAXBContext jaxbContexti = JAXBContext.newInstance(DatiCorrispettiviType.class);

		Unmarshaller jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
		DatiCorrispettiviType collaborativeContentInput = (DatiCorrispettiviType) jaxbUnmarshaller1.unmarshal(is);
		//getMatricola(collaborativeContentInput);
		Entity<DatiCorrispettiviType> entity = Entity.entity(collaborativeContentInput, MediaType.APPLICATION_XML);


		File f = FileUtils.toFile( APIProveHWImplTest.class.getClassLoader().getResource(nameFilexml));
		InputStream content = new FileInputStream(f);
		final String read = ReaderWriter.readFromAsString(content, MediaType.APPLICATION_XML_TYPE);

		final Entity<String> rex = Entity.entity(read, MediaType.APPLICATION_XML_TYPE);


		Response response = target("/corrispettivi/").request(MediaType.APPLICATION_XML).post(rex);



		String res2 = response.readEntity(new GenericType<String>() {
		});
		JAXBContext jaxbContext = JAXBContext.newInstance(EsitoOperazioneType.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(res2);
		EsitoOperazioneType esito = (EsitoOperazioneType) unmarshaller.unmarshal(reader);
		byte[] certificate = esito.getSignature().getKeyInfo().getX509Data().getX509Certificate();
		statusCertificateAndSignature(certificate,res2);


		//EsitoOperazioneType res = response.readEntity(new GenericType<EsitoOperazioneType>() {});
		JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(EsitoOperazioneType.class);
		Marshaller marshaller = jaxbCtx.createMarshaller();
		marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); // NOI18N
		marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// marshaller.marshal( res, System.out );

		assertNotNull(response);
	}

	private String getMatricola(DatiCorrispettiviType d) {
		String matricola = null;
		if (d.getSignature() != null) {
			byte[] certificate = d.getSignature().getKeyInfo().getX509Data().getX509Certificate();
			CertificateFactory fact = null;
			try {
				fact = CertificateFactory.getInstance("X.509");

				X509Certificate cer = (X509Certificate) fact.generateCertificate(new ByteArrayInputStream(certificate));
				Principal principal = cer.getSubjectDN();
				String name = principal.getName();
				matricola = name.substring(3, 14);

			} catch (CertificateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return matricola;
	}
	private String stringtoStreaming(InputStream inputStream){

		StringBuilder textBuilder = new StringBuilder();
		try{ 
			try (Reader reader = new BufferedReader(new InputStreamReader
					(inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
				int c = 0;
				while ((c = reader.read()) != -1) {
					textBuilder.append((char) c);
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return textBuilder.toString();
	}


	private boolean statusCertificateAndSignature(byte[] certificate, String Document) {

		//byte[] certificate = d.getSignature().getKeyInfo().getX509Data().getX509Certificate();
		CertificateFactory fact = null;
		try {
			fact = CertificateFactory.getInstance("X.509");

			X509Certificate cert = (X509Certificate) fact
					.generateCertificate(new ByteArrayInputStream(certificate));

			PublicKey publicKey = cert.getPublicKey();

			Document doc = convertStringToDocument(Document);// marshallToDocument(d,DatiCorrispettiviType.class);

			NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");

			if (nl.getLength() == 0) {
				throw new Exception("Cannot find Signature element");
			}

			DOMValidateContext valContext = new DOMValidateContext(publicKey, nl.item(0));

			XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

			XMLSignature signature = fac.unmarshalXMLSignature(valContext);

			boolean validFlag = signature.validate(valContext);

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
			return validFlag;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

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

}
