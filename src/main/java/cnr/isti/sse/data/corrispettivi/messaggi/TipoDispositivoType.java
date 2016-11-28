//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.11.29 alle 12:39:14 AM CET 
//


package cnr.isti.sse.data.corrispettivi.messaggi;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per TipoDispositivoType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TipoDispositivoType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;length value="2"/>
 *     &lt;enumeration value="RT"/>
 *     &lt;enumeration value="DA"/>
 *     &lt;enumeration value="MC"/>
 *     &lt;enumeration value="DM"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TipoDispositivoType")
@XmlEnum
public enum TipoDispositivoType {


    /**
     * RT = Registratore Telematico
     * 
     */
    RT,

    /**
     * DA = Distributore Automatico
     * 
     */
    DA,

    /**
     * MC = Multi Cassa
     * 
     */
    MC,

    /**
     * DM = Dispositivo Mobile
     * 
     */
    DM;

    public String value() {
        return name();
    }

    public static TipoDispositivoType fromValue(String v) {
        return valueOf(v);
    }

}
