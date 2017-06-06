package isti.cnr.sse.rest.impl;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


import cnr.isti.sse.data.corrispettivi.DatiCorrispettiviType;

import cnr.isti.sse.data.corrispettivi.messaggi.EsitoOperazioneType;
import cnr.isti.sse.data.send.LoadProperties;


@Consumes(MediaType.APPLICATION_XML)
//@Produces(MediaType.APPLICATION_XML)
@Path("/corrispettivi")
public class APIProveHWImpl {

	//@Inject 
	//TokenPersistence em;


	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(APIProveHWImpl.class);

	private static Map<String, BigDecimal> map = new HashMap<>();

	private static Map<String, Integer> ricevuti = new HashMap<>();


	@Path("/clearall")
	@GET
	public String clearall(){
		
		map = new HashMap<>();
		ricevuti = new HashMap<>();
		log.info("Clear All\n\r");
		return "<html><body>OK</body></html>";
	}

	@Path("/clear/{key:.*}")
	@GET
	public String clear(@PathParam("key") String key){
		if(map.containsKey(key)){
			map.put(key, new BigDecimal(0));
			ricevuti.put(key, 0);
			log.info("Clear "+key);
			return "<html><body>OK</body></html>";
		}else 
			return "<html><body>Elemento non presente</body></html>";
	}
	
	@Path("/init/{key:.*}")
	@GET
	public String init(@PathParam("key") String key, @QueryParam("grantot") int grantotale){
		if(map.containsKey(key)){
			map.put(key, new BigDecimal(grantotale));
			ricevuti.put(key, 0);
			log.info("Init: "+key);
			log.info("Grantotale "+grantotale);
			log.info("");
			return "<html><body>OK,  Init: "+key+" Grantotale "+grantotale+"</body></html>";
		}else{
			map.put(key, new BigDecimal(grantotale));
			ricevuti.put(key, 0);
			log.info("Init "+key);
			log.info("Grantotale "+grantotale);
			return "<html><body>Elemento non presente, creato Init: "+key+" Grantotale: "+grantotale+"</body></html>";
		}
			
	}
	
	@Path("/info/{key:.*}")
	@GET
	public String info(@PathParam("key") String key){
		if(map.containsKey(key)){
			BigDecimal grantotale = map.get(key);
			int num = ricevuti.get(key);
		//	map.put(key, new BigDecimal(0));
			//ricevuti.put(key, 0);
			log.info("Info for: "+key);
			log.info("Grantotale "+grantotale);
			log.info("Ricevuti in totale: "+num);
			log.info("");
			 return "<html><body>OK,  Info for: "+key+" Grantotale: "+grantotale+" Ricevuti in totale: "+num+"</body></html>";
		}else{
			return "<html><body>Elemento non presente, "+key+"</body></html>";
		}
			
	}
	

	@Path("/")
	@POST
	public EsitoOperazioneType putListMisuratoriFiscale(DatiCorrispettiviType Corrispettivi, @Context HttpServletRequest request){
		
		String timeStamp = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(new Date());
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		log.info("received form: " +ipAddress+ " "+timeStamp);
		try{


			//is client behind something?


			
			int num = aggiornaricevuti(ipAddress);
			if(num<=1){
				Properties p = LoadProperties.loadp();
				if(p.getProperty("ip").equals(ipAddress))
					map.put(p.getProperty("ip"), new BigDecimal(p.getProperty("grantotale")));
			}
			Utility.writeTo(Corrispettivi, ipAddress, num);
			Utility.calc(Corrispettivi, ipAddress, map);
			
			EsitoOperazioneType esito = new EsitoOperazioneType();
			esito.setIdOperazione(String.valueOf(num));
			esito.setVersione("1.0");
			return esito;
			//return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><EsitoOperazione xmlns=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0\" versione=\"1.0\"><IdOperazione>"+num+"</IdOperazione></EsitoOperazione>"; 

		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		int x = (int)Math.random() * 10;
		EsitoOperazioneType esito = new EsitoOperazioneType();
		esito.setIdOperazione(String.valueOf(x));
		esito.setVersione("1.0");
		return esito;// "<ns2:EsitoOperazione xmlns:ns2=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0\" versione=\"1.0\"><IdOperazione>0</IdOperazione></ns2:EsitoOperazione>"; 
		

	}

	

	private int aggiornaricevuti(String key) {
		if(ricevuti.containsKey(key)){
			int res = ricevuti.get(key) + 1;
			log.info("totale ricevuti da "+key+": "+res);
			ricevuti.put(key, res );
			return res;
		}else{
			ricevuti.put(key, 1 );
			log.info("totale ricevuti da "+key+": 1");
			return 1;
		}
		
	}

	@Path("/v1")
	@POST
	public String putListMisuratoriFiscale(String Corrispettivi, @Context HttpServletRequest request){


		String timeStamp = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(new Date());
		int x = (int)Math.random() * 10;
		EsitoOperazioneType esito = new EsitoOperazioneType();
		esito.setIdOperazione(String.valueOf(x));
		esito.setVersione("1.0");
		//is client behind something?
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		log.info("received form: " +ipAddress+ " "+timeStamp);
		int num = aggiornaricevuti(ipAddress);
		Utility.writeTo(Corrispettivi, ipAddress, num);
		//
		return "<ns2:EsitoOperazione xmlns:ns2=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0\" versione=\"1.0\"><IdOperazione>"+num+"</IdOperazione></ns2:EsitoOperazione>"; 
		//"<EsitoOperazione versione=\"1.0\"><IdOperazione>0</IdOperazione></EsitoOperazione>";
	}


	
	@Path("/jinfo/{key:.*}")
	@GET
	public String jinfo(@PathParam("key") String key){
		if(map.containsKey(key)){
			BigDecimal grantotale = map.get(key);
			int num = ricevuti.get(key);
		//	map.put(key, new BigDecimal(0));
			//ricevuti.put(key, 0);
			log.info("Info for: "+key);
			log.info("Grantotale "+grantotale);
			log.info("Ricevuti in totale: "+num);
			log.info("");
			 return "<html><body>OK,  Info for: "+key+" Grantotale: "+grantotale+" Ricevuti in totale: "+num+"</body></html>";
		}else{
			return "<html><body>Elemento non presente, "+key+"</body></html>";
		}
			
	}





}
