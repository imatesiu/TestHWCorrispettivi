package isti.cnr.sse.rest.impl.firma;


import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.xml.crypto.dsig.CanonicalizationMethod;
import javax.xml.crypto.dsig.DigestMethod;
import javax.xml.crypto.dsig.Reference;
import javax.xml.crypto.dsig.SignedInfo;
import javax.xml.crypto.dsig.Transform;
import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.X509Data;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;


public class SignReply {
	
	public static final String SHA256_RSA_SIGNATURE_ALGORITHM = "http://www.w3.org/2001/04/xmldsig-more#rsa-sha256";

    public static String Sign(Document dosigndocument, String al) {

        try {
            InputStream jskfile = SignReply.class.getClassLoader().getResourceAsStream("jetty-server-ssl.jks");
            String p12pass = "jetty8";
            String infile = "path_file_da_firmare.xml";
            String signedfile = "path_file_firmato.xml";
               
            Provider provider = null;
            /*
            provider = xml.XMLUtils.getXmlDsigProvider();
            System.out.println("XML provider: " + provider);
            */

            X509Certificate signerCert = null;
            PrivateKey key = null;
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(jskfile, p12pass.toCharArray());
            Enumeration<String> en = ks.aliases();
            while (en.hasMoreElements()) {
                String alias = (String) en.nextElement();
                if (ks.isKeyEntry(alias) && alias.equals(al)) {
                    signerCert = (X509Certificate) ks.getCertificate(alias);
                    key = (PrivateKey) ks.getKey(alias, p12pass.toCharArray());
                    break;
                }
            }

            if (signerCert == null || key == null) {
                throw new Exception("Key or Certificate not found!!");
            }

            // Create a DOM XMLSignatureFactory that will be used to generate the
            // enveloped signature
            XMLSignatureFactory fac;
            if (provider == null) {
                fac = XMLSignatureFactory.getInstance("DOM");
            } else {
                fac = XMLSignatureFactory.getInstance("DOM", provider);
            }

            // Create a Reference to the enveloped document (in this case we are
            // signing the whole document, so a URI of "" signifies that) and
            // also specify the SHA1 digest algorithm and the ENVELOPED Transform.
            Reference ref = fac.newReference(
            		"",
                    fac.newDigestMethod(DigestMethod.SHA256, null),
                    Collections.singletonList(fac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)),
                    null, 
                    null);

            // Create the SignedInfo
            SignedInfo si = fac.newSignedInfo(
                    fac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null),
                    fac.newSignatureMethod(SHA256_RSA_SIGNATURE_ALGORITHM, null),
                    Collections.singletonList(ref));

            KeyInfoFactory kif;
            if (provider == null) {
                kif = KeyInfoFactory.getInstance("DOM");
            } else {
                kif = KeyInfoFactory.getInstance("DOM", provider);
            }

            // Aggiunta del certificato del firmatario in KeyInfo
            List<X509Certificate> x509DataContent = new ArrayList<X509Certificate>();
            x509DataContent.add(signerCert);
            X509Data x509Data = kif.newX509Data(x509DataContent);
            List<X509Data> keyInfoContent = new ArrayList<X509Data>();
            keyInfoContent.add(x509Data);

            KeyInfo ki = kif.newKeyInfo(keyInfoContent);

            // Instantiate the document to be signed
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            dbf.setValidating(true);

            // default false
            dbf.setIgnoringElementContentWhitespace(true);
            // default true
            dbf.setExpandEntityReferences(false);
            //Document doc = null;
            //doc = dbf.newDocumentBuilder().parse(new FileInputStream(infile));

            // Create a DOMSignContext and specify the RSA PrivateKey and
            // location of the resulting XMLSignature's parent element
            DOMSignContext dsc = new DOMSignContext(key, dosigndocument.getDocumentElement());

            dsc.setProperty("javax.xml.crypto.dsig.cacheReference", Boolean.TRUE);
            dsc.putNamespacePrefix(XMLSignature.XMLNS, "ds");

            // Create the XMLSignature (but don't sign it yet)
            XMLSignature signature = fac.newXMLSignature(si, ki);

            // Marshal, generate (and sign) the enveloped signature
            signature.sign(dsc);

            // output the resulting document
            OutputStream os;
            os = new FileOutputStream(signedfile);

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer trans = tf.newTransformer(); 
            
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            trans.transform(new DOMSource(dosigndocument), result);
            
            
            trans.transform(new DOMSource(dosigndocument), new StreamResult(os));
            
           
            

            System.out.println("File " + signedfile + " firmato.");
            
            
            return writer.toString();

        } 
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null;
    }
	
    
   

}
