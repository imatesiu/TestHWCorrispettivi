//
// Questo file � stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andr� persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.01.17 alle 08:19:27 PM CET 
//


package cnr.isti.sse.data.corrispettivi;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per CumulatoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
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
     * Recupera il valore della propriet� venduto.
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
     * Imposta il valore della propriet� venduto.
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
     * Recupera il valore della propriet� vendutoContante.
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
     * Imposta il valore della propriet� vendutoContante.
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
     * Recupera il valore della propriet� vendutoNoContante.
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
     * Imposta il valore della propriet� vendutoNoContante.
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
     * Recupera il valore della propriet� incassato.
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
     * Imposta il valore della propriet� incassato.
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
     * Recupera il valore della propriet� incassatoRicarica.
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
     * Imposta il valore della propriet� incassatoRicarica.
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
     * Recupera il valore della propriet� incassatoVendita.
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
     * Imposta il valore della propriet� incassatoVendita.
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
     * Recupera il valore della propriet� totaleResoTubiResto.
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
     * Imposta il valore della propriet� totaleResoTubiResto.
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
     * Recupera il valore della propriet� totaleCaricatoTubiResto.
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
     * Imposta il valore della propriet� totaleCaricatoTubiResto.
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
     * Recupera il valore della propriet� totaleResoManualeTubiResto.
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
     * Imposta il valore della propriet� totaleResoManualeTubiResto.
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
     * Recupera il valore della propriet� totaleCaricatoManualeTubiResto.
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
     * Imposta il valore della propriet� totaleCaricatoManualeTubiResto.
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
