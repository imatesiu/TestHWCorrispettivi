package isti.cnr.sse.rest.impl;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import cnr.isti.sse.data.corrispettivi.DatiCorrispettiviType;
import cnr.isti.sse.data.corrispettivi.DatiRegistratoriTelematiciType;
import cnr.isti.sse.data.corrispettivi.IVAType;
import cnr.isti.sse.data.corrispettivi.messaggi.EsitoOperazioneType;


@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
@Path("/dispositivi/corrispettivi")
public class APIProveHWImpl {

	//@Inject 
	//TokenPersistence em;

	private static Integer num = 1;

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(APIProveHWImpl.class);

	private static Map<String, BigDecimal> map = new HashMap<>();

	private static Map<String, Integer> ricevuti = new HashMap<>();


	@Path("/clearall")
	@GET
	public String clearall(){
		num = 1;
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

	@Path("/")
	@POST
	public String putListMisuratoriFiscale(DatiCorrispettiviType Corrispettivi, @Context HttpServletRequest request){

		String timeStamp = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(new Date());
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = request.getRemoteAddr();
		}
		log.info("received form: " +ipAddress+ " "+timeStamp);
		try{


			//is client behind something?


			writeTo(Corrispettivi, ipAddress);
			calc(Corrispettivi, ipAddress);

		}catch (Exception e) {
			e.printStackTrace();
			log.error(e);
		}

		int x = (int)Math.random() * 10;
		EsitoOperazioneType esito = new EsitoOperazioneType();
		esito.setIdOperazione(String.valueOf(x));
		esito.setVersione("1.0");
		return "<ns2:EsitoOperazione xmlns:ns2=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0\" versione=\"1.0\"><IdOperazione>0</IdOperazione></ns2:EsitoOperazione>"; 
		

	}

	private void calc(DatiCorrispettiviType corrispettivi, String key) {

		aggiornaricevuti(key);
		log.info("Dati di trasmissione: ");
		log.info("Progessivo: "+corrispettivi.getTrasmissione().getProgressivo());

		List<DatiRegistratoriTelematiciType> riepilogo = corrispettivi.getDatiRT().getRiepilogo();
		for (DatiRegistratoriTelematiciType datiRegistratoriTelematici : riepilogo) {
			BigDecimal ammontare = datiRegistratoriTelematici.getAmmontare();
			IVAType iva = datiRegistratoriTelematici.getIVA();
			if(datiRegistratoriTelematici.getTotaleAmmontareResi()!=null)
				if (datiRegistratoriTelematici.getTotaleAmmontareResi().compareTo(new BigDecimal(0))!=0){
					log.error("dovevano esser 0 i resi");
				}
			if(datiRegistratoriTelematici.getTotaleAmmontareAnnulli()!=null)
				if (datiRegistratoriTelematici.getTotaleAmmontareAnnulli().compareTo(new BigDecimal(0))!=0){
					log.error("dovevano esser 0 gli annulli");
				}
			if(iva!=null){
				BigDecimal lordo = ammontare.multiply((iva.getAliquotaIVA().add(new BigDecimal(100)).divide(new BigDecimal(100)))).setScale(2,BigDecimal.ROUND_HALF_DOWN);
				BigDecimal impostaiva = ammontare.multiply(iva.getAliquotaIVA()).divide(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_DOWN);
				if(!impostaiva.equals(iva.getImposta())){
					log.error("imposta Errata!! per imponibile "+ammontare+" aliquota iva "+iva.getAliquotaIVA());	
					log.error("imposta Errata!! mi aspettavo iva "+impostaiva+" trovo "+iva.getImposta());			
				}
				if(lordo.compareTo(new BigDecimal(0))!=0)
					if(map.containsKey(key)){
						BigDecimal old = map.get(key);
						BigDecimal res = old.add(lordo);
						log.info("totale per "+key+": "+res);
						map.put(key, res );
					}else{
						map.put(key, lordo);
						log.info("totale per "+key+": "+lordo);
					}
			}else{
				if(ammontare.compareTo(new BigDecimal(0))!=0)
					if(map.containsKey(key)){
						BigDecimal old = map.get(key);
						BigDecimal res = old.add(ammontare);
						log.info("totale per "+key+": "+res);
						map.put(key, res );
					}else{
						map.put(key, ammontare);
						log.info("totale per "+key+": "+ammontare);
					}
			}
		}

		System.out.println();

	}

	private void aggiornaricevuti(String key) {
		if(ricevuti.containsKey(key)){
			int res = ricevuti.get(key) + 1;
			log.info("totale ricevuti da "+key+": "+res);
			ricevuti.put(key, res );		
		}else{
			ricevuti.put(key, 1 );
			log.info("totale ricevuti da "+key+": 1");
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
		aggiornaricevuti(ipAddress);
		writeTo(Corrispettivi, ipAddress);
		//
		return "<ns2:EsitoOperazione xmlns:ns2=\"http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0\" versione=\"1.0\"><IdOperazione>0</IdOperazione></ns2:EsitoOperazione>"; 
		//"<EsitoOperazione versione=\"1.0\"><IdOperazione>0</IdOperazione></EsitoOperazione>";
	}



	private void writeTo(DatiCorrispettiviType DCT, String ipAddress){
		JAXBContext jaxbCtx;
		try {
			jaxbCtx = javax.xml.bind.JAXBContext.newInstance(DatiCorrispettiviType.class);

			Marshaller marshaller = jaxbCtx.createMarshaller();
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
			marshaller.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			//marshaller.marshal(annotatedCollaborativeContentAnalysis, System.out);
			String timeStamp = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(new Date());
			File theDir = new File("received_"+ipAddress);

			// if the directory does not exist, create it
			if (!theDir.exists()) {
				theDir.mkdir();
			}

			OutputStream os = new FileOutputStream( "received_"+ipAddress+"/RT_"+ipAddress+"_"+timeStamp+"_"+num+".xml" );
			num++;
			marshaller.marshal( DCT, os );

		} catch (JAXBException | FileNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);

		}
	}

	private void writeTo(String DCT, String ipAddress){

		try {


			String timeStamp = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(new Date());
			File theDir = new File("received_"+ipAddress);

			// if the directory does not exist, create it
			if (!theDir.exists()) {
				theDir.mkdir();
			}

			String FILENAME = "received_"+ipAddress+"/RT_"+ipAddress+"_"+timeStamp+"_"+num+".xml";
			BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));
			num++;

			bw.write(DCT);
			bw.close();

		} catch (  IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}
	}





}
