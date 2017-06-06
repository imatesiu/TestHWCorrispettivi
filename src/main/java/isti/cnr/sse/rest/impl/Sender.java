package isti.cnr.sse.rest.impl;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;


import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import com.google.gson.Gson;

import cnr.isti.sse.data.corrispettivi.DatiCorrispettiviType;
import cnr.isti.sse.data.corrispettivi.messaggi.EsitoOperazioneType;
import cnr.isti.sse.data.send.dataProve;

public final class Sender {
	
	public Sender(){}
	
	public static EsitoOperazioneType sendFile(File f) {
		try{
			JAXBContext jaxbContexti = JAXBContext.newInstance(DatiCorrispettiviType.class);

			Unmarshaller jaxbUnmarshaller1 = jaxbContexti.createUnmarshaller();
			
			DatiCorrispettiviType collaborativeContentInput = (DatiCorrispettiviType) jaxbUnmarshaller1.unmarshal(f);


			Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
			WebTarget webTarget = client.target("http://fmt.isti.cnr.it:8080/TestHWCorrispettivi/dispositivi/").path("corrispettivi");


			Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_XML);
			Response response = invocationBuilder.post(Entity.entity(collaborativeContentInput, MediaType.APPLICATION_XML));


			//String id = response.readEntity(String.class);

			EsitoOperazioneType res =	response.readEntity(new GenericType<EsitoOperazioneType>() {});
			
			return res;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static EsitoOperazioneType sendDatiCorrispettivi(DatiCorrispettiviType collaborativeContentInput, String ipAddress) {
		try{
			


			Client client = ClientBuilder.newClient( new ClientConfig() );
			WebTarget webTarget = client.target("http://fmt.isti.cnr.it:8080/TestHWCorrispettivi/dispositivi/").path("corrispettivi");


			Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_XML).header("X-FORWARDED-FOR", ipAddress);
			Response response = invocationBuilder.post(Entity.entity(collaborativeContentInput, MediaType.APPLICATION_XML));

			EsitoOperazioneType res =	response.readEntity(new GenericType<EsitoOperazioneType>() {});
			
			return res;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static String  sendGrantotale(String key, int grantot) {
		try{
			Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
			WebTarget webTarget = client.target("http://fmt.isti.cnr.it:8080/TestHWCorrispettivi/dispositivi/").path("corrispettivi/init/").path(key).queryParam("grantot", grantot);
			
			Invocation.Builder invocationBuilder =  webTarget.request();
			Response response = invocationBuilder.get();
			
			String res =	response.readEntity(new GenericType<String>() {});
			
			return res;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String  sendClearAll() {
		try{
			Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
			WebTarget webTarget = client.target("http://fmt.isti.cnr.it:8080/TestHWCorrispettivi/dispositivi/").path("corrispettivi/clearall");
			
			Invocation.Builder invocationBuilder =  webTarget.request();
			Response response = invocationBuilder.get();
			
			String res =	response.readEntity(new GenericType<String>() {});
			
			return res;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void sendconfig(List<dataProve> listmf) {
		
		try{
			


			Client client = ClientBuilder.newClient( new ClientConfig().register( LoggingFilter.class ) );
			WebTarget webTarget = client.target("http://fmt.isti.cnr.it:8080/TestHWCorrispettivi/dispositivi/").path("corrispettivi/jinfo");

			Gson g = new Gson();
			
		
			Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
			Response response = invocationBuilder.post(Entity.entity(g.toJson(listmf), MediaType.APPLICATION_JSON));

			
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
