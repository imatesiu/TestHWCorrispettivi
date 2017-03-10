//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.03.10 at 12:54:00 PM CET 
//


package cnr.isti.sse.data.corrispettivi;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CumulatoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CumulatoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Venduto" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}Amount2DecimalType"/>
 *         &lt;element name="VendutoContante" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}Amount2DecimalType"/>
 *         &lt;element name="VendutoNoContante" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}Amount2DecimalType"/>
 *         &lt;element name="Incassato" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}Amount2DecimalType"/>
 *         &lt;element name="IncassatoRicarica" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}Amount2DecimalType"/>
 *         &lt;element name="IncassatoVendita" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}Amount2DecimalType"/>
 *         &lt;element name="TotaleResoTubiResto" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}Amount2DecimalType"/>
 *         &lt;element name="TotaleCaricatoTubiResto" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}Amount2DecimalType"/>
 *         &lt;element name="TotaleResoManualeTubiResto" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}Amount2DecimalType"/>
 *         &lt;element name="TotaleCaricatoManualeTubiResto" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}Amount2DecimalType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CumulatoType", propOrder = {
    "venduto",
    "vendutoContante",
    "vendutoNoContante",
    "incassato",
    "incassatoRicarica",
    "incassatoVendita",
    "totaleResoTubiResto",
    "totaleCaricatoTubiResto",
    "totaleResoManualeTubiResto",
    "totaleCaricatoManualeTubiResto"
})
public class CumulatoType {

    @XmlElement(name = "Venduto", required = true)
    protected BigDecimal venduto;
    @XmlElement(name = "VendutoContante", required = true)
    protected BigDecimal vendutoContante;
    @XmlElement(name = "VendutoNoContante", required = true)
    protected BigDecimal vendutoNoContante;
    @XmlElement(name = "Incassato", required = true)
    protected BigDecimal incassato;
    @XmlElement(name = "IncassatoRicarica", required = true)
    protected BigDecimal incassatoRicarica;
    @XmlElement(name = "IncassatoVendita", required = true)
    protected BigDecimal incassatoVendita;
    @XmlElement(name = "TotaleResoTubiResto", required = true)
    protected BigDecimal totaleResoTubiResto;
    @XmlElement(name = "TotaleCaricatoTubiResto", required = true)
    protected BigDecimal totaleCaricatoTubiResto;
    @XmlElement(name = "TotaleResoManualeTubiResto", required = true)
    protected BigDecimal totaleResoManualeTubiResto;
    @XmlElement(name = "TotaleCaricatoManualeTubiResto", required = true)
    protected BigDecimal totaleCaricatoManualeTubiResto;

    /**
     * Gets the value of the venduto property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVenduto() {
        return venduto;
    }

    /**
     * Sets the value of the venduto property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVenduto(BigDecimal value) {
        this.venduto = value;
    }

    /**
     * Gets the value of the vendutoContante property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVendutoContante() {
        return vendutoContante;
    }

    /**
     * Sets the value of the vendutoContante property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVendutoContante(BigDecimal value) {
        this.vendutoContante = value;
    }

    /**
     * Gets the value of the vendutoNoContante property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getVendutoNoContante() {
        return vendutoNoContante;
    }

    /**
     * Sets the value of the vendutoNoContante property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setVendutoNoContante(BigDecimal value) {
        this.vendutoNoContante = value;
    }

    /**
     * Gets the value of the incassato property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIncassato() {
        return incassato;
    }

    /**
     * Sets the value of the incassato property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIncassato(BigDecimal value) {
        this.incassato = value;
    }

    /**
     * Gets the value of the incassatoRicarica property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIncassatoRicarica() {
        return incassatoRicarica;
    }

    /**
     * Sets the value of the incassatoRicarica property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIncassatoRicarica(BigDecimal value) {
        this.incassatoRicarica = value;
    }

    /**
     * Gets the value of the incassatoVendita property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIncassatoVendita() {
        return incassatoVendita;
    }

    /**
     * Sets the value of the incassatoVendita property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIncassatoVendita(BigDecimal value) {
        this.incassatoVendita = value;
    }

    /**
     * Gets the value of the totaleResoTubiResto property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotaleResoTubiResto() {
        return totaleResoTubiResto;
    }

    /**
     * Sets the value of the totaleResoTubiResto property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotaleResoTubiResto(BigDecimal value) {
        this.totaleResoTubiResto = value;
    }

    /**
     * Gets the value of the totaleCaricatoTubiResto property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotaleCaricatoTubiResto() {
        return totaleCaricatoTubiResto;
    }

    /**
     * Sets the value of the totaleCaricatoTubiResto property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotaleCaricatoTubiResto(BigDecimal value) {
        this.totaleCaricatoTubiResto = value;
    }

    /**
     * Gets the value of the totaleResoManualeTubiResto property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotaleResoManualeTubiResto() {
        return totaleResoManualeTubiResto;
    }

    /**
     * Sets the value of the totaleResoManualeTubiResto property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotaleResoManualeTubiResto(BigDecimal value) {
        this.totaleResoManualeTubiResto = value;
    }

    /**
     * Gets the value of the totaleCaricatoManualeTubiResto property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotaleCaricatoManualeTubiResto() {
        return totaleCaricatoManualeTubiResto;
    }

    /**
     * Sets the value of the totaleCaricatoManualeTubiResto property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotaleCaricatoManualeTubiResto(BigDecimal value) {
        this.totaleCaricatoManualeTubiResto = value;
    }

}
