package isti.cnr.sse.rest.impl;

import static org.junit.Assert.*;

import java.io.InputStream;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
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
		
		
		String nameFilexml = "corrispettivo.xml";
		InputStream is = APIProveHWImplTest.class.getClassLoader().getResourceAsStream(nameFilexml );
		assertNotNull(is);
		JAXBContext jaxbContexti = JAXBContext.newInstance(DatiCorrispettiviType.class);

		Unmarshaller jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
		DatiCorrispettiviType collaborativeContentInput = (DatiCorrispettiviType) jaxbUnmarshaller1.unmarshal(is);

		Entity<DatiCorrispettiviType> entity = Entity.entity(collaborativeContentInput,MediaType.APPLICATION_XML);
		Response response =  target("/dispositivi/corrispettivi").request(MediaType.APPLICATION_XML).post(entity);

		String id = response.readEntity(String.class);

		assertNotNull(response);
		
		
		
		
		
	}

}
