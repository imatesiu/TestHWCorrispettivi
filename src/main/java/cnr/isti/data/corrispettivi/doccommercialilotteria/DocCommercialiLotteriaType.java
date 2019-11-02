//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.11.02 alle 09:28:26 PM CET 
//


package cnr.isti.data.corrispettivi.doccommercialilotteria;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import cnr.isti.sse.data.corrispettivi.messaggi.signature.SignatureType;


/**
 * <p>Classe Java per DocCommercialiLotteriaType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DocCommercialiLotteriaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DatiTrasmissione" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/v1.0}DatiTrasmissioneType"/>
 *         &lt;element name="DocumentoCommerciale" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/v1.0}DocumentoCommercialeType" maxOccurs="100"/>
 *       &lt;/sequence>
 *       &lt;attribute name="simulazione" type="{http://www.w3.org/2001/XMLSchema}string" fixed="true" />
 *       &lt;attribute name="versione" use="required" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/v1.0}VersioneType" fixed="1.0" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

@XmlRootElement(name = "DocCommercialiLotteria", namespace = "http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/v1.0")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocCommercialiLotteriaType", propOrder = {
    "datiTrasmissione",
    "documentoCommerciale"
})
public class DocCommercialiLotteriaType {

    @XmlElement(name = "DatiTrasmissione", required = true)
    protected DatiTrasmissioneType datiTrasmissione;
    @XmlElement(name = "DocumentoCommerciale", required = true)
    protected List<DocumentoCommercialeType> documentoCommerciale;
    @XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected SignatureType signature;
    @XmlAttribute(name = "simulazione")
    protected String simulazione;
    @XmlAttribute(name = "versione", required = true)
    protected String versione;
    

    /**
     * Recupera il valore della proprietà datiTrasmissione.
     * 
     * @return
     *     possible object is
     *     {@link DatiTrasmissioneType }
     *     
     */
    public DatiTrasmissioneType getDatiTrasmissione() {
        return datiTrasmissione;
    }

    /**
     * Imposta il valore della proprietà datiTrasmissione.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiTrasmissioneType }
     *     
     */
    public void setDatiTrasmissione(DatiTrasmissioneType value) {
        this.datiTrasmissione = value;
    }

    /**
     * Gets the value of the documentoCommerciale property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentoCommerciale property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentoCommerciale().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoCommercialeType }
     * 
     * 
     */
    public List<DocumentoCommercialeType> getDocumentoCommerciale() {
        if (documentoCommerciale == null) {
            documentoCommerciale = new ArrayList<DocumentoCommercialeType>();
        }
        return this.documentoCommerciale;
    }

    /**
     * Recupera il valore della proprietà simulazione.
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
     * Imposta il valore della proprietà simulazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSimulazione(String value) {
        this.simulazione = value;
    }

    /**
     * Recupera il valore della proprietà versione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersione() {
        if (versione == null) {
            return "1.0";
        } else {
            return versione;
        }
    }

    /**
     * Imposta il valore della proprietà versione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersione(String value) {
        this.versione = value;
    }
    
    /**
     * Recupera il valore della propriet� signature.
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
     * Imposta il valore della propriet� signature.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureType }
     *     
     */
    public void setSignature(SignatureType value) {
        this.signature = value;
    }
}


