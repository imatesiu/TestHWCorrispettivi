//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.11.02 alle 09:28:26 PM CET 
//


package cnr.isti.data.corrispettivi.doccommercialilotteria;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per TipoPagamentoType.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="TipoPagamentoType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;length value="2"/>
 *     &lt;enumeration value="PC"/>
 *     &lt;enumeration value="PE"/>
 *     &lt;enumeration value="NR"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TipoPagamentoType")
@XmlEnum
public enum TipoPagamentoType {


    /**
     * Pagamento in contanti
     * 
     */
    PC,

    /**
     * Pagamento con strumenti elettronici
     * 
     */
    PE,

    /**
     * Corrispettivi non riscossi
     * 
     */
    NR;

    public String value() {
        return name();
    }

    public static TipoPagamentoType fromValue(String v) {
        return valueOf(v);
    }

}
