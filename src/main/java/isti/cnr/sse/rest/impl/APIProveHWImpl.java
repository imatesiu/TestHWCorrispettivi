package isti.cnr.sse.rest.impl;

import java.util.HashMap;
import java.util.Map;

//import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;




public class APIProveHWImpl {
	
	//@Inject 
	//TokenPersistence em;

	private static Map<String,String> map = new HashMap<String,String>();

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(APIProveHWImpl.class);


	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	@Path("/dispositivi/corrispettivi/")
	@POST
	public String putListMisuratoriFiscale(@FormParam("sendmisuratori") String Corrispettivi){
		
		log.info("received");
		return "OK";
	}
	

	


}
