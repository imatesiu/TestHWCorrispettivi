package isti.cnr.sse.jsf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;

import isti.cnr.sse.rest.impl.RT;

@ManagedBean
@ViewScoped
public class dataScrollerall implements Serializable {

	private List<RT> rts;
	private RT rt;

	@PostConstruct
	public void init() {
		SendRest s = new SendRest();
		Response resp = s.sendGet("allrtopen/", null);
		Collection<RT> res = resp.readEntity(new GenericType<Collection<RT>>() {
		});
		rts = new ArrayList<>(res);
	}


	public List<RT> getRts() {
		return rts;
	}

	public void setRts(List<RT> rts) {
		this.rts = rts;
	}


	public RT getRt() {
		return rt;
	}


	public void setRt(RT rt) {
		this.rt = rt;
	}

	public RT getSelected() {
		return rt;
	}



	public void setSelected(RT selectedProve) {
		this.rt = selectedProve;
		sendStop(selectedProve.getMatricola());
	}
	
	public void remprova() {
		rts.remove(rt);

	}


	private void sendStop(String matricola) {
		SendRest s = new SendRest();
		Response resp = s.sendGet("stop/"+matricola, null);
		StatusType sf = resp.getStatusInfo();
		if(sf.getStatusCode()==200){
			addMessage("OK");
		}else{
			addMessage("KO"+sf.getStatusCode());
		}
		
	}

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}

