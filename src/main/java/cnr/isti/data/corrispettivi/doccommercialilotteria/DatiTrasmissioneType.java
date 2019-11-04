//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.11.02 alle 09:28:26 PM CET 
//


package cnr.isti.data.corrispettivi.doccommercialilotteria;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java per DatiTrasmissioneType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DatiTrasmissioneType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Formato" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/v1.0}FormatoType"/>
 *         &lt;element name="Denominazione" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/v1.0}String200LatinType"/>
 *         &lt;element name="IdCassa" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/v1.0}IdCassaType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiTrasmissioneType", propOrder = {
    "formato",
    "intestazione",
    "idCassa"
})
public class DatiTrasmissioneType {

    @XmlElement(name = "Formato", required = true)
    @XmlSchemaType(name = "string")
    protected FormatoType formato;
    @XmlElement(name = "Intestazione", required = true)
    protected Intestazione intestazione;
    @XmlElement(name = "IdCassa")
    protected String idCassa;

    /**
     * Recupera il valore della proprietà formato.
     * 
     * @return
     *     possible object is
     *     {@link FormatoType }
     *     
     */
    public FormatoType getFormato() {
        return formato;
    }

    /**
     * Imposta il valore della proprietà formato.
     * 
     * @param value
     *     allowed object is
     *     {@link FormatoType }
     *     
     */
    public void setFormato(FormatoType value) {
        this.formato = value;
    }

    /**
     * Recupera il valore della proprietà denominazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Intestazione getDenominazione() {
        return intestazione;
    }

    /**
     * Imposta il valore della proprietà denominazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenominazione(Intestazione value) {
        this.intestazione = value;
    }

    /**
     * Recupera il valore della proprietà idCassa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCassa() {
        return idCassa;
    }

    /**
     * Imposta il valore della proprietà idCassa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCassa(String value) {
        this.idCassa = value;
    }

}
