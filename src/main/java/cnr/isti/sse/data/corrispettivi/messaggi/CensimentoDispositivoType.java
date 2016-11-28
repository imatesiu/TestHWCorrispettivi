//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.11.29 alle 12:39:14 AM CET 
//


package cnr.isti.sse.data.corrispettivi.messaggi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per CensimentoDispositivoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="CensimentoDispositivoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Tipo" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}TipoDispositivoType"/>
 *         &lt;element name="MarchioFabbricante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Modello" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RifApprovazioneDispositivo" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}RifApprovazioneType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CensimentoDispositivoType", propOrder = {
    "tipo",
    "marchioFabbricante",
    "modello",
    "rifApprovazioneDispositivo"
})
public class CensimentoDispositivoType {

    @XmlElement(name = "Tipo", required = true)
    @XmlSchemaType(name = "string")
    protected TipoDispositivoType tipo;
    @XmlElement(name = "MarchioFabbricante")
    protected String marchioFabbricante;
    @XmlElement(name = "Modello")
    protected String modello;
    @XmlElement(name = "RifApprovazioneDispositivo")
    protected RifApprovazioneType rifApprovazioneDispositivo;

    /**
     * Recupera il valore della proprietà tipo.
     * 
     * @return
     *     possible object is
     *     {@link TipoDispositivoType }
     *     
     */
    public TipoDispositivoType getTipo() {
        return tipo;
    }

    /**
     * Imposta il valore della proprietà tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoDispositivoType }
     *     
     */
    public void setTipo(TipoDispositivoType value) {
        this.tipo = value;
    }

    /**
     * Recupera il valore della proprietà marchioFabbricante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarchioFabbricante() {
        return marchioFabbricante;
    }

    /**
     * Imposta il valore della proprietà marchioFabbricante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarchioFabbricante(String value) {
        this.marchioFabbricante = value;
    }

    /**
     * Recupera il valore della proprietà modello.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModello() {
        return modello;
    }

    /**
     * Imposta il valore della proprietà modello.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModello(String value) {
        this.modello = value;
    }

    /**
     * Recupera il valore della proprietà rifApprovazioneDispositivo.
     * 
     * @return
     *     possible object is
     *     {@link RifApprovazioneType }
     *     
     */
    public RifApprovazioneType getRifApprovazioneDispositivo() {
        return rifApprovazioneDispositivo;
    }

    /**
     * Imposta il valore della proprietà rifApprovazioneDispositivo.
     * 
     * @param value
     *     allowed object is
     *     {@link RifApprovazioneType }
     *     
     */
    public void setRifApprovazioneDispositivo(RifApprovazioneType value) {
        this.rifApprovazioneDispositivo = value;
    }

}
