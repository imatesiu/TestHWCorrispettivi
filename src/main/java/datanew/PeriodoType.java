//
// Questo file Ŕ stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrÓ persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.11.22 alle 11:15:36 PM CET 
//


package datanew;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per PeriodoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="PeriodoType">
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
 *         &lt;element name="DataOraPrelievoPrec" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="ProgressivoPrelievo" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}ProgressivoPrelievoType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeriodoType", propOrder = {
    "venduto",
    "vendutoContante",
    "vendutoNoContante",
    "incassato",
    "incassatoRicarica",
    "incassatoVendita",
    "totaleResoTubiResto",
    "totaleCaricatoTubiResto",
    "totaleResoManualeTubiResto",
    "totaleCaricatoManualeTubiResto",
    "dataOraPrelievoPrec",
    "progressivoPrelievo"
})
public class PeriodoType {

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
    @XmlElement(name = "DataOraPrelievoPrec", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataOraPrelievoPrec;
    @XmlElement(name = "ProgressivoPrelievo")
    @XmlSchemaType(name = "nonNegativeInteger")
    protected long progressivoPrelievo;

    /**
     * Recupera il valore della proprietÓ venduto.
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
     * Imposta il valore della proprietÓ venduto.
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
     * Recupera il valore della proprietÓ vendutoContante.
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
     * Imposta il valore della proprietÓ vendutoContante.
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
     * Recupera il valore della proprietÓ vendutoNoContante.
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
     * Imposta il valore della proprietÓ vendutoNoContante.
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
     * Recupera il valore della proprietÓ incassato.
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
     * Imposta il valore della proprietÓ incassato.
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
     * Recupera il valore della proprietÓ incassatoRicarica.
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
     * Imposta il valore della proprietÓ incassatoRicarica.
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
     * Recupera il valore della proprietÓ incassatoVendita.
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
     * Imposta il valore della proprietÓ incassatoVendita.
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
     * Recupera il valore della proprietÓ totaleResoTubiResto.
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
     * Imposta il valore della proprietÓ totaleResoTubiResto.
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
     * Recupera il valore della proprietÓ totaleCaricatoTubiResto.
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
     * Imposta il valore della proprietÓ totaleCaricatoTubiResto.
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
     * Recupera il valore della proprietÓ totaleResoManualeTubiResto.
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
     * Imposta il valore della proprietÓ totaleResoManualeTubiResto.
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
     * Recupera il valore della proprietÓ totaleCaricatoManualeTubiResto.
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
     * Imposta il valore della proprietÓ totaleCaricatoManualeTubiResto.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotaleCaricatoManualeTubiResto(BigDecimal value) {
        this.totaleCaricatoManualeTubiResto = value;
    }

    /**
     * Recupera il valore della proprietÓ dataOraPrelievoPrec.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataOraPrelievoPrec() {
        return dataOraPrelievoPrec;
    }

    /**
     * Imposta il valore della proprietÓ dataOraPrelievoPrec.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataOraPrelievoPrec(XMLGregorianCalendar value) {
        this.dataOraPrelievoPrec = value;
    }

    /**
     * Recupera il valore della proprietÓ progressivoPrelievo.
     * 
     */
    public long getProgressivoPrelievo() {
        return progressivoPrelievo;
    }

    /**
     * Imposta il valore della proprietÓ progressivoPrelievo.
     * 
     */
    public void setProgressivoPrelievo(long value) {
        this.progressivoPrelievo = value;
    }

}
