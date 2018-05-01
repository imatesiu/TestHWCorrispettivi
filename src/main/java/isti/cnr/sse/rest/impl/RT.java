package isti.cnr.sse.rest.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "RT")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RT", propOrder = {
    "Matricola",
    "GT",
    "timediff",
    "starttime",
    "workingtime",
    "stoptime",
    "Zricevuti",
    "Z",
    "Descrizione",
    "isCloded"
})
public class RT {
	@XmlElement(name = "Matricola", required = true)
	private String Matricola;
	@XmlElement(name = "GT", required = true)
	private BigDecimal GT;
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
			Descrizione= "";
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

	public BigDecimal getGT() {
		return GT;
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
		return "RT [Matricola=" + Matricola + ", GT=" + GT + ", timediff=" + timediff + ", starttime=" + starttime
				+ ", workingtime=" + workingtime + ", stoptime=" + stoptime + ", Kricevuti=" + Zricevuti + "]";
	}

	
	public Integer getZ() {
		return Z+Zricevuti;
	}

	public void setZ(Integer z) {
		Z = z;
	}
	
	
	

}
