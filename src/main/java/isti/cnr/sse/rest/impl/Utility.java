package isti.cnr.sse.rest.impl;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.Principal;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

import org.glassfish.grizzly.utils.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import cnr.isti.sse.data.corrispettivi.DatiCorrispettiviType;
import cnr.isti.sse.data.corrispettivi.DatiRegistratoriTelematiciType;
import cnr.isti.sse.data.corrispettivi.IVAType;
import cnr.isti.sse.data.corrispettivi.messaggi.AttivaDispositivoType;
import cnr.isti.sse.data.corrispettivi.messaggi.EventoDispositivoType;
import cnr.isti.sse.data.corrispettivi.messaggi.RichiestaCertificatoDispositivoType;
import datanew.SignatureType;

public class Utility {
	
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Utility.class);
	
	public static void calc(DatiCorrispettiviType corrispettivi, String key, Map<String, BigDecimal> map) {

		
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
						BigDecimal old = map.get(key);
						BigDecimal res = old.add(lordo);
						log.info("Ricevuto per "+key+": "+lordo);
						log.info("totale per "+key+": "+res);
						map.put(key, res );
					}else{
						map.put(key, lordo);
						log.info("Ricevuto per "+key+": "+lordo);
						log.info("totale per "+key+": "+lordo);
					}
			}else{
				if(ammontare.compareTo(new BigDecimal(0))!=0)
					if(map.containsKey(key)){
						BigDecimal old = map.get(key);
						BigDecimal res = old.add(ammontare);
						log.info("Ricevuto per "+key+": "+ammontare);
						log.info("totale per "+key+": "+res);
						map.put(key, res );
					}else{
						map.put(key, ammontare);
						log.info("Ricevuto per "+key+": "+ammontare);
						log.info("totale per "+key+": "+ammontare);
					}
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
					System.err.println("Signature failed core validation");
					boolean sv = signature.getSignatureValue().validate(valContext);
					System.out.println("signature validation status: " + sv);
					if (sv == false) {
						// Check the validation status of each Reference.
						Iterator i = signature.getSignedInfo().getReferences().iterator();
						for (int j = 0; i.hasNext(); j++) {
							boolean refValid = ((Reference) i.next()).validate(valContext);
							System.out.println("ref[" + j + "] validity status: " + refValid);
							if(refValid==true){
								validFlag = true;
							}
						}
					}else{

						validFlag = true;
					}
				} else {
					System.out.println("Signature passed core validation");
				}

				Principal principal = cert.getSubjectDN();
				String name = principal.getName();
				matricola = name.substring(3, 14);

			} catch (Exception e) {
				System.err.println("Signature VUOTA interrompere prova");
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

}
