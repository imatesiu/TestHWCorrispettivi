//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.03.26 at 08:39:20 PM CEST 
//


package cnr.isti.sse.data.corrispettivi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for IdFiscaleType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IdFiscaleType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdPaese" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}NazioneType"/>
 *         &lt;element name="IdCodice" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}IdCodiceType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IdFiscaleType", propOrder = {
    "idPaese",
    "idCodice"
})
public class IdFiscaleType {

    @XmlElement(name = "IdPaese", required = true)
    protected String idPaese;
    @XmlElement(name = "IdCodice", required = true)
    protected String idCodice;

    /**
     * Gets the value of the idPaese property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdPaese() {
        return idPaese;
    }

    /**
     * Sets the value of the idPaese property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdPaese(String value) {
        this.idPaese = value;
    }

    /**
     * Gets the value of the idCodice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCodice() {
        return idCodice;
    }

    /**
     * Sets the value of the idCodice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCodice(String value) {
        this.idCodice = value;
    }

}
