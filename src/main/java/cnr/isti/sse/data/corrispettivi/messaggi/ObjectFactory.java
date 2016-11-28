//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2016.11.29 alle 12:39:14 AM CET 
//


package cnr.isti.sse.data.corrispettivi.messaggi;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cnr.isti.sse.data package. 
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

    private final static QName _RichiestaCertificatoDispositivo_QNAME = new QName("http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0", "RichiestaCertificatoDispositivo");
    private final static QName _EventoDispositivo_QNAME = new QName("http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0", "EventoDispositivo");
    private final static QName _EsitoOperazione_QNAME = new QName("http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0", "EsitoOperazione");
    private final static QName _AttivaDispositivo_QNAME = new QName("http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0", "AttivaDispositivo");
    private final static QName _EsitoRichiestaCertificatoDispositivo_QNAME = new QName("http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0", "EsitoRichiestaCertificatoDispositivo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cnr.isti.sse.data
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RichiestaCertificatoDispositivoType }
     * 
     */
    public RichiestaCertificatoDispositivoType createRichiestaCertificatoDispositivoType() {
        return new RichiestaCertificatoDispositivoType();
    }

    /**
     * Create an instance of {@link EventoDispositivoType }
     * 
     */
    public EventoDispositivoType createEventoDispositivoType() {
        return new EventoDispositivoType();
    }

    /**
     * Create an instance of {@link EsitoOperazioneType }
     * 
     */
    public EsitoOperazioneType createEsitoOperazioneType() {
        return new EsitoOperazioneType();
    }

    /**
     * Create an instance of {@link EsitoRichiestaCertificatoDispositivoType }
     * 
     */
    public EsitoRichiestaCertificatoDispositivoType createEsitoRichiestaCertificatoDispositivoType() {
        return new EsitoRichiestaCertificatoDispositivoType();
    }

    /**
     * Create an instance of {@link AttivaDispositivoType }
     * 
     */
    public AttivaDispositivoType createAttivaDispositivoType() {
        return new AttivaDispositivoType();
    }

    /**
     * Create an instance of {@link DettaglioEventoDispositivoType }
     * 
     */
    public DettaglioEventoDispositivoType createDettaglioEventoDispositivoType() {
        return new DettaglioEventoDispositivoType();
    }

    /**
     * Create an instance of {@link InformazioniAddizionaliRTType }
     * 
     */
    public InformazioniAddizionaliRTType createInformazioniAddizionaliRTType() {
        return new InformazioniAddizionaliRTType();
    }

    /**
     * Create an instance of {@link ErroreType }
     * 
     */
    public ErroreType createErroreType() {
        return new ErroreType();
    }

    /**
     * Create an instance of {@link ErroriType }
     * 
     */
    public ErroriType createErroriType() {
        return new ErroriType();
    }

    /**
     * Create an instance of {@link TecnicoVerificatoreType }
     * 
     */
    public TecnicoVerificatoreType createTecnicoVerificatoreType() {
        return new TecnicoVerificatoreType();
    }

    /**
     * Create an instance of {@link CensimentoDispositivoType }
     * 
     */
    public CensimentoDispositivoType createCensimentoDispositivoType() {
        return new CensimentoDispositivoType();
    }

    /**
     * Create an instance of {@link RifApprovazioneType }
     * 
     */
    public RifApprovazioneType createRifApprovazioneType() {
        return new RifApprovazioneType();
    }

    /**
     * Create an instance of {@link InformazioniAddizionaliDAType }
     * 
     */
    public InformazioniAddizionaliDAType createInformazioniAddizionaliDAType() {
        return new InformazioniAddizionaliDAType();
    }

    /**
     * Create an instance of {@link GeoLocType }
     * 
     */
    public GeoLocType createGeoLocType() {
        return new GeoLocType();
    }

    /**
     * Create an instance of {@link IdFiscaleType }
     * 
     */
    public IdFiscaleType createIdFiscaleType() {
        return new IdFiscaleType();
    }

    /**
     * Create an instance of {@link InformazioniAddizionaliType }
     * 
     */
    public InformazioniAddizionaliType createInformazioniAddizionaliType() {
        return new InformazioniAddizionaliType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RichiestaCertificatoDispositivoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0", name = "RichiestaCertificatoDispositivo")
    public JAXBElement<RichiestaCertificatoDispositivoType> createRichiestaCertificatoDispositivo(RichiestaCertificatoDispositivoType value) {
        return new JAXBElement<RichiestaCertificatoDispositivoType>(_RichiestaCertificatoDispositivo_QNAME, RichiestaCertificatoDispositivoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EventoDispositivoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0", name = "EventoDispositivo")
    public JAXBElement<EventoDispositivoType> createEventoDispositivo(EventoDispositivoType value) {
        return new JAXBElement<EventoDispositivoType>(_EventoDispositivo_QNAME, EventoDispositivoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EsitoOperazioneType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0", name = "EsitoOperazione")
    public JAXBElement<EsitoOperazioneType> createEsitoOperazione(EsitoOperazioneType value) {
        return new JAXBElement<EsitoOperazioneType>(_EsitoOperazione_QNAME, EsitoOperazioneType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AttivaDispositivoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0", name = "AttivaDispositivo")
    public JAXBElement<AttivaDispositivoType> createAttivaDispositivo(AttivaDispositivoType value) {
        return new JAXBElement<AttivaDispositivoType>(_AttivaDispositivo_QNAME, AttivaDispositivoType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EsitoRichiestaCertificatoDispositivoType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ivaservizi.agenziaentrate.gov.it/docs/xsd/corrispettivi/v1.0", name = "EsitoRichiestaCertificatoDispositivo")
    public JAXBElement<EsitoRichiestaCertificatoDispositivoType> createEsitoRichiestaCertificatoDispositivo(EsitoRichiestaCertificatoDispositivoType value) {
        return new JAXBElement<EsitoRichiestaCertificatoDispositivoType>(_EsitoRichiestaCertificatoDispositivo_QNAME, EsitoRichiestaCertificatoDispositivoType.class, null, value);
    }

}
