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
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per InformazioniAddizionaliRTType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="InformazioniAddizionaliRTType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MatricolaRegistratoreDiCassa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DataMessaInServizio" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InformazioniAddizionaliRTType", propOrder = {
    "matricolaRegistratoreDiCassa",
    "dataMessaInServizio"
})
public class InformazioniAddizionaliRTType {

    @XmlElement(name = "MatricolaRegistratoreDiCassa")
    protected String matricolaRegistratoreDiCassa;
    @XmlElement(name = "DataMessaInServizio")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataMessaInServizio;

    /**
     * Recupera il valore della proprietà matricolaRegistratoreDiCassa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatricolaRegistratoreDiCassa() {
        return matricolaRegistratoreDiCassa;
    }

    /**
     * Imposta il valore della proprietà matricolaRegistratoreDiCassa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatricolaRegistratoreDiCassa(String value) {
        this.matricolaRegistratoreDiCassa = value;
    }

    /**
     * Recupera il valore della proprietà dataMessaInServizio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataMessaInServizio() {
        return dataMessaInServizio;
    }

    /**
     * Imposta il valore della proprietà dataMessaInServizio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataMessaInServizio(XMLGregorianCalendar value) {
        this.dataMessaInServizio = value;
    }

}
