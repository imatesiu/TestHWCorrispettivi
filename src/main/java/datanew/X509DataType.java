//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.11.22 alle 11:15:36 PM CET 
//


package datanew;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per X509DataType complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="X509DataType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="X509IssuerSerial" type="{http://www.w3.org/2000/09/xmldsig#}X509IssuerSerialType"/>
 *         &lt;element name="X509SKI" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="X509SubjectName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="X509Certificate" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="X509CRL" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "X509DataType", namespace = "http://www.w3.org/2000/09/xmldsig#", propOrder = {
    "x509IssuerSerial",
    "x509SKI",
    "x509SubjectName",
    "x509Certificate",
    "x509CRL"
})
public class X509DataType {

    @XmlElement(name = "X509IssuerSerial", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
    protected X509IssuerSerialType x509IssuerSerial;
    @XmlElement(name = "X509SKI", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
    protected byte[] x509SKI;
    @XmlElement(name = "X509SubjectName", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
    protected String x509SubjectName;
    @XmlElement(name = "X509Certificate", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
    protected byte[] x509Certificate;
    @XmlElement(name = "X509CRL", namespace = "http://www.w3.org/2000/09/xmldsig#", required = true)
    protected byte[] x509CRL;

    /**
     * Recupera il valore della proprietà x509IssuerSerial.
     * 
     * @return
     *     possible object is
     *     {@link X509IssuerSerialType }
     *     
     */
    public X509IssuerSerialType getX509IssuerSerial() {
        return x509IssuerSerial;
    }

    /**
     * Imposta il valore della proprietà x509IssuerSerial.
     * 
     * @param value
     *     allowed object is
     *     {@link X509IssuerSerialType }
     *     
     */
    public void setX509IssuerSerial(X509IssuerSerialType value) {
        this.x509IssuerSerial = value;
    }

    /**
     * Recupera il valore della proprietà x509SKI.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getX509SKI() {
        return x509SKI;
    }

    /**
     * Imposta il valore della proprietà x509SKI.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setX509SKI(byte[] value) {
        this.x509SKI = value;
    }

    /**
     * Recupera il valore della proprietà x509SubjectName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getX509SubjectName() {
        return x509SubjectName;
    }

    /**
     * Imposta il valore della proprietà x509SubjectName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setX509SubjectName(String value) {
        this.x509SubjectName = value;
    }

    /**
     * Recupera il valore della proprietà x509Certificate.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getX509Certificate() {
        return x509Certificate;
    }

    /**
     * Imposta il valore della proprietà x509Certificate.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setX509Certificate(byte[] value) {
        this.x509Certificate = value;
    }

    /**
     * Recupera il valore della proprietà x509CRL.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getX509CRL() {
        return x509CRL;
    }

    /**
     * Imposta il valore della proprietà x509CRL.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setX509CRL(byte[] value) {
        this.x509CRL = value;
    }

}
