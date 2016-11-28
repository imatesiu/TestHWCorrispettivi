//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.11.29 alle 12:44:43 AM CET 
//


package cnr.isti.sse.data.corrispettivi;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cnr.isti.sse.data.corrispettivi package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DatiCorrispettivi_QNAME = new QName("http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0", "DatiCorrispettivi");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cnr.isti.sse.data.corrispettivi
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DatiCorrispettiviType }
     * 
     */
    public DatiCorrispettiviType createDatiCorrispettiviType() {
        return new DatiCorrispettiviType();
    }

    /**
     * Create an instance of {@link DatiTrasmissioneType }
     * 
     */
    public DatiTrasmissioneType createDatiTrasmissioneType() {
        return new DatiTrasmissioneType();
    }

    /**
     * Create an instance of {@link CumulatoType }
     * 
     */
    public CumulatoType createCumulatoType() {
        return new CumulatoType();
    }

    /**
     * Create an instance of {@link DispositivoType }
     * 
     */
    public DispositivoType createDispositivoType() {
        return new DispositivoType();
    }

    /**
     * Create an instance of {@link SegnalazioneType }
     * 
     */
    public SegnalazioneType createSegnalazioneType() {
        return new SegnalazioneType();
    }

    /**
     * Create an instance of {@link DatiDistributoriAutomaticiType }
     * 
     */
    public DatiDistributoriAutomaticiType createDatiDistributoriAutomaticiType() {
        return new DatiDistributoriAutomaticiType();
    }

    /**
     * Create an instance of {@link PeriodoType }
     * 
     */
    public PeriodoType createPeriodoType() {
        return new PeriodoType();
    }

    /**
     * Create an instance of {@link GeoLocType }
     * 
     */
    public GeoLocType createGeoLocType() {
        return new GeoLocType();
    }

    /**
     * Create an instance of {@link InterventoTecnicoType }
     * 
     */
    public InterventoTecnicoType createInterventoTecnicoType() {
        return new InterventoTecnicoType();
    }

    /**
     * Create an instance of {@link ElencoCorrispettiviType }
     * 
     */
    public ElencoCorrispettiviType createElencoCorrispettiviType() {
        return new ElencoCorrispettiviType();
    }

    /**
     * Create an instance of {@link PeriodoInattivoType }
     * 
     */
    public PeriodoInattivoType createPeriodoInattivoType() {
        return new PeriodoInattivoType();
    }

    /**
     * Create an instance of {@link DatiRegistratoriTelematiciType }
     * 
     */
    public DatiRegistratoriTelematiciType createDatiRegistratoriTelematiciType() {
        return new DatiRegistratoriTelematiciType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DatiCorrispettiviType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0", name = "DatiCorrispettivi")
    public JAXBElement<DatiCorrispettiviType> createDatiCorrispettivi(DatiCorrispettiviType value) {
        return new JAXBElement<DatiCorrispettiviType>(_DatiCorrispettivi_QNAME, DatiCorrispettiviType.class, null, value);
    }

}
