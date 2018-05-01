package isti.cnr.sse.jsf;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;





@ManagedBean
@SessionScoped
public class NewProva {

	private String Matricola;
	private String grantotale;
	private String zchiusure;
	private String Descrizione;
	
	private TipoProve prove ;
	
	
	public String getMatricola() {
		return Matricola;
	}
	public void setMatricola(String matricola) {
		Matricola = matricola;
	}
	public String getGrantotale() {
		return grantotale;
	}
	public void setGrantotale(String grantotale) {
		this.grantotale = grantotale;
	}

	public String getZchiusure() {
		return zchiusure;
	}
	public void setZchiusure(String zchiusure) {
		this.zchiusure = zchiusure;
	}
	public TipoProve getProve() {
		return prove;
	}
	public void setProve(TipoProve prove) {
		this.prove = prove;
	}
	public String getDescrizione() {
		if(Descrizione==null){
			Descrizione  = "pia";
		}
		return Descrizione;
	}
	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
	}
	
	public void save(ActionEvent actionEvent) {
		String[] args = new String[10];
		args[0] = "grantot";
		args[1] = getGrantotale();
		args[2] = "desc";
		args[3] = getDescrizione();
		args[4] = "z";
		args[5] = getZchiusure();
		SendRest s = new SendRest();
		String resutl = s.sendGet("init/"+getMatricola(), args);
		addMessage(resutl);
	}
	
	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
}
