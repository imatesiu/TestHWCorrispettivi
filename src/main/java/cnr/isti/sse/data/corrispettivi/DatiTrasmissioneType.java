//
// Questo file � stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andr� persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.01.17 alle 08:19:27 PM CET 
//


package cnr.isti.sse.data.corrispettivi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Blocco con informazioni su tipo di trasmissione e soggetto trasmittente
 * 
 * <p>Classe Java per DatiTrasmissioneType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DatiTrasmissioneType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Progressivo" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}Integer15Type"/>
 *         &lt;element name="Formato" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}FormatoTrasmissioneType"/>
 *         &lt;element name="Dispositivo" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}DispositivoType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiTrasmissioneType", propOrder = {
    "progressivo",
    "formato",
    "dispositivo"
})
public class DatiTrasmissioneType {

    @XmlElement(name = "Progressivo")
    @XmlSchemaType(name = "positiveInteger")
    protected long progressivo;
    @XmlElement(name = "Formato", required = true)
    @XmlSchemaType(name = "string")
    protected FormatoTrasmissioneType formato;
    @XmlElement(name = "Dispositivo")
    protected DispositivoType dispositivo;

    /**
     * Recupera il valore della propriet� progressivo.
     * 
     */
    public long getProgressivo() {
        return progressivo;
    }

    /**
     * Imposta il valore della propriet� progressivo.
     * 
     */
    public void setProgressivo(long value) {
        this.progressivo = value;
    }

    /**
     * Recupera il valore della propriet� formato.
     * 
     * @return
     *     possible object is
     *     {@link FormatoTrasmissioneType }
     *     
     */
    public FormatoTrasmissioneType getFormato() {
        return formato;
    }

    /**
     * Imposta il valore della propriet� formato.
     * 
     * @param value
     *     allowed object is
     *     {@link FormatoTrasmissioneType }
     *     
     */
    public void setFormato(FormatoTrasmissioneType value) {
        this.formato = value;
    }

    /**
     * Recupera il valore della propriet� dispositivo.
     * 
     * @return
     *     possible object is
     *     {@link DispositivoType }
     *     
     */
    public DispositivoType getDispositivo() {
        return dispositivo;
    }

    /**
     * Imposta il valore della propriet� dispositivo.
     * 
     * @param value
     *     allowed object is
     *     {@link DispositivoType }
     *     
     */
    public void setDispositivo(DispositivoType value) {
        this.dispositivo = value;
    }

}
