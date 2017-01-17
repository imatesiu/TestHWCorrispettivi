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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per DatiDistributoriAutomaticiType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DatiDistributoriAutomaticiType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="Periodo" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}PeriodoType"/>
 *         &lt;element name="Cumulato" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}CumulatoType"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiDistributoriAutomaticiType", propOrder = {
    "periodo",
    "cumulato"
})
public class DatiDistributoriAutomaticiType {

    @XmlElement(name = "Periodo")
    protected PeriodoType periodo;
    @XmlElement(name = "Cumulato")
    protected CumulatoType cumulato;

    /**
     * Recupera il valore della propriet� periodo.
     * 
     * @return
     *     possible object is
     *     {@link PeriodoType }
     *     
     */
    public PeriodoType getPeriodo() {
        return periodo;
    }

    /**
     * Imposta il valore della propriet� periodo.
     * 
     * @param value
     *     allowed object is
     *     {@link PeriodoType }
     *     
     */
    public void setPeriodo(PeriodoType value) {
        this.periodo = value;
    }

    /**
     * Recupera il valore della propriet� cumulato.
     * 
     * @return
     *     possible object is
     *     {@link CumulatoType }
     *     
     */
    public CumulatoType getCumulato() {
        return cumulato;
    }

    /**
     * Imposta il valore della propriet� cumulato.
     * 
     * @param value
     *     allowed object is
     *     {@link CumulatoType }
     *     
     */
    public void setCumulato(CumulatoType value) {
        this.cumulato = value;
    }

}
