package isti.cnr.sse.rest.impl;

import static org.junit.Assert.*;

import java.io.InputStream;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.test.DeploymentContext;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.ServletDeploymentContext;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.glassfish.jersey.test.spi.TestContainerFactory;
import org.junit.Test;

import cnr.isti.sse.data.corrispettivi.DatiCorrispettiviType;
import cnr.isti.sse.data.corrispettivi.messaggi.EsitoOperazioneType;

public class APIProveHWImplTest extends JerseyTest{

	

	@Override
	protected TestContainerFactory getTestContainerFactory()  {
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
	public void test() throws JAXBException {
		
		//for(int i = 0 ; i<10; i++){
		String nameFilexml = "test_corrispettivi.xml";
		runTest(nameFilexml);
		
		nameFilexml = "CC/RT_192.168.1.166_07_04_2017__10_16_45_4.xml";
		runTest(nameFilexml);
		
		nameFilexml = "CC/RT_192.168.1.166_07_04_2017__10_17_04_5.xml";
		runTest(nameFilexml);
		
		nameFilexml = "CC/RT_192.168.1.166_07_04_2017__10_17_24_6.xml";
		runTest(nameFilexml);
		
		/*nameFilexml = "corrispettivo.xml";
		runTest(nameFilexml);
		if(i==8){
			sendgetclear();
		}*/
		
	//	}
		
		
		
	}
	
	private void sendgetclear() {
		Response response =  target("/dispositivi/corrispettivi/clear/127.0.0.1").request(MediaType.APPLICATION_XML).get();
	}

	private void runTest(String nameFilexml) throws JAXBException{
		InputStream is = APIProveHWImplTest.class.getClassLoader().getResourceAsStream(nameFilexml );
		assertNotNull(is);
		JAXBContext jaxbContexti = JAXBContext.newInstance(DatiCorrispettiviType.class);

		Unmarshaller jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
		DatiCorrispettiviType collaborativeContentInput = (DatiCorrispettiviType) jaxbUnmarshaller1.unmarshal(is);

		Entity<DatiCorrispettiviType> entity = Entity.entity(collaborativeContentInput,MediaType.APPLICATION_XML);
		Response response =  target("/dispositivi/corrispettivi/v1").request(MediaType.APPLICATION_XML).post(entity);

		//String id = response.readEntity(String.class);
		
		EsitoOperazioneType res =	response.readEntity(new GenericType<EsitoOperazioneType>() {});


		JAXBContext jaxbCtx = javax.xml.bind.JAXBContext.newInstance(EsitoOperazioneType.class);

		Marshaller marshaller = jaxbCtx.createMarshaller();
		marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
		marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		
		//marshaller.marshal( res, System.out );
		
		assertNotNull(response);
	}

}
