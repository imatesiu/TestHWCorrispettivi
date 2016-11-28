//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.11.29 alle 12:44:43 AM CET 
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
 *         &lt;element name="AliquotaIVA" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}RateType"/>
 *         &lt;element name="Natura" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}NaturaType" minOccurs="0"/>
 *         &lt;element name="Ammontare" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}Amount2DecimalType"/>
 *         &lt;element name="Imposta" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}Amount2DecimalType"/>
 *         &lt;element name="RifNormativo" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}String100LatinType" minOccurs="0"/>
 *         &lt;element name="VentilazioneIVA" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}VentilazioneIVAType" minOccurs="0"/>
 *         &lt;element name="TotaleAmmontareResi" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}Amount2DecimalType" minOccurs="0"/>
 *         &lt;element name="TotaleAmmontareAnnulli" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}Amount2DecimalType" minOccurs="0"/>
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
    "aliquotaIVA",
    "natura",
    "ammontare",
    "imposta",
    "rifNormativo",
    "ventilazioneIVA",
    "totaleAmmontareResi",
    "totaleAmmontareAnnulli"
})
public class DatiRegistratoriTelematiciType {

    @XmlElement(name = "AliquotaIVA", required = true)
    protected BigDecimal aliquotaIVA;
    @XmlElement(name = "Natura")
    @XmlSchemaType(name = "string")
    protected NaturaType natura;
    @XmlElement(name = "Ammontare", required = true)
    protected BigDecimal ammontare;
    @XmlElement(name = "Imposta", required = true)
    protected BigDecimal imposta;
    @XmlElement(name = "RifNormativo")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String rifNormativo;
    @XmlElement(name = "VentilazioneIVA")
    @XmlSchemaType(name = "string")
    protected VentilazioneIVAType ventilazioneIVA;
    @XmlElement(name = "TotaleAmmontareResi")
    protected BigDecimal totaleAmmontareResi;
    @XmlElement(name = "TotaleAmmontareAnnulli")
    protected BigDecimal totaleAmmontareAnnulli;

    /**
     * Recupera il valore della proprietà aliquotaIVA.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAliquotaIVA() {
        return aliquotaIVA;
    }

    /**
     * Imposta il valore della proprietà aliquotaIVA.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAliquotaIVA(BigDecimal value) {
        this.aliquotaIVA = value;
    }

    /**
     * Recupera il valore della proprietà natura.
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
     * Imposta il valore della proprietà natura.
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
     * Recupera il valore della proprietà ammontare.
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
     * Imposta il valore della proprietà ammontare.
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
     * Recupera il valore della proprietà imposta.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getImposta() {
        return imposta;
    }

    /**
     * Imposta il valore della proprietà imposta.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setImposta(BigDecimal value) {
        this.imposta = value;
    }

    /**
     * Recupera il valore della proprietà rifNormativo.
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
     * Imposta il valore della proprietà rifNormativo.
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
     * Recupera il valore della proprietà ventilazioneIVA.
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
     * Imposta il valore della proprietà ventilazioneIVA.
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
     * Recupera il valore della proprietà totaleAmmontareResi.
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
     * Imposta il valore della proprietà totaleAmmontareResi.
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
     * Recupera il valore della proprietà totaleAmmontareAnnulli.
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
     * Imposta il valore della proprietà totaleAmmontareAnnulli.
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
