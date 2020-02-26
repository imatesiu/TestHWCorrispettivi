package isti.cnr.sse.rest.impl.dispositivi.corrispettivi.lotteria;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.IOUtils;
import org.glassfish.grizzly.utils.Pair;

import cnr.isti.data.corrispettivi.doccommercialilotteria.DatiPagamentoType;
import cnr.isti.data.corrispettivi.doccommercialilotteria.DocCommercialiLotteriaType;
import cnr.isti.data.corrispettivi.doccommercialilotteria.DocumentoCommercialeType;
import cnr.isti.data.corrispettivi.doccommercialilotteria.ResoAnnulloType;
import cnr.isti.data.corrispettivi.doccommercialilotteria.TipologiaResoAnnulloType;
import cnr.isti.data.corrispettivi.doccommercialilotteria.VenditaType;
import cnr.isti.sse.data.corrispettivi.DatiCorrispettiviType;
import cnr.isti.sse.data.corrispettivi.messaggi.EventoDispositivoType;
import isti.cnr.sse.rest.impl.APIProveHWImpl;
import isti.cnr.sse.rest.impl.ErrorHttp;
import isti.cnr.sse.rest.impl.Utility;

@Consumes(MediaType.APPLICATION_XML)
//@Produces(MediaType.APPLICATION_XML)
@Path("/lotteria")
public class APILotteriaCorrispettiviImpl {

	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(APILotteriaCorrispettiviImpl.class);

	private static Integer ErrorType = 9999;

	private static ErrorHttp flag = ErrorHttp.Null;

	
	@Path("/corrispettivi")
	@POST
	public String postLotteriaCorrispettivi(String lotteria, @Context HttpServletRequest request, @Context HttpServletResponse response)
			throws JAXBException {// DatiCorrispettiviType Corrispettivi,
		// @Context HttpServletRequest request){
		response.setHeader("Connection", "Close");
		log.info("****************LotteriaCorrispettivi********************");
		log.info("Message from: "+request.getRemoteAddr());
		int len = request.getContentLength();
		if(request.getRequestURL().lastIndexOf("https")==-1) {
			log.error("**** CONESSIONE NON SSL ***** ");
			if(request.getRequestURL().indexOf("9090")>-1) {
				log.error("**** USATA PORTA 9090 ***** ");
			}
			log.error("**** *** *** *** ***** ");
		}
		
		
		JAXBContext jaxbContext = JAXBContext.newInstance(DocCommercialiLotteriaType.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Utility.validateXmlLotteria(unmarshaller);
		if(!flag.equals(ErrorHttp.Null)){
			try{
				// @Todo
				InputStream is = APIProveHWImpl.class.getClassLoader().getResourceAsStream("lotteria/error.tracciato.xml");
				String text = IOUtils.toString(is, StandardCharsets.UTF_8.name());
				
				if(ErrorType!=9999) {
					text = Utility.getResource(ErrorType);
					
				}			
				
					throw new WebApplicationException(Response.status(flag.getValue()).entity(text).build());

			} catch (IOException e) {
				e.printStackTrace();
				log.error(e);
			}
			
		}
		
		if(lotteria.length()==0){
			try{
				InputStream is = APIProveHWImpl.class.getClassLoader().getResourceAsStream("lotteria/error.tracciato.xml");
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
			StringReader reader = new StringReader(lotteria);
			DocCommercialiLotteriaType docLotteria = (DocCommercialiLotteriaType) unmarshaller.unmarshal(reader);
			
			Date now = new Date();
			String timeStamp = new SimpleDateFormat("dd_MM_yyyy__HH_mm_ss").format(now);
			Pair<String, Boolean> pair = Utility.getMatricola(docLotteria, lotteria);
			String ipAddress = pair.getFirst();
			if (ipAddress == null) {
				ipAddress = request.getHeader("X-FORWARDED-FOR");
			}

			if (ipAddress == null) {
				ipAddress = request.getRemoteAddr();
			}
			log.info("received form: " + ipAddress + " " + timeStamp);
			
			int error = checkDatiLotteria(len, docLotteria);
			log.info("************************************");
			if(pair.getSecond()){
				// TODO cambiare risporta
				InputStream is = APIProveHWImpl.class.getClassLoader().getResourceAsStream("response/lotteria/Lotteria_AC.xml");
				String text = IOUtils.toString(is, StandardCharsets.UTF_8.name());
				return text;
			}else{
				InputStream is = APIProveHWImpl.class.getClassLoader().getResourceAsStream("response/lotteria/Lotteria_Error.SC.99999.xml");
				String text = IOUtils.toString(is, StandardCharsets.UTF_8.name());
				throw new WebApplicationException(Response.status(406).entity(text).build());
			}


		} catch (IOException  | JAXBException e) {
			e.printStackTrace();
			log.error(e);
		}
		try{
			// TODO
			InputStream is = APIProveHWImpl.class.getClassLoader().getResourceAsStream("response/lotteria/Lotteria_Error.SC.00000.xml");
			String text = IOUtils.toString(is, StandardCharsets.UTF_8.name());
			throw new WebApplicationException(Response.status(406).entity(text).build());
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e);
		}
		return null;
		
	}
	
	
	private int checkDatiLotteria(int len, DocCommercialiLotteriaType docLotteria) {
		
		if(docLotteria.getDocumentoCommerciale().size()>100 ){
			log.error("Error "+ 300);

			return 300;
		}
		if(len>60000){
			log.error("Error "+ 301);

			return 301;
		}
		
		 List<DocumentoCommercialeType> list = docLotteria.getDocumentoCommerciale();
		for (DocumentoCommercialeType documentoCommerciale : list) {
			log.info("Progressivo Lotteria: "+documentoCommerciale.getNumeroProgressivo());
			BigDecimal ammontare = documentoCommerciale.getAmmontare();
			log.info("Ricevuto Ammontare lotteria: "+ammontare);
			ResoAnnulloType resoannullo = documentoCommerciale.getResoAnnullo();
			if(resoannullo!=null){
				log.info("*****DATI RESO/ANNULLO*****");
				log.info(resoannullo.getTipologia());
				log.info(resoannullo.getDispositivo());
				log.info(resoannullo.getProgressivo());
				log.info(resoannullo.getDataOra());
				log.info("*********");
			}
			
			
			VenditaType vendita = documentoCommerciale.getVendita();
			if(vendita!=null){
				BigDecimal totpagamenti = new BigDecimal(0);
				for(DatiPagamentoType pagamenti : vendita.getDatiPagamento()){
					totpagamenti = totpagamenti.add(pagamenti.getImporto());
				}
				log.info("Somma Pagamenti Calcolata: "+totpagamenti);
				if(ammontare.compareTo(totpagamenti)!=0){
					log.error("Error "+ 308);
					return 308;
				}
			}
		}
		
		log.info("****END*****");
		return 0;
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
	
	
}
