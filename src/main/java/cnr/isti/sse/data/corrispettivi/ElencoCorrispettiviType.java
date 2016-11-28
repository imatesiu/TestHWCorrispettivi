//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.11.29 alle 12:44:43 AM CET 
//


package cnr.isti.sse.data.corrispettivi;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ElencoCorrispettiviType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ElencoCorrispettiviType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Riepilogo" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}DatiRegistratoriTelematiciType" maxOccurs="20"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ElencoCorrispettiviType", propOrder = {
    "riepilogo"
})
public class ElencoCorrispettiviType {

    @XmlElement(name = "Riepilogo", required = true)
    protected List<DatiRegistratoriTelematiciType> riepilogo;

    /**
     * Gets the value of the riepilogo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the riepilogo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRiepilogo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DatiRegistratoriTelematiciType }
     * 
     * 
     */
    public List<DatiRegistratoriTelematiciType> getRiepilogo() {
        if (riepilogo == null) {
            riepilogo = new ArrayList<DatiRegistratoriTelematiciType>();
        }
        return this.riepilogo;
    }

}
