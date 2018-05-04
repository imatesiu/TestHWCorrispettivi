package isti.cnr.sse.rest.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import isti.cnr.sse.jsf.TipoProve;

@XmlRootElement(name = "RT")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RT", propOrder = {
    "Matricola",
    "GT",
    "TotaleRicevuto",
    "timediff",
    "starttime",
    "workingtime",
    "stoptime",
    "Zricevuti",
    "Z",
    "Descrizione",
    "isCloded"
})
public class RT implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3320736152498502084L;
	
	@XmlElement(name = "Matricola", required = true)
	private String Matricola;
	@XmlElement(name = "GT", required = true)
	private BigDecimal GT;
	@XmlElement(name = "TotaleRicevuto", required = true)
	private BigDecimal TotaleRicevuto;
	@XmlElement(name = "timediff", required = true)
	private Integer timediff;
	@XmlElement(name = "starttime", required = true)
	private Date starttime;
	@XmlElement(name = "workingtime", required = true)
	private Date workingtime;
	@XmlElement(name = "stoptime", required = true)
	private Date stoptime;
	@XmlElement(name = "Zricevuti", required = true)
	private Integer Zricevuti;
	@XmlElement(name = "Z", required = true)
	private Integer Z;
	@XmlElement(name = "Descrizione", required = true)
	private String Descrizione;
	@XmlAttribute(name = "isCloded", required = true)
	private boolean isCloded;
	
	
	
	public RT() {
		this.Matricola="";
		GT = new BigDecimal(0);
		this.TotaleRicevuto = new BigDecimal(0);
		this.timediff = 0;
		this.starttime = new Date();
		this.workingtime = starttime;
		this.stoptime = starttime;
		Zricevuti = 0;
		this.Descrizione = "";
		this.isCloded = false;
	}

	public RT(String Matricola,BigDecimal gT, Integer timediff, Date starttime, Date workingtime, Date stoptime, Integer kricevuti) {
	
		this.Matricola=Matricola;
		GT = gT;
		this.TotaleRicevuto = new BigDecimal(0);
		this.timediff = timediff;
		this.starttime = starttime;
		this.workingtime = workingtime;
		this.stoptime = stoptime;
		this.Zricevuti = kricevuti;
		this.Descrizione = "";
		this.isCloded = false;
	}
	
	public RT(String Matricola, Date starttime) {
		this.Matricola=Matricola;
		GT = new BigDecimal(0);
		this.TotaleRicevuto = new BigDecimal(0);
		this.timediff = 0;
		this.starttime = starttime;
		this.workingtime = starttime;
		this.stoptime = starttime;
		Zricevuti = 0;
		this.Descrizione = "";
		this.isCloded = false;
	}
	
	public RT(String Matricola, Date starttime, BigDecimal gt) {
		this.Matricola=Matricola;
		GT = gt;
		this.TotaleRicevuto = new BigDecimal(0);
		this.timediff = 0;
		this.starttime = starttime;
		this.workingtime = starttime;
		this.stoptime = starttime;
		Zricevuti = 0;
		this.Descrizione = "";
		this.isCloded = false;
	}
	
	
	
	public String getDescrizione() {
		return Descrizione;
	}

	public void setDescrizione(String descrizione) {
		Descrizione = descrizione;
		if(descrizione==null){
			Descrizione= TipoProve.Termiche.toString();
		}
	}

	public boolean isCloded() {
		return isCloded;
	}

	public void setCloded(boolean isCloded) {
		this.isCloded = isCloded;
	}
	
	public void setCloded() {
		this.isCloded = true;
	}

	public String getMatricola() {
		return Matricola;
	}

	public void setMatricola(String matricola) {
		Matricola = matricola;
	}

	public BigDecimal getTotaleRicevuto() {
		return TotaleRicevuto;
	}

	public void setTotaleRicevuto(BigDecimal totaleRicevuto) {
		TotaleRicevuto = totaleRicevuto;
	}

	public BigDecimal getGT() {
		BigDecimal gb = new BigDecimal(0); 
		return gb.add(GT).add(TotaleRicevuto);
	}
	public void setGT(BigDecimal gT) {
		GT = gT;
	}
	public Integer getTimediff() {
		return timediff;
	}
	public void setTimediff(Integer timediff) {
		this.timediff = timediff;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getWorkingtime() {
		return workingtime;
	}
	public void setWorkingtime(Date workingtime) {
		this.workingtime = workingtime;
		setStoptime(workingtime);
	}
	public Date getStoptime() {
		return stoptime;
	}
	public void setStoptime(Date stoptime) {
		this.stoptime = stoptime;
	}
	public Integer getZricevuti() {
		return Zricevuti;
	}
	public void setZricevuti(Integer kricevuti) {
		Zricevuti = kricevuti;
	}

	@Override
	public String toString() {
		return " " + Matricola + "; " + GT + "; " + TotaleRicevuto + "; " + timediff + "; " + starttime + "; "
				+ workingtime + "; " + stoptime + "; " + Zricevuti + "; " + Z + "; " + Descrizione + "; " + isCloded;
	}

	public String toHtml(){
		String tag = "<html><body> Info per";
		String body = "Matricola: " + Matricola + "; <br/> GT: " + GT + "; <br/> TotaleRicevuto: " + TotaleRicevuto
				+ "; <br/> timediff: " + timediff + "; <br/> starttime: " + starttime + "; <br/> workingtime: "
				+ workingtime + "; <br/> stoptime: " + stoptime + "; <br/> Zricevuti: " + Zricevuti + "; <br/> Z: " + Z
				+ "; <br/> Descrizione: " + Descrizione + "; <br/> isCloded: " + isCloded;
		String etag = "</body></html>";
		return tag+body+etag;
	}
	
	public String getInfo(){
		String body = "Matricola: " + Matricola + "; GT: " + GT + "; TotaleRicevuto: " + TotaleRicevuto + "; timediff: "
				+ timediff + "; starttime: " + starttime + "; workingtime: " + workingtime + "; stoptime: " + stoptime
				+ "; Zricevuti: " + Zricevuti + "; Z: " + Z + "; Descrizione: " + Descrizione + "; isCloded: "
				+ isCloded;
		
		return body;
	}
	public String getKey(){
		return Matricola+Descrizione;
	}
	public Integer getZ() {
		return Z+Zricevuti;
	}

	public void setZ(Integer z) {
		Z = z;
	}
	
	
	

}
