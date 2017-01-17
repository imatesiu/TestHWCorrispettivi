//
// Questo file � stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andr� persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.01.17 alle 08:19:27 PM CET 
//


package cnr.isti.sse.data.corrispettivi;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per VentilazioneIVAType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="VentilazioneIVAType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SI"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "VentilazioneIVAType")
@XmlEnum
public enum VentilazioneIVAType {


    /**
     * Indica l'eventuale applicazione della ventilazione dell'IVA sui corrispettivi (ex DM 3495 del 24/02/1973)
     * 
     */
    SI;

    public String value() {
        return name();
    }

    public static VentilazioneIVAType fromValue(String v) {
        return valueOf(v);
    }

}
