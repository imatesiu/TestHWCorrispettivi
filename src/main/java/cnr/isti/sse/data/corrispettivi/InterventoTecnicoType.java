//
// Questo file � stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andr� persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.11.29 alle 12:44:43 AM CET 
//


package cnr.isti.sse.data.corrispettivi;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Blocco da valorizzare se si devono riportare informazioni relative ad intervento tecnico effettuato sul dispositivo
 * 
 * <p>Classe Java per InterventoTecnicoType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="InterventoTecnicoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CFTecnico" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}CodiceFiscaleType"/>
 *         &lt;element name="DataOra" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="Codice" type="{http://www.w3.org/2001/XMLSchema}normalizedString"/>
 *         &lt;element name="Note" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0}String1000LatinType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InterventoTecnicoType", propOrder = {
    "cfTecnico",
    "dataOra",
    "codice",
    "note"
})
public class InterventoTecnicoType {

    @XmlElement(name = "CFTecnico", required = true)
    protected String cfTecnico;
    @XmlElement(name = "DataOra", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataOra;
    @XmlElement(name = "Codice", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String codice;
    @XmlElement(name = "Note")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String note;

    /**
     * Recupera il valore della propriet� cfTecnico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCFTecnico() {
        return cfTecnico;
    }

    /**
     * Imposta il valore della propriet� cfTecnico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCFTecnico(String value) {
        this.cfTecnico = value;
    }

    /**
     * Recupera il valore della propriet� dataOra.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataOra() {
        return dataOra;
    }

    /**
     * Imposta il valore della propriet� dataOra.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataOra(XMLGregorianCalendar value) {
        this.dataOra = value;
    }

    /**
     * Recupera il valore della propriet� codice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il valore della propriet� codice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodice(String value) {
        this.codice = value;
    }

    /**
     * Recupera il valore della propriet� note.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Imposta il valore della propriet� note.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

}
