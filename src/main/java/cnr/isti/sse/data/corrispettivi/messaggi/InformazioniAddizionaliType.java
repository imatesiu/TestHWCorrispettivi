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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per InformazioniAddizionaliType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="InformazioniAddizionaliType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice>
 *           &lt;element name="DA" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}InformazioniAddizionaliDAType"/>
 *           &lt;element name="RT" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}InformazioniAddizionaliRTType"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InformazioniAddizionaliType", propOrder = {
    "da",
    "rt"
})
public class InformazioniAddizionaliType {

    @XmlElement(name = "DA")
    protected InformazioniAddizionaliDAType da;
    @XmlElement(name = "RT")
    protected InformazioniAddizionaliRTType rt;

    /**
     * Recupera il valore della proprietà da.
     * 
     * @return
     *     possible object is
     *     {@link InformazioniAddizionaliDAType }
     *     
     */
    public InformazioniAddizionaliDAType getDA() {
        return da;
    }

    /**
     * Imposta il valore della proprietà da.
     * 
     * @param value
     *     allowed object is
     *     {@link InformazioniAddizionaliDAType }
     *     
     */
    public void setDA(InformazioniAddizionaliDAType value) {
        this.da = value;
    }

    /**
     * Recupera il valore della proprietà rt.
     * 
     * @return
     *     possible object is
     *     {@link InformazioniAddizionaliRTType }
     *     
     */
    public InformazioniAddizionaliRTType getRT() {
        return rt;
    }

    /**
     * Imposta il valore della proprietà rt.
     * 
     * @param value
     *     allowed object is
     *     {@link InformazioniAddizionaliRTType }
     *     
     */
    public void setRT(InformazioniAddizionaliRTType value) {
        this.rt = value;
    }

}
