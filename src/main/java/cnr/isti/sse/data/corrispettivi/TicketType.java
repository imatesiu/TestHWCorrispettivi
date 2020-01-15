package cnr.isti.sse.data.corrispettivi;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TicketType", propOrder = {
    "pagatoTicket",
    "numeroTicket"
})
public class TicketType {
	
	 @XmlElement(name = "PagatoTicket")
	    protected BigDecimal pagatoTicket;
	 @XmlElement(name = "NumeroTicket")
	    protected Integer numeroTicket;
	public BigDecimal getPagatoTicket() {
		return pagatoTicket;
	}
	public void setPagatoTicket(BigDecimal pagatoTicket) {
		this.pagatoTicket = pagatoTicket;
	}
	public Integer getNumeroTicket() {
		return numeroTicket;
	}
	public void setNumeroTicket(Integer numeroTicket) {
		this.numeroTicket = numeroTicket;
	}

	 
	 
	 
}
