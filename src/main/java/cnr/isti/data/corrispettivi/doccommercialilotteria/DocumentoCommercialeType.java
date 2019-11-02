//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2019.11.02 alle 09:28:26 PM CET 
//


package cnr.isti.data.corrispettivi.doccommercialilotteria;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per DocumentoCommercialeType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DocumentoCommercialeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdCliente" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/v1.0}IdClienteType"/>
 *         &lt;element name="DataOra" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="NumeroProgressivo" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/v1.0}ProgressivoType"/>
 *         &lt;element name="Ammontare" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/v1.0}Amount2DecimalType"/>
 *         &lt;choice>
 *           &lt;element name="ResoAnnullo" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/v1.0}ResoAnnulloType"/>
 *           &lt;element name="Vendita" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/doccommercialilotteria/v1.0}VenditaType"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentoCommercialeType", propOrder = {
    "idCliente",
    "dataOra",
    "numeroProgressivo",
    "ammontare",
    "resoAnnullo",
    "vendita"
})
public class DocumentoCommercialeType {

    @XmlElement(name = "IdCliente", required = true)
    protected String idCliente;
    @XmlElement(name = "DataOra", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataOra;
    @XmlElement(name = "NumeroProgressivo", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String numeroProgressivo;
    @XmlElement(name = "Ammontare", required = true)
    protected BigDecimal ammontare;
    @XmlElement(name = "ResoAnnullo")
    protected ResoAnnulloType resoAnnullo;
    @XmlElement(name = "Vendita")
    protected VenditaType vendita;

    /**
     * Recupera il valore della proprietà idCliente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCliente() {
        return idCliente;
    }

    /**
     * Imposta il valore della proprietà idCliente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCliente(String value) {
        this.idCliente = value;
    }

    /**
     * Recupera il valore della proprietà dataOra.
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
     * Imposta il valore della proprietà dataOra.
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
     * Recupera il valore della proprietà numeroProgressivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroProgressivo() {
        return numeroProgressivo;
    }

    /**
     * Imposta il valore della proprietà numeroProgressivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroProgressivo(String value) {
        this.numeroProgressivo = value;
    }

    /**
     * Recupera il valore della proprietà ammontare.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAmmontare() {
        return ammontare;
    }

    /**
     * Imposta il valore della proprietà ammontare.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAmmontare(BigDecimal value) {
        this.ammontare = value;
    }

    /**
     * Recupera il valore della proprietà resoAnnullo.
     * 
     * @return
     *     possible object is
     *     {@link ResoAnnulloType }
     *     
     */
    public ResoAnnulloType getResoAnnullo() {
        return resoAnnullo;
    }

    /**
     * Imposta il valore della proprietà resoAnnullo.
     * 
     * @param value
     *     allowed object is
     *     {@link ResoAnnulloType }
     *     
     */
    public void setResoAnnullo(ResoAnnulloType value) {
        this.resoAnnullo = value;
    }

    /**
     * Recupera il valore della proprietà vendita.
     * 
     * @return
     *     possible object is
     *     {@link VenditaType }
     *     
     */
    public VenditaType getVendita() {
        return vendita;
    }

    /**
     * Imposta il valore della proprietà vendita.
     * 
     * @param value
     *     allowed object is
     *     {@link VenditaType }
     *     
     */
    public void setVendita(VenditaType value) {
        this.vendita = value;
    }

}
