//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.11.29 alle 12:39:14 AM CET 
//


package cnr.isti.sse.data.corrispettivi.messaggi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per RichiestaCertificatoDispositivoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="RichiestaCertificatoDispositivoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Csr" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="Dispositivo" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}CensimentoDispositivoType"/>
 *         &lt;element name="TecnicoVerificatore" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}TecnicoVerificatoreType"/>
 *       &lt;/sequence>
 *       &lt;attribute name="versione" use="required" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}VersioneType" fixed="1.0" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RichiestaCertificatoDispositivoType", propOrder = {
    "csr",
    "dispositivo",
    "tecnicoVerificatore"
})
public class RichiestaCertificatoDispositivoType {

    @XmlElement(name = "Csr", required = true)
    protected byte[] csr;
    @XmlElement(name = "Dispositivo", required = true)
    protected CensimentoDispositivoType dispositivo;
    @XmlElement(name = "TecnicoVerificatore", required = true)
    protected TecnicoVerificatoreType tecnicoVerificatore;
    @XmlAttribute(name = "versione", required = true)
    protected String versione;

    /**
     * Recupera il valore della proprietà csr.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getCsr() {
        return csr;
    }

    /**
     * Imposta il valore della proprietà csr.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setCsr(byte[] value) {
        this.csr = value;
    }

    /**
     * Recupera il valore della proprietà dispositivo.
     * 
     * @return
     *     possible object is
     *     {@link CensimentoDispositivoType }
     *     
     */
    public CensimentoDispositivoType getDispositivo() {
        return dispositivo;
    }

    /**
     * Imposta il valore della proprietà dispositivo.
     * 
     * @param value
     *     allowed object is
     *     {@link CensimentoDispositivoType }
     *     
     */
    public void setDispositivo(CensimentoDispositivoType value) {
        this.dispositivo = value;
    }

    /**
     * Recupera il valore della proprietà tecnicoVerificatore.
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
     * Imposta il valore della proprietà tecnicoVerificatore.
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

}
