package isti.cnr.sse.jsf;



import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Collection;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.naming.InvalidNameException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.CMSProcessable;
import org.bouncycastle.cms.CMSProcessableByteArray;
import org.bouncycastle.cms.CMSSignedData;
import org.bouncycastle.cms.SignerInformation;
import org.bouncycastle.cms.jcajce.JcaSimpleSignerInfoVerifierBuilder;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.StoreException;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped 
public class FileUploadFWView {
     
    private UploadedFile fileFW;
    private UploadedFile fileCertificate;
    private UploadedFile fileFirma;
    private String firma;
    
    private String resp;
    
    private String url;
    private String path;

 
    
    public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}

	public UploadedFile getFile() {
        return fileFW;
    }
 
    public void setFile(UploadedFile file) {
        this.fileFW = file;
    }
    
    
     
 /*   public void upload() {
        if(fileFW != null) {
            FacesMessage message = new FacesMessage("Succesful", fileFW.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            SendRest r = new SendRest();
    		try {
				r.SendPost(url, path, fileFW.getInputstream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }*/
     
    public UploadedFile getFileFirma() {
		return fileFirma;
	}

	public void setFileFirma(UploadedFile fileFirma) {
		this.fileFirma = fileFirma;
	}

	public void handleFileUpload(FileUploadEvent event) throws IOException {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        setFile(event.getFile());
       // SendRest r = new SendRest();
		//r.SendPost(url, path,  event.getFile().getInputstream());
    }
    
    public void handleCertificateFileUpload(FileUploadEvent event) throws IOException {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        setFileCertificate(event.getFile());
       // SendRest r = new SendRest();
		//r.SendPost(url, path,  event.getFile().getInputstream());
    }
    
    public void handleFirmaFileUpload(FileUploadEvent event) throws IOException {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        setFileFirma(event.getFile());
       // SendRest r = new SendRest();
		//r.SendPost(url, path,  event.getFile().getInputstream());
    }

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
    
	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	

	public UploadedFile getFileFW() {
		return fileFW;
	}

	public void setFileFW(UploadedFile fileFW) {
		this.fileFW = fileFW;
	}

	public UploadedFile getFileCertificate() {
		return fileCertificate;
	}

	public void setFileCertificate(UploadedFile fileCertificate) {
		this.fileCertificate = fileCertificate;
	}

	public String getFirma() {
		return firma;
	}

	public void setFirma(String firma) {
		this.firma = firma;
	}

	public void send(ActionEvent actionEvent) throws IOException {
		addMessage("send"+url);
		 try {
			 
		CertificateFactory cf=CertificateFactory.getInstance("X509");
			X509Certificate cert=(X509Certificate)cf.generateCertificate(fileCertificate.getInputstream());
			
			
			
			PublicKey publicKey =cert.getPublicKey();
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(publicKey);

            byte[] bytes = IOUtils.toByteArray(fileFW.getInputstream());
            signature.update(bytes);
           // byte[ ] decode = Base64.decode(getFirma());
            byte[ ] decode = new byte[] { 0, 1, 2 };
            if(firma!=null)
            	decode = Base64.decodeBase64(getFirma().getBytes());//Base64.decode(getFirma());
            if(fileFirma!=null)		
            	decode =	IOUtils.toByteArray(fileFirma.getInputstream());
            
            
            boolean verified  = false;
            if(fileFirma.getFileName().contains("p7s")){
            	verified  = callVerP7s();
            }else {
            	if(decode!=null) {
                	verified = signature.verify(decode);
                }
            }
            
            
            
            
            
            if (verified) {
                System.out.println("Data verified.");
                addMessage("Data verified.");
                setResp("Data verified OK");
            } else {
                System.out.println("Cannot verify data.");
                addMessage("Cannot verify data.");
                setResp("Cannot verify data. KO");
            }
			
			
		} catch (CertificateException | SignatureException | InvalidKeyException | NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 setResp("Cannot verify data. KO "+e.getMessage());
		}
		
    }
	
	private boolean callVerP7s() {
		try {
			 byte[] bytes = IOUtils.toByteArray(fileFW.getInputstream());

	        byte[] bytesc = IOUtils.toByteArray(fileFirma.getInputstream());
			  
			return  verifyPKCS7(bytes, bytesc);
			  
        
		}catch (Exception e) {
			  System.out.println("Signature verification failed " + e.getMessage());
			  setResp("Signature verification failed " + e.getMessage());
		}
		return false;
	}
	/**
     * Verify a PKCS7 signature.
     *
     * @param byteArray the byte sequence that has been signed
	 * @param psi 
     * @param contents the /Contents field as a COSString
     * @param sig the PDF signature (the /V dictionary)
     * @throws CertificateException
     * @throws CMSException
     * @throws StoreException
     * @throws OperatorCreationException
     */
    private boolean verifyPKCS7(byte[] byteArray,byte[] contents)
            throws CMSException, CertificateException, StoreException, OperatorCreationException,
            NoSuchAlgorithmException, NoSuchProviderException, InvalidNameException {
        // inspiration:
        // http://stackoverflow.com/a/26702631/535646
        // http://stackoverflow.com/a/9261365/535646
        CMSProcessable signedContent = new CMSProcessableByteArray(byteArray);
        CMSSignedData signedData = new CMSSignedData(signedContent, contents);
        Store certificatesStore = signedData.getCertificates();
        Collection<SignerInformation> signers = signedData.getSignerInfos().getSigners();
        SignerInformation signerInformation = signers.iterator().next();
        Collection matches = certificatesStore.getMatches(signerInformation.getSID());
        X509CertificateHolder certificateHolder = (X509CertificateHolder) matches.iterator().next();
        X509Certificate certFromSignedData = new JcaX509CertificateConverter().getCertificate(certificateHolder);

        System.out.println("certFromSignedData: " + certFromSignedData);

        /*CertificateInfo ci = new CertificateInfo();
        psi.certificateInfo = ci;
        ci.issuerDN = certFromSignedData.getIssuerDN().toString();
        ci.subjectDN = certFromSignedData.getSubjectDN().toString();

        ci.notValidAfter = certFromSignedData.getNotAfter();
        ci.notValidBefore = certFromSignedData.getNotBefore();

        ci.signAlgorithm = certFromSignedData.getSigAlgName();
        ci.serial = certFromSignedData.getSerialNumber().toString();
        
        String issuerDN = certFromSignedData.getIssuerDN().toString();

        LdapName ldapDN = new LdapName(issuerDN);
        for(Rdn rdn: ldapDN.getRdns()) {
            ci.issuerOIDs.put(rdn.getType(), rdn.getValue().toString());
        }

        ldapDN = new LdapName(ci.subjectDN);
        for(Rdn rdn: ldapDN.getRdns()) {
            ci.subjectOIDs.put(rdn.getType(), rdn.getValue().toString());
        }*/

        //certFromSignedData.checkValidity(sig.getSignDate().getTime());

        if (isSelfSigned(certFromSignedData)) {
            System.err.println("Certificate is self-signed, LOL!");
           // psi.isSelfSigned = true;
        } else {
            System.out.println("Certificate is not self-signed");
            //psi.isSelfSigned = false;
            // todo rest of chain
        }

        if (signerInformation.verify(new JcaSimpleSignerInfoVerifierBuilder().build(certFromSignedData))) {
            System.out.println("Signature verified");
            setResp("Signature verified");
            return true;
            //psi.signatureVerified="YES";
        } else {
            System.out.println("Signature verification failed");
            setResp("Signature verified");
           // psi.signatureVerified="NO";
        }
        
        return false;
    }

    // https://svn.apache.org/repos/asf/cxf/tags/cxf-2.4.1/distribution/src/main/release/samples/sts_issue_operation/src/main/java/demo/sts/provider/cert/CertificateVerifier.java

    /**
     * Checks whether given X.509 certificate is self-signed.
     */
    private static boolean isSelfSigned(X509Certificate cert) throws CertificateException, NoSuchAlgorithmException, NoSuchProviderException {
        try {
            // Try to verify certificate signature with its own public key
            PublicKey key = cert.getPublicKey();
            cert.verify(key);
            return true;
        } catch (SignatureException | InvalidKeyException sigEx) {
            return false;
        }
    }
	
    

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	
}