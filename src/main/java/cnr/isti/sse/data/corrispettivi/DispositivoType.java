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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


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
 *         &lt;element name="Tipo" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}TipoDispositivoType"/>
 *         &lt;element name="IdDispositivo" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}IdDispositivoType"/>
 *         &lt;element name="GeoLocalizzazione" type="{http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/dati/v1.0}GeoLocType" minOccurs="0"/>
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
    "tipo",
    "idDispositivo",
    "geoLocalizzazione"
})
public class DispositivoType {

    @XmlElement(name = "Tipo", required = true)
    @XmlSchemaType(name = "string")
    protected TipoDispositivoType tipo;
    @XmlElement(name = "IdDispositivo", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String idDispositivo;
    @XmlElement(name = "GeoLocalizzazione")
    protected GeoLocType geoLocalizzazione;

    /**
     * Recupera il valore della propriet� tipo.
     * 
     * @return
     *     possible object is
     *     {@link TipoDispositivoType }
     *     
     */
    public TipoDispositivoType getTipo() {
        return tipo;
    }

    /**
     * Imposta il valore della propriet� tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoDispositivoType }
     *     
     */
    public void setTipo(TipoDispositivoType value) {
        this.tipo = value;
    }

    /**
     * Recupera il valore della propriet� idDispositivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdDispositivo() {
        return idDispositivo;
    }

    /**
     * Imposta il valore della propriet� idDispositivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdDispositivo(String value) {
        this.idDispositivo = value;
    }

    /**
     * Recupera il valore della propriet� geoLocalizzazione.
     * 
     * @return
     *     possible object is
     *     {@link GeoLocType }
     *     
     */
    public GeoLocType getGeoLocalizzazione() {
        return geoLocalizzazione;
    }

    /**
     * Imposta il valore della propriet� geoLocalizzazione.
     * 
     * @param value
     *     allowed object is
     *     {@link GeoLocType }
     *     
     */
    public void setGeoLocalizzazione(GeoLocType value) {
        this.geoLocalizzazione = value;
    }

}
