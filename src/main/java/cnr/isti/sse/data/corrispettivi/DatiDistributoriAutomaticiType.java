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
 * <p>Java class for DatiDistributoriAutomaticiType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatiDistributoriAutomaticiType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="Periodo" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}PeriodoType"/>
 *         &lt;element name="Cumulato" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}CumulatoType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiDistributoriAutomaticiType", propOrder = {
    "periodo",
    "cumulato"
})
public class DatiDistributoriAutomaticiType {

    @XmlElement(name = "Periodo")
    protected PeriodoType periodo;
    @XmlElement(name = "Cumulato")
    protected CumulatoType cumulato;

    /**
     * Gets the value of the periodo property.
     * 
     * @return
     *     possible object is
     *     {@link PeriodoType }
     *     
     */
    public PeriodoType getPeriodo() {
        return periodo;
    }

    /**
     * Sets the value of the periodo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodoType }
     *     
     */
    public void setPeriodo(PeriodoType value) {
        this.periodo = value;
    }

    /**
     * Gets the value of the cumulato property.
     * 
     * @return
     *     possible object is
     *     {@link CumulatoType }
     *     
     */
    public CumulatoType getCumulato() {
        return cumulato;
    }

    /**
     * Sets the value of the cumulato property.
     * 
     * @param value
     *     allowed object is
     *     {@link CumulatoType }
     *     
     */
    public void setCumulato(CumulatoType value) {
        this.cumulato = value;
    }

}
