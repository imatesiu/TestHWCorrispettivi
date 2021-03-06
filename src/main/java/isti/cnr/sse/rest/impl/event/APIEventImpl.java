package isti.cnr.sse.rest.impl.event;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.glassfish.grizzly.utils.Pair;

import cnr.isti.sse.data.corrispettivi.messaggi.DettaglioEventoDispositivoType;
import cnr.isti.sse.data.corrispettivi.messaggi.EventoDispositivoType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import isti.cnr.sse.jsf.SendRest;
import isti.cnr.sse.rest.impl.APIProveHWImpl;
import isti.cnr.sse.rest.impl.error.ErrorHttp;
import isti.cnr.sse.rest.util.Utility;

@Consumes(MediaType.APPLICATION_XML)
//@Produces(MediaType.APPLICATION_XML)
@Path("/evento")
public class APIEventImpl {

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(APIEventImpl.class);

	private static Integer ErrorType = 9999;

	private static ErrorHttp flag = ErrorHttp.Null;

	@RequestBody(content = @Content(
			mediaType = "application/xml",
			schema = @Schema(implementation = EventoDispositivoType.class)
			),
	description = "." )
	@Path("/")
	@PUT
	public String putEventMisuratoriFiscale2(String event, @Context HttpServletRequest request, @Context HttpServletResponse response)
			throws JAXBException {// DatiCorrispettiviType Corrispettivi,
		// @Context HttpServletRequest request){
		response.setHeader("Connection", "Close");
		log.info("***************Evento***************");
		JAXBContext jaxbContext = JAXBContext.newInstance(EventoDispositivoType.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Utility.validateXmlMessaggi(unmarshaller);
		
		if(event.length()==0 | (!flag.equals(ErrorHttp.Null))){
			try{
				InputStream is = APIProveHWImpl.class.getClassLoader().getResourceAsStream("response.err.tracciato.xml");
				String text = IOUtils.toString(is, StandardCharsets.UTF_8.name());
				if(ErrorType!=9999) {
					text = Utility.getResource(ErrorType);
					
				}	
				throw new WebApplicationException(Response.status(406).entity(text).build());
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
			
		try {
			StringReader reader = new StringReader(event);
			EventoDispositivoType EventoDispositivo = (EventoDispositivoType) unmarshaller.unmarshal(reader);
			
			Date now = new Date();
			String timeStamp = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(now);
			Pair<String, Boolean> pair = Utility.getMatricola(EventoDispositivo, event);
			String ipAddress = pair.getFirst();
			if (ipAddress == null) {
				ipAddress = request.getHeader("X-FORWARDED-FOR");
			}

			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}
			Utility.writeTo(event, ipAddress, 0);
			log.info("received form: " + ipAddress + " " + timeStamp);
			DettaglioEventoDispositivoType dettaglio = EventoDispositivo.getDettaglio();
			log.info("Evento Codice: " +dettaglio.getCodice());
			log.info("Evento Descrizione: " +dettaglio.getDescrizione());
			log.info("Evento: " +EventoDispositivo.getEvento());
			log.info("************************************");
			if(dettaglio.getCodice()=="603" | dettaglio.getCodice()=="604") {
			Pair<String, Integer> res = sendAdE_event(event, ipAddress);
			String responseAsString = res.getFirst();
			Integer status = res.getSecond();
			throw new WebApplicationException(Response.status(status).entity(responseAsString).build());
			//return Response.status(status).entity(responseAsString).build();
			}
			
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
	
	
	@Path("/setxml/{key:.*}")
	@GET
	public String setXml(@PathParam("key") Integer key) {
		
			if(key!=null)
				ErrorType = key;
			else
				ErrorType = 9999;
			
			return "<html><body>OK, "+ErrorType+"</body></html>";
		

	}
	
	@Path("/set/{key:.*}")
	@GET
	public String fuoriorario(@PathParam("key") String key) {
		
			
			flag = ErrorHttp.get(key);
			
			return "<html><body>OK, "+flag+"</body></html>";
		

	}
	
	private Pair<String,Integer> sendAdE_event(String stringa, String ipAddress) {
		try {
			Integer Status = new Integer(200);
			Client client = ClientBuilder.newClient();
			String url = "https://v-apid-ivaservizi.agenziaentrate.gov.it";
			SendRest r = new SendRest();
			
			Response response = r.SendPut(url, "/v1/dispositivi/evento/",  stringa);
				

			
			Status = response.getStatus();

			String responseAsString = response.readEntity(String.class);
			if(responseAsString==null) {
					responseAsString = String.valueOf(response.getStatus());
					
			}
			if(responseAsString.length()==0) {
				responseAsString = String.valueOf(response.getStatus());
			}
			Utility.writeTo(responseAsString, "Resp_"+ipAddress+"_ADE", 0);
			return new Pair<String, Integer>(responseAsString,Status);
			/*response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", url);
			response.sendRedirect(url);*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}
		/*try{
			InputStream is = APIProveHWImpl.class.getClassLoader().getResourceAsStream("response.err.tracciato.xml");
			String text = IOUtils.toString(is, StandardCharsets.UTF_8.name());
			throw new WebApplicationException(Response.status(406).entity(text).build());
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		}*/
		return null;
	}
	
	
}
