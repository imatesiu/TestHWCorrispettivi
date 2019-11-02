package cnr.isti.data.corrispettivi.doccommercialilotteria;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Intestazione", propOrder = {
    "denominazione",
    "comune"
})
public class Intestazione {
	
	@XmlElement(name = "Denominazione", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String denominazione;
	
	@XmlElement(name = "Comune", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String comune;
	
	 public String getDenominazione() {
	        return denominazione;
	    }

	    /**
	     * Imposta il valore della proprietà denominazione.
	     * 
	     * @param value
	     *     allowed object is
	     *     {@link String }
	     *     
	     */
	    public void setDenominazione(String value) {
	        this.denominazione = value;
	    }

		public String getComune() {
			return comune;
		}

		public void setComune(String comune) {
			this.comune = comune;
		}
	    
	    

}
