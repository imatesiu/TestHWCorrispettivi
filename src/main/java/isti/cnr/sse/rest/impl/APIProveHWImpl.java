package isti.cnr.sse.rest.impl;




import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.utils.Pair;

import cnr.isti.sse.data.corrispettivi.DatiCorrispettiviType;
import cnr.isti.sse.data.corrispettivi.messaggi.EsitoOperazioneType;



@Consumes(MediaType.APPLICATION_XML)
//@Produces(MediaType.APPLICATION_XML)
@Path("/corrispettivi")
public class APIProveHWImpl {

	//@Inject 
	//TokenPersistence em;


	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(APIProveHWImpl.class);

	private static Map<String, BigDecimal> map = new HashMap<>();
	
	private static Map<String, Pair<Integer,Date>> timediff = new HashMap<>();

	private static Map<String, Integer> ricevuti = new HashMap<>();


	@Path("/clearall")
	@GET
	public String clearall(){
		
		map = new HashMap<>();
		ricevuti = new HashMap<>();
		timediff = new HashMap<>();
		log.info("Clear All\n\r");
		return "<html><body>OK</body></html>";
	}

	@Path("/clear/{key:.*}")
	@GET
	public String clear(@PathParam("key") String key){
		if(map.containsKey(key)){
			map.put(key, new BigDecimal(0));
			ricevuti.put(key, 0);
			timediff.put(key, new Pair<>(0, new Date()));
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
			timediff.put(key, new Pair<>(0, new Date()));
			log.info("Init: "+key);
			log.info("Grantotale "+grantotale);
			log.info("");
			return "<html><body>OK,  Init: "+key+" Grantotale "+grantotale+"</body></html>";
		}else{
			map.put(key, new BigDecimal(grantotale));
			ricevuti.put(key, 0);
			timediff.put(key, new Pair<>(0, new Date()));
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
			Integer  diff = timediff.get(key).getFirst();
		//	map.put(key, new BigDecimal(0));
			//ricevuti.put(key, 0);
			log.info("Info for: "+key);
			log.info("Grantotale "+grantotale);
			log.info("Ricevuti in totale: "+num);
			log.info("TimeDiff: "+diff);
			log.info("");
			 return "<html><body>OK,  Info for: "+key+" Grantotale: "+grantotale+" Ricevuti in totale: "+num+" tempo in sencodi:"+diff+" </body></html>";
		}else{
			return "<html><body>Elemento non presente, "+key+"</body></html>";
		}
			
	}
	

	@Path("/")
	@POST
	public EsitoOperazioneType putListMisuratoriFiscale(DatiCorrispettiviType Corrispettivi, @Context HttpServletRequest request){
		Date now = new Date();
		String timeStamp = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(now);
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		log.info("received form: " +ipAddress+ " "+timeStamp);
		try{


			//is client behind something?
			aggiornadiff(now,ipAddress);
			
			
			int num = aggiornaricevuti(ipAddress);
			Utility.writeTo(Corrispettivi, ipAddress, num);
			Utility.calc(Corrispettivi, ipAddress, map);
			
			EsitoOperazioneType esito = new EsitoOperazioneType();
			esito.setIdOperazione(String.valueOf(num));
			esito.setVersione("1.0"); 
			Beep.tone(1000, 300);  
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

	

	private void aggiornadiff(Date now,  String key) {
		if(timediff.containsKey(key)){
			Date oldtime = (Date) timediff.get(key).getSecond();
			int diff = (int)((now.getTime()-oldtime.getTime()) / 1000);
			timediff.put(key, new Pair<>(diff,now));
			log.info("diff_time: "+diff);
		}else{
			timediff.put(key, new Pair<>(0,now));
			log.info("diff_time: "+0);
		}
		
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



}
