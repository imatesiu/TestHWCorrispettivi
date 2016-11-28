//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.11.29 alle 12:39:14 AM CET 
//


package cnr.isti.sse.data.corrispettivi.messaggi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per InformazioniAddizionaliDAType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="InformazioniAddizionaliDAType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ProtocolloComunicazione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="TipoDistributore" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InformazioniAddizionaliDAType", propOrder = {
    "protocolloComunicazione",
    "tipoDistributore"
})
public class InformazioniAddizionaliDAType {

    @XmlElement(name = "ProtocolloComunicazione", required = true)
    protected String protocolloComunicazione;
    @XmlElement(name = "TipoDistributore", required = true)
    protected String tipoDistributore;

    /**
     * Recupera il valore della proprietà protocolloComunicazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocolloComunicazione() {
        return protocolloComunicazione;
    }

    /**
     * Imposta il valore della proprietà protocolloComunicazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocolloComunicazione(String value) {
        this.protocolloComunicazione = value;
    }

    /**
     * Recupera il valore della proprietà tipoDistributore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDistributore() {
        return tipoDistributore;
    }

    /**
     * Imposta il valore della proprietà tipoDistributore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDistributore(String value) {
        this.tipoDistributore = value;
    }

}
