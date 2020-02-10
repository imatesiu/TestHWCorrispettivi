//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.03.26 at 08:39:20 PM CEST 
//


package cnr.isti.sse.data.corrispettivi;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * <p>Java class for DatiRegistratoriTelematiciType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
    "importoParziale",
    
    "totaleAmmontareResi",
    "totaleAmmontareAnnulli",
    "beniInSospeso",
    "nonRiscossoServizi",
    "nonRiscossoFatture",
    "totaleDaFattureRT",
    "nonRiscossoDCRaSSN",
    "nonRiscossoOmaggio",
    "codiceAttivita"
})
public class DatiRegistratoriTelematiciType {

    @XmlElement(name = "IVA")
    protected IVAType iva;
    @XmlElement(name = "Natura")
    protected NaturaType natura;
    @XmlElement(name = "VentilazioneIVA")
    protected VentilazioneIVAType ventilazioneIVA;
    @XmlElement(name = "Ammontare", required = true)
    protected BigDecimal ammontare;
    @XmlElement(name = "RifNormativo")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String rifNormativo;
    @XmlElement(name = "ImportoParziale")
    protected BigDecimal importoParziale;
    @XmlElement(name = "TotaleAmmontareResi")
    protected BigDecimal totaleAmmontareResi;
    @XmlElement(name = "TotaleAmmontareAnnulli")
    protected BigDecimal totaleAmmontareAnnulli;
    @XmlElement(name = "BeniInSospeso")
    protected BigDecimal beniInSospeso;
    
    @XmlElement(name = "NonRiscossoServizi")
    protected BigDecimal nonRiscossoServizi;
    @XmlElement(name = "NonRiscossoFatture")
    protected BigDecimal nonRiscossoFatture;
    @XmlElement(name = "TotaleDaFattureRT")
    protected BigDecimal totaleDaFattureRT;
    @XmlElement(name = "NonRiscossoDCRaSSN")
    protected BigDecimal nonRiscossoDCRaSSN;
    @XmlElement(name = "NonRiscossoOmaggio")
    protected BigDecimal nonRiscossoOmaggio;
    @XmlElement(name = "CodiceAttivita")
    protected BigDecimal codiceAttivita;
    /**
     * Gets the value of the iva property.
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
     * Sets the value of the iva property.
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
     * Gets the value of the natura property.
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
     * Sets the value of the natura property.
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
     * Gets the value of the ventilazioneIVA property.
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
     * Sets the value of the ventilazioneIVA property.
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
     * Gets the value of the ammontare property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmmontare() {
    	if(ammontare==null)
    		return new BigDecimal(0);
        return ammontare;
    }

    /**
     * Sets the value of the ammontare property.
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
     * Gets the value of the rifNormativo property.
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
     * Sets the value of the rifNormativo property.
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
     * Gets the value of the totaleAmmontareResi property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotaleAmmontareResi() {
    	if(totaleAmmontareResi==null)
    		return new BigDecimal(0);
        return totaleAmmontareResi;
    }

    /**
     * Sets the value of the totaleAmmontareResi property.
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
     * Gets the value of the totaleAmmontareAnnulli property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotaleAmmontareAnnulli() {
    	if(totaleAmmontareAnnulli==null)
    		return new BigDecimal(0);
        return totaleAmmontareAnnulli;
    }

    /**
     * Sets the value of the totaleAmmontareAnnulli property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotaleAmmontareAnnulli(BigDecimal value) {
        this.totaleAmmontareAnnulli = value;
    }

	public IVAType getIva() {
		return iva;
	}

	public void setIva(IVAType iva) {
		this.iva = iva;
	}

	public BigDecimal getImportoParziale() {
		if(importoParziale==null)
    		return new BigDecimal(0);
		return importoParziale;
	}

	public void setImportoParziale(BigDecimal importoParziale) {
		this.importoParziale = importoParziale;
	}

	public BigDecimal getBeniInSospeso() {
		if(beniInSospeso==null)
    		return new BigDecimal(0);
		return beniInSospeso;
	}

	public void setBeniInSospeso(BigDecimal beniInSospeso) {
		this.beniInSospeso = beniInSospeso;
	}

	public BigDecimal getNonRiscossoServizi() {
		if(nonRiscossoServizi==null)
    		return new BigDecimal(0);
		return nonRiscossoServizi;
	}

	public void setNonRiscossoServizi(BigDecimal nonRiscossoServizi) {
		this.nonRiscossoServizi = nonRiscossoServizi;
	}

	public BigDecimal getNonRiscossoFatture() {
		if(nonRiscossoFatture==null)
    		return new BigDecimal(0);
		return nonRiscossoFatture;
	}

	public void setNonRiscossoFatture(BigDecimal nonRiscossoFatture) {
		this.nonRiscossoFatture = nonRiscossoFatture;
	}

	public BigDecimal getTotaleDaFattureRT() {
		if(totaleDaFattureRT==null)
    		return new BigDecimal(0);
		return totaleDaFattureRT;
	}

	public void setTotaleDaFattureRT(BigDecimal totaleDaFattureRT) {
		this.totaleDaFattureRT = totaleDaFattureRT;
	}

	public BigDecimal getNonRiscossoDCRaSSN() {
		if(nonRiscossoDCRaSSN==null)
    		return new BigDecimal(0);
		return nonRiscossoDCRaSSN;
	}

	public void setNonRiscossoDCRaSSN(BigDecimal nonRiscossoDCRaSSN) {
		this.nonRiscossoDCRaSSN = nonRiscossoDCRaSSN;
	}

	public BigDecimal getNonRiscossoOmaggio() {
		if(nonRiscossoOmaggio==null)
    		return new BigDecimal(0);
		return nonRiscossoOmaggio;
	}

	public void setNonRiscossoOmaggio(BigDecimal nonRiscossoOmaggio) {
		this.nonRiscossoOmaggio = nonRiscossoOmaggio;
	}

	public BigDecimal getCodiceAttivita() {
		if(codiceAttivita==null)
    		return new BigDecimal(0);
		return codiceAttivita;
	}

	public void setCodiceAttivita(BigDecimal codiceAttivita) {
		this.codiceAttivita = codiceAttivita;
	}
    
    

}
