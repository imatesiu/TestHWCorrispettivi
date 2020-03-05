package isti.cnr.sse.rest.impl.dispositivi;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URISyntaxException;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;
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

import cnr.isti.sse.data.corrispettivi.messaggi.AttivaDispositivoType;
import cnr.isti.sse.data.corrispettivi.messaggi.EsitoOperazioneType;
import cnr.isti.sse.data.corrispettivi.messaggi.RichiestaCertificatoDispositivoType;




public class ApiDispositiviTest extends JerseyTest {

	@Override
	protected TestContainerFactory getTestContainerFactory() {
		return new GrizzlyWebTestContainerFactory();
	}

	@Override
	protected DeploymentContext configureDeployment() {
		forceSet(TestProperties.CONTAINER_PORT, "0");
		return ServletDeploymentContext.forServlet(new ServletContainer(new ResourceConfig(APIDispositiviImpl.class)))
				.build();

	}

	@Override
	protected Application configure() {
		return new ResourceConfig(ApiDispositiviTest.class);
	}

	@Test
	public void test() throws JAXBException, IOException, URISyntaxException {
		String nameFilexml = "attivazione.xml";//
		runTest(nameFilexml);
	}
	private void runTest(String nameFilexml) throws JAXBException, IOException, URISyntaxException {
		
		
		InputStream is = ApiDispositiviTest.class.getClassLoader().getResourceAsStream(nameFilexml);
		assertNotNull(is);
		JAXBContext jaxbContexti = JAXBContext.newInstance(AttivaDispositivoType.class);

		Unmarshaller jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
		AttivaDispositivoType collaborativeContentInput = (AttivaDispositivoType) jaxbUnmarshaller1.unmarshal(is);
		//getMatricola(collaborativeContentInput);
		Entity<AttivaDispositivoType> entity = Entity.entity(collaborativeContentInput, MediaType.APPLICATION_XML);


		File f = FileUtils.toFile( ApiDispositiviTest.class.getClassLoader().getResource(nameFilexml));
		InputStream content = new FileInputStream(f);
		final String read = ReaderWriter.readFromAsString(content, MediaType.APPLICATION_XML_TYPE);

		final Entity<String> rex = Entity.entity(read, MediaType.APPLICATION_XML_TYPE);


		Response response = target("/").request(MediaType.APPLICATION_XML).put(rex);



		String res2 = response.readEntity(new GenericType<String>() {
		});
		/*JAXBContext jaxbContext = JAXBContext.newInstance(EsitoOperazioneType.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(res2);
		EsitoOperazioneType esito = (EsitoOperazioneType) unmarshaller.unmarshal(reader);
		byte[] certificate = esito.getSignature().getKeyInfo().getX509Data().getX509Certificate();


		//EsitoOperazioneType res = response.readEntity(new GenericType<EsitoOperazioneType>() {});
		JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(EsitoOperazioneType.class);
		Marshaller marshaller = jaxbCtx.createMarshaller();
		marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); // NOI18N
		marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		marshaller.marshal( esito, System.out );
		*/
		assertNotNull(response);
	}
	
	@Test
	public void testCensimento() throws JAXBException, IOException, URISyntaxException {
		String nameFilexml = "cens.xml";//
		runTestCens(nameFilexml);
	}
	
private void runTestCens(String nameFilexml) throws JAXBException, IOException, URISyntaxException {
		
		
		InputStream is = ApiDispositiviTest.class.getClassLoader().getResourceAsStream(nameFilexml);
		assertNotNull(is);
		JAXBContext jaxbContexti = JAXBContext.newInstance(RichiestaCertificatoDispositivoType.class);

		Unmarshaller jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
		RichiestaCertificatoDispositivoType collaborativeContentInput = (RichiestaCertificatoDispositivoType) jaxbUnmarshaller1.unmarshal(is);
		//getMatricola(collaborativeContentInput);
		Entity<RichiestaCertificatoDispositivoType> entity = Entity.entity(collaborativeContentInput, MediaType.APPLICATION_XML);


		File f = FileUtils.toFile( ApiDispositiviTest.class.getClassLoader().getResource(nameFilexml));
		InputStream content = new FileInputStream(f);
		final String read = ReaderWriter.readFromAsString(content, MediaType.APPLICATION_XML_TYPE);

		final Entity<String> rex = Entity.entity(read, MediaType.APPLICATION_XML_TYPE);


		Response response = target("/").request(MediaType.APPLICATION_XML).post(rex);



		String res2 = response.readEntity(new GenericType<String>() {
		});
		/*JAXBContext jaxbContext = JAXBContext.newInstance(EsitoOperazioneType.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		StringReader reader = new StringReader(res2);
		EsitoOperazioneType esito = (EsitoOperazioneType) unmarshaller.unmarshal(reader);
		byte[] certificate = esito.getSignature().getKeyInfo().getX509Data().getX509Certificate();


		//EsitoOperazioneType res = response.readEntity(new GenericType<EsitoOperazioneType>() {});
		JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(EsitoOperazioneType.class);
		Marshaller marshaller = jaxbCtx.createMarshaller();
		marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); // NOI18N
		marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		marshaller.marshal( esito, System.out );
		
		assertNotNull(response);*/
	}

}
