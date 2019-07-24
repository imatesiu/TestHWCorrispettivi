package isti.cnr.sse.jsf;



import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.primefaces.util.Base64;

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
            	decode = Base64.decode(getFirma());
            if(fileFirma!=null)		
            	decode =	IOUtils.toByteArray(fileFirma.getInputstream());
            boolean verified  = false;
            if(decode!=null) {
            	verified = signature.verify(decode);
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
	
	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
	
	
}