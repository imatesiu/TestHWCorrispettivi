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
	public Integer getNumeroDocCommerciali() {
		return numeroDocCommerciali;
	}
	public void setNumeroDocCommerciali(Integer numeroDocCommerciali) {
		this.numeroDocCommerciali = numeroDocCommerciali;
	}
	public BigDecimal getPagatoContanti() {
		return pagatoContanti;
	}
	public void setPagatoContanti(BigDecimal pagatoContanti) {
		this.pagatoContanti = pagatoContanti;
	}
	public BigDecimal getPagatoElettronico() {
		return pagatoElettronico;
	}
	public void setPagatoElettronico(BigDecimal pagatoElettronico) {
		this.pagatoElettronico = pagatoElettronico;
	}
	public BigDecimal getScontoApagare() {
		return scontoApagare;
	}
	public void setScontoApagare(BigDecimal scontoApagare) {
		this.scontoApagare = scontoApagare;
	}
	public TicketType getTicket() {
		return ticket;
	}
	public void setTicket(TicketType ticket) {
		this.ticket = ticket;
	}
	 
	 
}
