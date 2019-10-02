package isti.cnr.sse.jsf;



import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.ws.rs.core.Response;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped 
public class FileUploadView {
     
    private UploadedFile file;
    
    private String resp;
    
    private String url;
    private String path;
    private boolean value1;  

 
    
    public boolean isValue1() {
		return value1;
	}

	public void setValue1(boolean value1) {
		this.value1 = value1;
	}

	public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}

	public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() {
        if(file != null) {
            FacesMessage message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            SendRest r = new SendRest();
    		try {
				r.SendPost(url, path, file.getInputstream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
     
    public void handleFileUpload(FileUploadEvent event) throws IOException {
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        setFile(event.getFile());
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

	public void send(ActionEvent actionEvent) throws IOException {
		addMessage("send"+url);
		SendRest r = new SendRest();
		Response response = null;
		if(path.contains("corrispettivi")){
			response  = r.SendPost(url, path,  file.getInputstream());
		}else{
			if(value1)
				response  = r.SendPut(url, path,  file.getInputstream());
			else
				response  = r.SendPost(url, path,  file.getInputstream());
		}
		response.getHeaders();
		String re = response.readEntity(String.class);
		if(re.isEmpty()) {
			re = "Status Response: "+ new Integer(response.getStatus()).toString();
		}
		setResp(re);

	}
	
	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
