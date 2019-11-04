//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.11.02 alle 09:34:26 PM CET 
//


package cnr.isti.data.corrispettivi.doccommercialilotteria.messaggi;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import cnr.isti.sse.data.corrispettivi.messaggi.signature.SignatureType;


/**
 * <p>Classe Java per DocCommercialiLotteriaEsitoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DocCommercialiLotteriaEsitoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdOperazione" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DataOra" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="NumElementi" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="Esito" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/esito/v1.0}EsitoType"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element name="ListaErrori" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/esito/v1.0}ErroriType"/>
 *           &lt;element name="SegnalazioniDocComm" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/esito/v1.0}SegnalazioniDocCommType"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *       &lt;attribute name="versione" use="required" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/esito/v1.0}VersioneType" fixed="1.0" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement(name = "DocCommercialiLotteriaEsito", namespace = "http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/esito/v1.0")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocCommercialiLotteriaEsitoType", propOrder = {
    "idOperazione",
    "dataOra",
    "numElementi",
    "esito",
    "listaErrori",
    "segnalazioniDocComm",
    "signature"
})
public class DocCommercialiLotteriaEsitoType {

    @XmlElement(name = "IdOperazione", required = true)
    protected String idOperazione;
    @XmlElement(name = "DataOra", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataOra;
    @XmlElement(name = "NumElementi", required = true)
    protected BigInteger numElementi;
    @XmlElement(name = "Esito", required = true)
    @XmlSchemaType(name = "string")
    protected EsitoType esito;
    @XmlElement(name = "ListaErrori")
    protected List<ErroriType> listaErrori;
    @XmlElement(name = "SegnalazioniDocComm")
    protected SegnalazioniDocCommType segnalazioniDocComm;
    @XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected SignatureType signature;
    @XmlAttribute(name = "versione", required = true)
    protected String versione;

    /**
     * Recupera il valore della proprietà idOperazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdOperazione() {
        return idOperazione;
    }

    /**
     * Imposta il valore della proprietà idOperazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdOperazione(String value) {
        this.idOperazione = value;
    }

    /**
     * Recupera il valore della proprietà dataOra.
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
     * Imposta il valore della proprietà dataOra.
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
     * Recupera il valore della proprietà numElementi.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumElementi() {
        return numElementi;
    }

    /**
     * Imposta il valore della proprietà numElementi.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumElementi(BigInteger value) {
        this.numElementi = value;
    }

    /**
     * Recupera il valore della proprietà esito.
     * 
     * @return
     *     possible object is
     *     {@link EsitoType }
     *     
     */
    public EsitoType getEsito() {
        return esito;
    }

    /**
     * Imposta il valore della proprietà esito.
     * 
     * @param value
     *     allowed object is
     *     {@link EsitoType }
     *     
     */
    public void setEsito(EsitoType value) {
        this.esito = value;
    }

    /**
     * Recupera il valore della proprietà listaErrori.
     * 
     * @return
     *     possible object is
     *     {@link ErroriType }
     *     
     */
    public List<ErroriType> getListaErrori() {
    	if(listaErrori==null)
    		listaErrori = new ArrayList<ErroriType>();
        return listaErrori;
    }

    /**
     * Imposta il valore della proprietà listaErrori.
     * 
     * @param value
     *     allowed object is
     *     {@link ErroriType }
     *     
     */
    public void setListaErrori(List<ErroriType> value) {
        this.listaErrori = value;
    }

    /**
     * Recupera il valore della proprietà segnalazioniDocComm.
     * 
     * @return
     *     possible object is
     *     {@link SegnalazioniDocCommType }
     *     
     */
    public SegnalazioniDocCommType getSegnalazioniDocComm() {
        return segnalazioniDocComm;
    }

    /**
     * Imposta il valore della proprietà segnalazioniDocComm.
     * 
     * @param value
     *     allowed object is
     *     {@link SegnalazioniDocCommType }
     *     
     */
    public void setSegnalazioniDocComm(SegnalazioniDocCommType value) {
        this.segnalazioniDocComm = value;
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
