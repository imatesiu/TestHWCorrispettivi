//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.11.02 alle 09:34:26 PM CET 
//


package cnr.isti.data.corrispettivi.doccommercialilotteria.messaggi;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per SegnalazioniDocCommType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="SegnalazioniDocCommType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Segnalazione" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/esito/v1.0}SegnalazioneType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SegnalazioniDocCommType", propOrder = {
    "segnalazione"
})
public class SegnalazioniDocCommType {

    @XmlElement(name = "Segnalazione", required = true)
    protected List<SegnalazioneType> segnalazione;

    /**
     * Gets the value of the segnalazione property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the segnalazione property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSegnalazione().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SegnalazioneType }
     * 
     * 
     */
    public List<SegnalazioneType> getSegnalazione() {
        if (segnalazione == null) {
            segnalazione = new ArrayList<SegnalazioneType>();
        }
        return this.segnalazione;
    }

}
