package isti.cnr.sse.rest.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import cnr.isti.sse.data.corrispettivi.DatiCorrispettiviType;
import cnr.isti.sse.data.corrispettivi.DatiRegistratoriTelematiciType;
import cnr.isti.sse.data.corrispettivi.IVAType;
import isti.cnr.sse.rest.impl.util.CSVUtils;

public class Utility {
	
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Utility.class);
	
	public static void calc(DatiCorrispettiviType corrispettivi, String key, Map<String, RT> map) {

		
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

				BigDecimal lordo = ammontare.multiply((iva.getAliquotaIVA().add(new BigDecimal(100)).divide(new BigDecimal(100)))).setScale(2, RoundingMode.HALF_UP);
				BigDecimal impostaiva = ammontare.multiply(iva.getAliquotaIVA()).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);

				BigDecimal ammAnn = datiRegistratoriTelematici.getTotaleAmmontareAnnulli();
				BigDecimal ammResi = datiRegistratoriTelematici.getTotaleAmmontareResi();
				
				
				BigDecimal ivaann = ammAnn.multiply(iva.getAliquotaIVA()).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
				BigDecimal ivaresi= ammResi.multiply(iva.getAliquotaIVA()).divide(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP);
				
				impostaiva = impostaiva.subtract(ivaann).subtract(ivaresi);
				
				if(!impostaiva.equals(iva.getImposta())){
					log.error("imposta Errata!! per imponibile "+ammontare+" aliquota iva "+iva.getAliquotaIVA());	
					log.error("imposta Errata!! mi aspettavo iva "+impostaiva+" trovo "+iva.getImposta());			
				}
				if(lordo.compareTo(new BigDecimal(0))!=0)
					if(map.containsKey(key)){
						RT rt = map.get(key);
						BigDecimal old = rt.getTotaleRicevuto();
						BigDecimal res = old.add(lordo);
						log.info("Ricevuto per "+key+": "+lordo);
						log.info("totale per "+key+": "+res);
						rt.setTotaleRicevuto(res );
					}/*else{
						RT rt = map.get(key);
						rt.setGT(lordo);
						//map.put(key, lordo);
						log.info("Ricevuto per "+key+": "+lordo);
						log.info("totale per "+key+": "+lordo);
					}*/
			}else{
				if(ammontare.compareTo(new BigDecimal(0))!=0)
					if(map.containsKey(key)){
						RT rt = map.get(key);
						BigDecimal old = rt.getTotaleRicevuto();
						BigDecimal res = old.add(ammontare);
						log.info("Ricevuto per "+key+": "+ammontare);
						log.info("totale per "+key+": "+res);
						rt.setTotaleRicevuto(res );
					}/*else{
						map.put(key, ammontare);
						log.info("Ricevuto per "+key+": "+ammontare);
						log.info("totale per "+key+": "+ammontare);
					}*/
			}
		}

		System.out.println();

	}
	
	
	public static void writeTo(DatiCorrispettiviType DCT, String ipAddress, int num){
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
			
			marshaller.marshal( DCT, os );

		} catch (JAXBException | FileNotFoundException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);

		}
	}

	public static void writeTo(String DCT, String ipAddress, int num){

		try {


			String timeStamp = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(new Date());
			File theDir = new File("received_"+ipAddress);

			// if the directory does not exist, create it
			if (!theDir.exists()) {
				theDir.mkdir();
			}

			String FILENAME = "received_"+ipAddress+"/RT_"+ipAddress+"_"+timeStamp+"_"+num+".xml";
			BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME));
			

			bw.write(DCT);
			bw.close();

		} catch (  IOException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}
	}
	public static void writeRTs(Map<String,RT> map){
		try{
			String csvFile = "develope.csv";
			File f = new File(csvFile);
			FileWriter writer;
			//for header
			if(!f.exists()){
				writer = new FileWriter(csvFile);
				String header = "Matricola; GT; TotaleRicevuto; timediff; starttime; workingtime; stoptime; Zricevuti; Z; Descrizione; isCloded";
				CSVUtils.writeLine(writer, Arrays.asList(header.split(";")));
			}else{
				writer = new FileWriter(csvFile,true);
			}

			for (RT d : map.values()) {

				List<String> list = new ArrayList<>(Arrays.asList(d.toString().split(";")));


				CSVUtils.writeLine(writer, list);

				//try custom separator and quote. 
				//CSVUtils.writeLine(writer, list, '|', '\"');
			}

			writer.flush();
			writer.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	

    public static void serialize(Map<String,RT> map)
    {
    	String file = "database.ser";
        FileOutputStream fileOut;
        try
         {
            fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(map);
            out.close();
            fileOut.close();
         }catch(Exception e)
         {
             e.printStackTrace();
             return;
         }  
        return;
    }
     
    public static Map<String,RT> deserialize()
    {
    	String file = "database.ser";
    	Map<String,RT> map = null;
          try
          {
             FileInputStream fileIn = new FileInputStream(file);
             ObjectInputStream in = new ObjectInputStream(fileIn);
             map = (Map<String,RT>) in.readObject();
             in.close();
             fileIn.close();
         }catch(Exception e)
         {
             e.printStackTrace();   
             return null;
         }
         return map;
    }

}
