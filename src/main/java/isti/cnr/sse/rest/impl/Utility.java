package isti.cnr.sse.rest.impl;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.InputStream;

import java.io.OutputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.security.auth.x500.X500Principal;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.io.IOUtils;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.glassfish.grizzly.utils.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import cnr.isti.data.corrispettivi.doccommercialilotteria.DocCommercialiLotteriaType;
import cnr.isti.sse.data.corrispettivi.DatiCorrispettiviType;
import cnr.isti.sse.data.corrispettivi.DatiRegistratoriTelematiciType;
import cnr.isti.sse.data.corrispettivi.ElencoCorrispettiviType;
import cnr.isti.sse.data.corrispettivi.IVAType;
import cnr.isti.sse.data.corrispettivi.NaturaType;
import cnr.isti.sse.data.corrispettivi.TotaliType;
import cnr.isti.sse.data.corrispettivi.messaggi.AttivaDispositivoType;


import cnr.isti.sse.data.corrispettivi.messaggi.EventoDispositivoType;
import cnr.isti.sse.data.corrispettivi.messaggi.signature.SignatureType;
import isti.cnr.sse.rest.impl.util.CSVUtils;


import cnr.isti.sse.data.corrispettivi.messaggi.RichiestaCertificatoDispositivoType;


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
						String info = "IVA"+iva.getAliquotaIVA()+"% -> Ammont:"+ammontare+" Impo: "+impostaiva;
						log.info("Ricevuto per "+key+": Lordo "+lordo +"-->"+info);
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


	public static Pair<String,Boolean> getMatricola(DatiCorrispettiviType d, String corri) {
		SignatureType signa = d.getSignature();
		return getMatricola(signa, corri);
	}

	public static Pair<String,Boolean> getMatricola(RichiestaCertificatoDispositivoType d, String corri) {
		SignatureType signa = d.getSignature();
		return getMatricola(signa, corri);
	}

	public static Pair<String,Boolean> getMatricola(AttivaDispositivoType d, String corri) {
		SignatureType signa = d.getSignature();
		return getMatricola(signa, corri);
	}

	public static Pair<String,Boolean> getMatricola(EventoDispositivoType d, String corri) {
		SignatureType signa = d.getSignature();
		return getMatricola(signa, corri);
	}

	private static Pair<String,Boolean> getMatricola(SignatureType d, String corri) {
		String matricola = "";
		boolean validFlag = false;
		if (d != null) {
			byte[] certificate = d.getKeyInfo().getX509Data().getX509Certificate();
			CertificateFactory fact = null;
			try {
				fact = CertificateFactory.getInstance("X.509");

				X509Certificate cert = (X509Certificate) fact
						.generateCertificate(new ByteArrayInputStream(certificate));

				PublicKey publicKey = cert.getPublicKey();

				Document doc = convertStringToDocument(corri);// marshallToDocument(d,DatiCorrispettiviType.class);

				NodeList nl = doc.getElementsByTagNameNS(XMLSignature.XMLNS, "Signature");

				if (nl.getLength() == 0) {
					throw new Exception("Cannot find Signature element");
				}

				DOMValidateContext valContext = new DOMValidateContext(publicKey, nl.item(0));

				XMLSignatureFactory fac = XMLSignatureFactory.getInstance("DOM");

				XMLSignature signature = fac.unmarshalXMLSignature(valContext);

				validFlag = signature.validate(valContext);

				// Check core validation status.
				if (validFlag == false) {
					Beep.tone(1000, 300,1600);
					Beep.tone(1000, 300,1600);
					Beep.tone(1000, 300,1600);
					log.error("Signature failed core validation");
					boolean sv = signature.getSignatureValue().validate(valContext);
					log.trace("signature validation status: " + sv);
					if (sv == false) {
						// Check the validation status of each Reference.
						Iterator i = signature.getSignedInfo().getReferences().iterator();
						for (int j = 0; i.hasNext(); j++) {
							boolean refValid = ((Reference) i.next()).validate(valContext);
							log.trace("ref[" + j + "] validity status: " + refValid);
							if(refValid==true){
								validFlag = true;
							}
						}
					}else{

						validFlag = true;
					}
				} else {
					log.trace("Signature passed core validation");
				}

				Principal principal = cert.getSubjectDN();
				String common_name = principal.getName();
				/*matricola = common_name;
				if(common_name.length()==14) {
					matricola = common_name.substring(3, 14);
				}*/
				X500Principal principals = cert.getSubjectX500Principal();

			    X500Name x500name = new X500Name( principals.getName() );
			    
				 String org = IETFUtils.valueToString(x500name.getRDNs(BCStyle.O)[0].getFirst().getValue());
				 String cn = IETFUtils.valueToString(x500name.getRDNs(BCStyle.CN)[0].getFirst().getValue());
				 matricola = cn;
				 if(!org.contains("Agenzia"))
					 log.info("ORG  : " + org);
		         

			} catch (Exception e) {
				log.error("Signature VUOTA interrompere prova");
				Pair <String,Boolean> pair = new Pair<>();
				pair.setFirst("0.0.0.0");
				pair.setSecond(false);
				return pair;
			}

		}
		Pair <String,Boolean> pair = new Pair<>();
		pair.setFirst(matricola);
		pair.setSecond(validFlag);
		return pair;
	}

	private static Document convertStringToDocument(String xmlStr) {
		try {

			DOMResult output = new DOMResult();
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(new StreamSource(new StringReader(xmlStr)), output);

			return (Document) output.getNode();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
			String csvFile = "finals.csv";
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
				writeRT(writer,d);	


				//try custom separator and quote. 
				//CSVUtils.writeLine(writer, list, '|', '\"');
			}

			writer.flush();
			writer.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void writeRT( RT d){
		try{
			String csvFile = d.getMatricola()+"."+d.getDescrizione()+".csv";
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
			writeRT(writer,d);	
			writer.flush();
			writer.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	private static void writeRT(FileWriter writer, RT d) throws IOException {
		List<String> list = new ArrayList<>(Arrays.asList(d.toString().split(";")));
		CSVUtils.writeLine(writer, list);

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

	public static String getResource(int codeerror) throws IOException {

		String nameFile = "response/";
		if(codeerror>=200 && codeerror<=299)
			nameFile+="Corrispettivi_00"+codeerror+".xml";
		if(codeerror>=100 && codeerror<=199)
			nameFile+="Attivazione_00"+codeerror+".xml";
		if(codeerror>=700 && codeerror<=799)
			nameFile+="Eventi_00"+codeerror+".xml";
		if(codeerror>=300 && codeerror<=306)
			nameFile+="lotteria/Lotteria_Error.SC.00"+codeerror+".xml";
		if(codeerror==99999)
			nameFile+="lotteria/Lotteria_Error.SC.99999.xml";


		InputStream is = Utility.class.getClassLoader().getResourceAsStream(nameFile);
		String text = IOUtils.toString(is, StandardCharsets.UTF_8.name());


		return text;
	}


	public static Pair<String, Boolean> getMatricola(DocCommercialiLotteriaType docLotteria, String event) {
		SignatureType signa = docLotteria.getSignature();
		return getMatricola(signa, event);
	}


	public static void testInfoCorri(DatiCorrispettiviType corrispettivi) {
		try {
			if(corrispettivi!=null) {
				boolean Simu = corrispettivi.isSimulazione();
				if(Simu) {
					log.info("**** ATTENZIONE ****");
					log.info("**** Simulazione ATTIVA ****");
					log.info("**** ********** ****");
				}
				TotaliType Totali = corrispettivi.getDatiRT().getTotali();
				if(Totali==null) {
					log.error("**** ATTENZIONE ****");
					log.error("Corrispettivi v6");
					log.error("Passare a corrispettivi v7");
					log.error("**** ********** ****");
					return;
				}
				List<DatiRegistratoriTelematiciType> Riepilogo = corrispettivi.getDatiRT().getRiepilogo();
				BigDecimal Tot = checkRiepilogo(Riepilogo);

				BigDecimal sum = Totali.getPagatoContanti().add(Totali.getPagatoElettronico()).add(Totali.getScontoApagare());
				if(Tot.compareTo(sum)!=0) {
					log.error("Totali non congruenti");
				}

			}
		}catch (Exception e) {
			log.error(e);
		}

	}


	private static BigDecimal checkRiepilogo(List<DatiRegistratoriTelematiciType> riepilogo) {
		
		BigDecimal totale = new BigDecimal(0);
		
		for (DatiRegistratoriTelematiciType datiRegistratoriTelematiciType : riepilogo) {
			
			IVAType ivat = datiRegistratoriTelematiciType.getIva();
			
			NaturaType naturat = datiRegistratoriTelematiciType.getNatura();
			
			BigDecimal ammontare = datiRegistratoriTelematiciType.getAmmontare();
			
			BigDecimal parziale = datiRegistratoriTelematiciType.getImportoParziale();		
			BigDecimal beniinsonp = datiRegistratoriTelematiciType.getBeniInSospeso();
			BigDecimal nonriscoSSN = datiRegistratoriTelematiciType.getNonRiscossoDCRaSSN();
			BigDecimal nonriscoFAT = datiRegistratoriTelematiciType.getNonRiscossoFatture();
			BigDecimal nonriscoOmg = datiRegistratoriTelematiciType.getNonRiscossoOmaggio();
			BigDecimal nonriscoServ = datiRegistratoriTelematiciType.getNonRiscossoServizi();
			BigDecimal resi = datiRegistratoriTelematiciType.getTotaleAmmontareResi();
			BigDecimal annulli = datiRegistratoriTelematiciType.getTotaleAmmontareAnnulli();

			BigDecimal sum = parziale.add(beniinsonp)
					.add(nonriscoSSN).add(nonriscoFAT).add(nonriscoServ)
					.add(resi).add(annulli); 
			
			if(ammontare.compareTo(sum)!=0) {
				log.error("Il valore nel campo Ammontare non Corretto");
			}
			
			if(ivat!=null) {
				BigDecimal aliq = ivat.getAliquotaIVA();
				totale = totale.add(ammontare.multiply(aliq));
			}else {
				totale = totale.add(ammontare);
			}

		}
		
		return totale;
	}


}
