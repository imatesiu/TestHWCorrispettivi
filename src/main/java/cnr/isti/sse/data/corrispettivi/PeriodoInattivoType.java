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
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Blocco con informazioni sui dati contabili e fiscali dei corrispettivi
 * 
 * <p>Classe Java per PeriodoInattivoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="PeriodoInattivoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Dal" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Al" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PeriodoInattivoType", propOrder = {
    "dal",
    "al"
})
public class PeriodoInattivoType {

    @XmlElement(name = "Dal", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dal;
    @XmlElement(name = "Al", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar al;

    /**
     * Recupera il valore della propriet� dal.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDal() {
        return dal;
    }

    /**
     * Imposta il valore della propriet� dal.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDal(XMLGregorianCalendar value) {
        this.dal = value;
    }

    /**
     * Recupera il valore della propriet� al.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAl() {
        return al;
    }

    /**
     * Imposta il valore della propriet� al.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAl(XMLGregorianCalendar value) {
        this.al = value;
    }

}
