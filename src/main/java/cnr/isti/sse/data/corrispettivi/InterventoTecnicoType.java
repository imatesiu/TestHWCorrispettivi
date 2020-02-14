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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 * 				Blocco da valorizzare se si devono riportare informazioni relative ad
 * 				intervento tecnico effettuato sul dispositivo
 * 			
 * 
 * <p>Java class for InterventoTecnicoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InterventoTecnicoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CFTecnico" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}CodiceFiscaleType"/>
 *         &lt;element name="IdIVALaboratorio" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}IdFiscaleType"/>
 *         &lt;element name="DataOra" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Codice" type="{http://www.w3.org/2001/XMLSchema}normalizedString"/>
 *         &lt;element name="Note" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}String1000LatinType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InterventoTecnicoType", propOrder = {
    "cfTecnico",
    "idIVALaboratorio",
    "dataOra",
    "codice",
    "note",
    "numeroSw",
    "dataRelease"
    
})
public class InterventoTecnicoType {

    @XmlElement(name = "CFTecnico", required = true)
    protected String cfTecnico;
    @XmlElement(name = "IdIVALaboratorio", required = true)
    protected IdFiscaleType idIVALaboratorio;
    @XmlElement(name = "DataOra", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataOra;
    @XmlElement(name = "Codice", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String codice;
    @XmlElement(name = "Note")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String note;
    @XmlElement(name = "NumeroSw")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String numeroSw;
    @XmlElement(name = "DataRelease")
    protected XMLGregorianCalendar dataRelease;


    /**
     * Gets the value of the cfTecnico property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCFTecnico() {
        return cfTecnico;
    }

    /**
     * Sets the value of the cfTecnico property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCFTecnico(String value) {
        this.cfTecnico = value;
    }

    /**
     * Gets the value of the idIVALaboratorio property.
     * 
     * @return
     *     possible object is
     *     {@link IdFiscaleType }
     *     
     */
    public IdFiscaleType getIdIVALaboratorio() {
        return idIVALaboratorio;
    }

    /**
     * Sets the value of the idIVALaboratorio property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdFiscaleType }
     *     
     */
    public void setIdIVALaboratorio(IdFiscaleType value) {
        this.idIVALaboratorio = value;
    }

    /**
     * Gets the value of the dataOra property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataOra() {
        return dataOra;
    }

    /**
     * Sets the value of the dataOra property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataOra(XMLGregorianCalendar value) {
        this.dataOra = value;
    }

    /**
     * Gets the value of the codice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Sets the value of the codice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodice(String value) {
        this.codice = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

	public String getCfTecnico() {
		return cfTecnico;
	}

	public void setCfTecnico(String cfTecnico) {
		this.cfTecnico = cfTecnico;
	}

	public String getNumeroSw() {
		return numeroSw;
	}

	public void setNumeroSw(String numeroSw) {
		this.numeroSw = numeroSw;
	}

	public XMLGregorianCalendar getDataRelease() {
		return dataRelease;
	}

	public void setDataRelease(XMLGregorianCalendar dataRelease) {
		this.dataRelease = dataRelease;
	}
    
    

}
