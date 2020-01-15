package cnr.isti.sse.data.corrispettivi;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TotaliType", propOrder = {
    "numeroDocCommerciali",
    "pagatoContanti",
    "pagatoElettronico",
    "scontoApagare",
    "ticket"
})
public class TotaliType {

	
	 @XmlElement(name = "NumeroDocCommerciali", required = true)
	  protected Integer numeroDocCommerciali;
	 
	 @XmlElement(name = "PagatoContanti")
	    protected BigDecimal pagatoContanti;
	 @XmlElement(name = "PagatoElettronico")
	    protected BigDecimal pagatoElettronico;
	 @XmlElement(name = "ScontoApagare")
	   protected BigDecimal scontoApagare;
	 @XmlElement(name = "Ticket")
	     protected TicketType ticket;
}
