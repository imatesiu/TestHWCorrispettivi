//
// Questo file Ŕ stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrÓ persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.11.22 alle 11:15:36 PM CET 
//


package datanew;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per DatiCorrispettiviType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DatiCorrispettiviType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Trasmissione" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}DatiTrasmissioneType"/>
 *         &lt;element name="PeriodoInattivo" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}PeriodoInattivoType" minOccurs="0"/>
 *         &lt;element name="DataOraRilevazione" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;choice>
 *           &lt;element name="DatiRT" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}ElencoCorrispettiviType"/>
 *           &lt;element name="DatiDA" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}DatiDistributoriAutomaticiType"/>
 *         &lt;/choice>
 *         &lt;element name="InterventoTecnico" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}InterventoTecnicoType" maxOccurs="50" minOccurs="0"/>
 *         &lt;element name="Segnalazione" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}SegnalazioneType" maxOccurs="50" minOccurs="0"/>
 *         &lt;element ref="{http://www.w3.org/2000/09/xmldsig#}Signature"/>
 *       &lt;/sequence>
 *       &lt;attribute name="versione" use="required" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}FormatoTrasmissioneType" />
 *       &lt;attribute name="simulazione" type="{http://www.w3.org/2001/XMLSchema}string" fixed="true" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiCorrispettiviType", propOrder = {
    "trasmissione",
    "periodoInattivo",
    "dataOraRilevazione",
    "datiRT",
    "datiDA",
    "interventoTecnico",
    "segnalazione",
    "signature"
})
public class DatiCorrispettiviType {

    @XmlElement(name = "Trasmissione", required = true)
    protected DatiTrasmissioneType trasmissione;
    @XmlElement(name = "PeriodoInattivo")
    protected PeriodoInattivoType periodoInattivo;
    @XmlElement(name = "DataOraRilevazione", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataOraRilevazione;
    @XmlElement(name = "DatiRT")
    protected ElencoCorrispettiviType datiRT;
    @XmlElement(name = "DatiDA")
    protected DatiDistributoriAutomaticiType datiDA;
    @XmlElement(name = "InterventoTecnico")
    protected List<InterventoTecnicoType> interventoTecnico;
    @XmlElement(name = "Segnalazione")
    protected List<SegnalazioneType> segnalazione;
    @XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
    protected SignatureType signature;
    @XmlAttribute(name = "versione", required = true)
    protected FormatoTrasmissioneType versione;
    @XmlAttribute(name = "simulazione")
    protected String simulazione;

    /**
     * Recupera il valore della proprietÓ trasmissione.
     * 
     * @return
     *     possible object is
     *     {@link DatiTrasmissioneType }
     *     
     */
    public DatiTrasmissioneType getTrasmissione() {
        return trasmissione;
    }

    /**
     * Imposta il valore della proprietÓ trasmissione.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiTrasmissioneType }
     *     
     */
    public void setTrasmissione(DatiTrasmissioneType value) {
        this.trasmissione = value;
    }

    /**
     * Recupera il valore della proprietÓ periodoInattivo.
     * 
     * @return
     *     possible object is
     *     {@link PeriodoInattivoType }
     *     
     */
    public PeriodoInattivoType getPeriodoInattivo() {
        return periodoInattivo;
    }

    /**
     * Imposta il valore della proprietÓ periodoInattivo.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodoInattivoType }
     *     
     */
    public void setPeriodoInattivo(PeriodoInattivoType value) {
        this.periodoInattivo = value;
    }

    /**
     * Recupera il valore della proprietÓ dataOraRilevazione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataOraRilevazione() {
        return dataOraRilevazione;
    }

    /**
     * Imposta il valore della proprietÓ dataOraRilevazione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataOraRilevazione(XMLGregorianCalendar value) {
        this.dataOraRilevazione = value;
    }

    /**
     * Recupera il valore della proprietÓ datiRT.
     * 
     * @return
     *     possible object is
     *     {@link ElencoCorrispettiviType }
     *     
     */
    public ElencoCorrispettiviType getDatiRT() {
        return datiRT;
    }

    /**
     * Imposta il valore della proprietÓ datiRT.
     * 
     * @param value
     *     allowed object is
     *     {@link ElencoCorrispettiviType }
     *     
     */
    public void setDatiRT(ElencoCorrispettiviType value) {
        this.datiRT = value;
    }

    /**
     * Recupera il valore della proprietÓ datiDA.
     * 
     * @return
     *     possible object is
     *     {@link DatiDistributoriAutomaticiType }
     *     
     */
    public DatiDistributoriAutomaticiType getDatiDA() {
        return datiDA;
    }

    /**
     * Imposta il valore della proprietÓ datiDA.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiDistributoriAutomaticiType }
     *     
     */
    public void setDatiDA(DatiDistributoriAutomaticiType value) {
        this.datiDA = value;
    }

    /**
     * Gets the value of the interventoTecnico property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the interventoTecnico property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInterventoTecnico().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InterventoTecnicoType }
     * 
     * 
     */
    public List<InterventoTecnicoType> getInterventoTecnico() {
        if (interventoTecnico == null) {
            interventoTecnico = new ArrayList<InterventoTecnicoType>();
        }
        return this.interventoTecnico;
    }

    /**
     * Gets the value of the segnalazione property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the segnalazione property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSegnalazione().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SegnalazioneType }
     * 
     * 
     */
    public List<SegnalazioneType> getSegnalazione() {
        if (segnalazione == null) {
            segnalazione = new ArrayList<SegnalazioneType>();
        }
        return this.segnalazione;
    }

    /**
     * Recupera il valore della proprietÓ signature.
     * 
     * @return
     *     possible object is
     *     {@link SignatureType }
     *     
     */
    public SignatureType getSignature() {
        return signature;
    }

    /**
     * Imposta il valore della proprietÓ signature.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureType }
     *     
     */
    public void setSignature(SignatureType value) {
        this.signature = value;
    }

    /**
     * Recupera il valore della proprietÓ versione.
     * 
     * @return
     *     possible object is
     *     {@link FormatoTrasmissioneType }
     *     
     */
    public FormatoTrasmissioneType getVersione() {
        return versione;
    }

    /**
     * Imposta il valore della proprietÓ versione.
     * 
     * @param value
     *     allowed object is
     *     {@link FormatoTrasmissioneType }
     *     
     */
    public void setVersione(FormatoTrasmissioneType value) {
        this.versione = value;
    }

    /**
     * Recupera il valore della proprietÓ simulazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSimulazione() {
        if (simulazione == null) {
            return "true";
        } else {
            return simulazione;
        }
    }

    /**
     * Imposta il valore della proprietÓ simulazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSimulazione(String value) {
        this.simulazione = value;
    }

}
