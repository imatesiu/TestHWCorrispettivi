//
// Questo file � stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andr� persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.11.29 alle 12:39:14 AM CET 
//


package cnr.isti.sse.data.corrispettivi.messaggi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import datanew.SignatureType;


/**
 * <p>Classe Java per AttivaDispositivoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="AttivaDispositivoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Gestore" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}IdFiscaleType"/>
 *         &lt;element name="TecnicoVerificatore" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}TecnicoVerificatoreType"/>
 *         &lt;element name="GeoLocalizzazione" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}GeoLocType" minOccurs="0"/>
 *         &lt;element name="InformazioniAddizionali" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}InformazioniAddizionaliType" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="versione" use="required" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}VersioneType" fixed="1.0" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlRootElement(name = "AttivaDispositivo", namespace = "http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AttivaDispositivoType", propOrder = {
    "gestore",
    "tecnicoVerificatore",
    "geoLocalizzazione",
    "informazioniAddizionali",
    "signature"
})
public class AttivaDispositivoType {

    @XmlElement(name = "Gestore", required = true)
    protected IdFiscaleType gestore;
    @XmlElement(name = "TecnicoVerificatore", required = true)
    protected TecnicoVerificatoreType tecnicoVerificatore;
    @XmlElement(name = "GeoLocalizzazione")
    protected GeoLocType geoLocalizzazione;
    @XmlElement(name = "InformazioniAddizionali")
    protected InformazioniAddizionaliType informazioniAddizionali;
    @XmlElement(name = "Signature", namespace = "http://www.w3.org/2000/09/xmldsig#")
    protected SignatureType signature;
    @XmlAttribute(name = "versione", required = true)
    protected String versione;

    /**
     * Recupera il valore della propriet� gestore.
     * 
     * @return
     *     possible object is
     *     {@link IdFiscaleType }
     *     
     */
    public IdFiscaleType getGestore() {
        return gestore;
    }

    /**
     * Imposta il valore della propriet� gestore.
     * 
     * @param value
     *     allowed object is
     *     {@link IdFiscaleType }
     *     
     */
    public void setGestore(IdFiscaleType value) {
        this.gestore = value;
    }

    /**
     * Recupera il valore della propriet� tecnicoVerificatore.
     * 
     * @return
     *     possible object is
     *     {@link TecnicoVerificatoreType }
     *     
     */
    public TecnicoVerificatoreType getTecnicoVerificatore() {
        return tecnicoVerificatore;
    }

    /**
     * Imposta il valore della propriet� tecnicoVerificatore.
     * 
     * @param value
     *     allowed object is
     *     {@link TecnicoVerificatoreType }
     *     
     */
    public void setTecnicoVerificatore(TecnicoVerificatoreType value) {
        this.tecnicoVerificatore = value;
    }

    /**
     * Recupera il valore della propriet� geoLocalizzazione.
     * 
     * @return
     *     possible object is
     *     {@link GeoLocType }
     *     
     */
    public GeoLocType getGeoLocalizzazione() {
        return geoLocalizzazione;
    }

    /**
     * Imposta il valore della propriet� geoLocalizzazione.
     * 
     * @param value
     *     allowed object is
     *     {@link GeoLocType }
     *     
     */
    public void setGeoLocalizzazione(GeoLocType value) {
        this.geoLocalizzazione = value;
    }

    /**
     * Recupera il valore della propriet� informazioniAddizionali.
     * 
     * @return
     *     possible object is
     *     {@link InformazioniAddizionaliType }
     *     
     */
    public InformazioniAddizionaliType getInformazioniAddizionali() {
        return informazioniAddizionali;
    }

    /**
     * Imposta il valore della propriet� informazioniAddizionali.
     * 
     * @param value
     *     allowed object is
     *     {@link InformazioniAddizionaliType }
     *     
     */
    public void setInformazioniAddizionali(InformazioniAddizionaliType value) {
        this.informazioniAddizionali = value;
    }

    /**
     * Recupera il valore della propriet� versione.
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
     * Imposta il valore della propriet� versione.
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
