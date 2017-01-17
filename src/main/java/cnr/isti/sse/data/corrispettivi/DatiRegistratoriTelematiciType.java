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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Classe Java per DatiRegistratoriTelematiciType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DatiRegistratoriTelematiciType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="IVA" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}IVAType"/>
 *           &lt;element name="Natura" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}NaturaType"/>
 *           &lt;element name="VentilazioneIVA" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}VentilazioneIVAType"/>
 *         &lt;/choice>
 *         &lt;element name="Ammontare" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}Amount2DecimalType"/>
 *         &lt;element name="RifNormativo" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}String100LatinType" minOccurs="0"/>
 *         &lt;element name="TotaleAmmontareResi" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}Amount2DecimalType" minOccurs="0"/>
 *         &lt;element name="TotaleAmmontareAnnulli" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}Amount2DecimalType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiRegistratoriTelematiciType", propOrder = {
    "iva",
    "natura",
    "ventilazioneIVA",
    "ammontare",
    "rifNormativo",
    "totaleAmmontareResi",
    "totaleAmmontareAnnulli"
})
public class DatiRegistratoriTelematiciType {

    @XmlElement(name = "IVA")
    protected IVAType iva;
    @XmlElement(name = "Natura")
    @XmlSchemaType(name = "string")
    protected NaturaType natura;
    @XmlElement(name = "VentilazioneIVA")
    @XmlSchemaType(name = "string")
    protected VentilazioneIVAType ventilazioneIVA;
    @XmlElement(name = "Ammontare", required = true)
    protected BigDecimal ammontare;
    @XmlElement(name = "RifNormativo")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String rifNormativo;
    @XmlElement(name = "TotaleAmmontareResi")
    protected BigDecimal totaleAmmontareResi;
    @XmlElement(name = "TotaleAmmontareAnnulli")
    protected BigDecimal totaleAmmontareAnnulli;

    /**
     * Recupera il valore della propriet� iva.
     * 
     * @return
     *     possible object is
     *     {@link IVAType }
     *     
     */
    public IVAType getIVA() {
        return iva;
    }

    /**
     * Imposta il valore della propriet� iva.
     * 
     * @param value
     *     allowed object is
     *     {@link IVAType }
     *     
     */
    public void setIVA(IVAType value) {
        this.iva = value;
    }

    /**
     * Recupera il valore della propriet� natura.
     * 
     * @return
     *     possible object is
     *     {@link NaturaType }
     *     
     */
    public NaturaType getNatura() {
        return natura;
    }

    /**
     * Imposta il valore della propriet� natura.
     * 
     * @param value
     *     allowed object is
     *     {@link NaturaType }
     *     
     */
    public void setNatura(NaturaType value) {
        this.natura = value;
    }

    /**
     * Recupera il valore della propriet� ventilazioneIVA.
     * 
     * @return
     *     possible object is
     *     {@link VentilazioneIVAType }
     *     
     */
    public VentilazioneIVAType getVentilazioneIVA() {
        return ventilazioneIVA;
    }

    /**
     * Imposta il valore della propriet� ventilazioneIVA.
     * 
     * @param value
     *     allowed object is
     *     {@link VentilazioneIVAType }
     *     
     */
    public void setVentilazioneIVA(VentilazioneIVAType value) {
        this.ventilazioneIVA = value;
    }

    /**
     * Recupera il valore della propriet� ammontare.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmmontare() {
        return ammontare;
    }

    /**
     * Imposta il valore della propriet� ammontare.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmmontare(BigDecimal value) {
        this.ammontare = value;
    }

    /**
     * Recupera il valore della propriet� rifNormativo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRifNormativo() {
        return rifNormativo;
    }

    /**
     * Imposta il valore della propriet� rifNormativo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRifNormativo(String value) {
        this.rifNormativo = value;
    }

    /**
     * Recupera il valore della propriet� totaleAmmontareResi.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotaleAmmontareResi() {
        return totaleAmmontareResi;
    }

    /**
     * Imposta il valore della propriet� totaleAmmontareResi.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotaleAmmontareResi(BigDecimal value) {
        this.totaleAmmontareResi = value;
    }

    /**
     * Recupera il valore della propriet� totaleAmmontareAnnulli.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotaleAmmontareAnnulli() {
        return totaleAmmontareAnnulli;
    }

    /**
     * Imposta il valore della propriet� totaleAmmontareAnnulli.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotaleAmmontareAnnulli(BigDecimal value) {
        this.totaleAmmontareAnnulli = value;
    }

}
