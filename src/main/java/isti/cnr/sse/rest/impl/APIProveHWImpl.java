package isti.cnr.sse.rest.impl;

import static org.junit.Assert.assertTrue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import cnr.isti.sse.data.corrispettivi.DatiCorrispettiviType;
import cnr.isti.sse.data.corrispettivi.messaggi.EsitoOperazioneType;


@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_XML)
@Path("/dispositivi/corrispettivi")
public class APIProveHWImpl {
	
	//@Inject 
	//TokenPersistence em;

	private static Integer num = 1;

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(APIProveHWImpl.class);

	


	@Path("/v1")
	@POST
	public EsitoOperazioneType putListMisuratoriFiscale(DatiCorrispettiviType Corrispettivi, @Context HttpServletRequest request){
		
		
		String timeStamp = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(new Date());
		log.info("received: "+timeStamp);
		int x = (int)Math.random() * 10;
		EsitoOperazioneType esito = new EsitoOperazioneType();
		esito.setIdOperazione(String.valueOf(x));
		esito.setVersione("1.0");
		//is client behind something?
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			   ipAddress = request.getRemoteAddr();
		}
		writeTo(Corrispettivi, ipAddress);
		
		return esito;
	}
	
	@Path("/")
	@POST
	public String putListMisuratoriFiscale(String Corrispettivi, @Context HttpServletRequest request){
		
		
		String timeStamp = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(new Date());
		log.info("received: "+timeStamp);
		int x = (int)Math.random() * 10;
		EsitoOperazioneType esito = new EsitoOperazioneType();
		esito.setIdOperazione(String.valueOf(x));
		esito.setVersione("1.0");
		//is client behind something?
		String ipAddress = request.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			   ipAddress = request.getRemoteAddr();
		}
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
			
		}
	}
	

	


}
