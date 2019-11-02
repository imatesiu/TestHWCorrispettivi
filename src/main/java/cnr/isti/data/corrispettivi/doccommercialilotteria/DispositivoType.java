//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.11.02 alle 09:28:26 PM CET 
//


package cnr.isti.data.corrispettivi.doccommercialilotteria;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per DispositivoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DispositivoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MatrTrasm" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/v1.0}MatrTrasmType"/>
 *         &lt;element name="MatrCassa" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/v1.0}MatrCassaType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DispositivoType", propOrder = {
    "matrTrasm",
    "matrCassa"
})
public class DispositivoType {

    @XmlElement(name = "MatrTrasm", required = true)
    protected String matrTrasm;
    @XmlElement(name = "MatrCassa")
    protected String matrCassa;

    /**
     * Recupera il valore della proprietà matrTrasm.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatrTrasm() {
        return matrTrasm;
    }

    /**
     * Imposta il valore della proprietà matrTrasm.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatrTrasm(String value) {
        this.matrTrasm = value;
    }

    /**
     * Recupera il valore della proprietà matrCassa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatrCassa() {
        return matrCassa;
    }

    /**
     * Imposta il valore della proprietà matrCassa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatrCassa(String value) {
        this.matrCassa = value;
    }

}
