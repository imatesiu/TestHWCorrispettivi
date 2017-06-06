package isti.cnr.sse.rest.impl;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import cnr.isti.sse.data.corrispettivi.DatiCorrispettiviType;
import cnr.isti.sse.data.corrispettivi.messaggi.EsitoOperazioneType;
import cnr.isti.sse.data.send.dataProve;


@Consumes(MediaType.APPLICATION_XML)
//@Produces(MediaType.APPLICATION_XML)
@Path("/corrispettivi")
public class APIProveHWImpl {

	//@Inject 
	//TokenPersistence em;


	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(APIProveHWImpl.class);

	//private static Map<String, BigDecimal> map = new HashMap<>();

	//private static Map<String, Pair<Integer,Date>> timediff = new HashMap<>();

	private static Map<String, Integer> ricevuti = new HashMap<>();


	private static Map<String, dataProve> data;// = new HashMap<>();

	
	public APIProveHWImpl(){
		if(data==null){
			data = new HashMap<>();
			loadconfig();
		}
	}


	@Path("/clearall")
	@GET
	public String clearall(){

		//map = new HashMap<>();
		ricevuti = new HashMap<>();
		//timediff = new HashMap<>();
		data = new HashMap<>();
		
		Sender.sendClearAll();
		
		log.info("Clear All\n\r");
		return "<html><body>OK</body></html>";
	}

	@Path("/clear/{key:.*}")
	@GET
	public String clear(@PathParam("key") String key){
		if(data.containsKey(key)){
			//map.put(key, new BigDecimal(0));
			ricevuti.put(key, 0);
			//	timediff.put(key, new Pair<>(0, new Date()));
			dataProve d = data.get(key);
			d.init();
			data.put(key, d);
			log.info("Clear "+key);
			
			Sender.sendClear(key);
			
			return "<html><body>OK</body></html>";
		}else 
			return "<html><body>Elemento non presente</body></html>";
	}

	@Path("/init/{key:.*}")
	@GET
	public String init(@PathParam("key") String key, @QueryParam("grantot") int grantotale){

		Sender.sendGrantotale(key, grantotale);

		if(data.containsKey(key)){
			//map.put(key, new BigDecimal(grantotale));

			ricevuti.put(key, 0);
			//	timediff.put(key, new Pair<>(0, new Date()));

			dataProve d = data.get(key);
			d.init();
			d.setGrantotale(new BigDecimal(grantotale));
			data.put(key, d);

			log.info("Init: "+key);
			log.info("Grantotale "+grantotale);
			log.info("");
			return "<html><body>OK,  Init: "+key+" Grantotale "+grantotale+"</body></html>";
		}else{
			//map.put(key, new BigDecimal(grantotale));
			ricevuti.put(key, 0);
			//	timediff.put(key, new Pair<>(0, new Date()));
			dataProve d = new dataProve(key,new BigDecimal(grantotale), 0,0);
			d.setOldtime(new Date());
			data.put(key, d);

			log.info("Init "+key);
			log.info("Grantotale "+grantotale);
			return "<html><body>Elemento non presente, creato Init: "+key+" Grantotale: "+grantotale+"</body></html>";
		}

	}

	@Path("/info/{key:.*}")
	@GET
	public String info(@PathParam("key") String key){
		if(data.containsKey(key)){
			BigDecimal grantotale = data.get(key).getGrantotale();
			int num = ricevuti.get(key);
			Integer  diff = data.get(key).getDifftime();//timediff.get(key).getFirst();
			//	map.put(key, new BigDecimal(0));
			//ricevuti.put(key, 0);
			log.info("Info for: "+key);
			log.info("Grantotale "+grantotale);
			log.info("Ricevuti in totale: "+num);
			log.info("TimeDiff: "+diff);
			log.info("");
			return "<html><body>OK,  Info for: "+key+" Grantotale: "+grantotale+" Ricevuti in totale: "+num+"</body></html>";
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
			Utility.calc(Corrispettivi, ipAddress, data);

			EsitoOperazioneType esito = new EsitoOperazioneType();
			esito.setIdOperazione(String.valueOf(num));
			esito.setVersione("1.0");
			
			
			
			Sender.sendDatiCorrispettivi(Corrispettivi, ipAddress);
			
			
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




	private void loadconfig()  {

		try{
			Gson g = new Gson();
			JsonReader reader = new JsonReader(new FileReader("config.json"));
			Type listType = new TypeToken<ArrayList<dataProve>>(){}.getType();
			List<dataProve> Listmf = g.fromJson(reader, listType);

			for(dataProve d : Listmf){
				String key = d.getIpAddress();
				data.put(key, d);
				
			}

			Sender.sendconfig(Listmf);
			
			
			
			
			
			
		}catch (FileNotFoundException e) {
			log.error(e);
		}
		

	

	}

	private void aggiornadiff(Date now,  String key) {

		if(data.containsKey(key)){
			Date oldtime = data.get(key).getOldtime();
			int diff = (int)((now.getTime()-oldtime.getTime()) / 1000);
			data.get(key).setDifftime(diff);
			data.get(key).setOldtime(now);
			//	timediff.put(key, new Pair<>(diff,now));
			log.info("diff_time: "+diff);
		}else{
			//map.put(key, new BigDecimal(grantotale));

			//	timediff.put(key, new Pair<>(0, new Date()));
			dataProve d = new dataProve(key,new BigDecimal(0), 0, 0);
			d.setOldtime(new Date());
			data.put(key, d);

		}

	}

	private int aggiornaricevuti(String key) {
		if(ricevuti.containsKey(key)){
			int res = ricevuti.get(key) + 1;
			log.info("totale ricevuti da "+key+": "+res);
			data.get(key).setNuminvii(res);
			ricevuti.put(key, res );
			return res;
		}else{
			ricevuti.put(key, 1 );
			log.info("totale ricevuti da "+key+": 1");
			data.get(key).setNuminvii(1);
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



	@Path("/jinfo/")
	@GET
	public String jinfo(){
		Collection<dataProve> c = data.values();


		Gson g = new Gson();
		//List<Prova> Listprove = g.fromJson(MF, listType);
		return g.toJson(c);


	}


	@Path("/jinit/")
	@POST
	public void  receive_info(String sdata){



		Gson g = new Gson();

		Type listType = new TypeToken<ArrayList<dataProve>>(){}.getType();
		List<dataProve> Listprove = g.fromJson(sdata, listType);

		for(dataProve d : Listprove){
			data.put(d.getIpAddress(),d);
			ricevuti.put(d.getIpAddress(), d.getNuminvii());
		}


	}







}
