//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.11.02 alle 09:34:26 PM CET 
//


package cnr.isti.sse.data.corrispettivi.doccommercialilotteria.messaggi;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per EsitoType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="EsitoType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;length value="2"/>
 *     &lt;enumeration value="SC"/>
 *     &lt;enumeration value="SE"/>
 *     &lt;enumeration value="AC"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "EsitoType")
@XmlEnum
public enum EsitoType {


    /**
     * Fornitura interamente scartata
     * 
     */
    SC,

    /**
     * Fornituta accolta con segnalazione su almeno un documento commerciale
     * 
     */
    SE,

    /**
     * Fornituta acquisita correttamente
     * 
     */
    AC;

    public String value() {
        return name();
    }

    public static EsitoType fromValue(String v) {
        return valueOf(v);
    }

}
