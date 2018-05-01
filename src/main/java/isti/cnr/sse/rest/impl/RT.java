package isti.cnr.sse.rest.impl;

import java.math.BigDecimal;
import java.util.Date;

public class RT {
	private String Matricola;
	private BigDecimal GT;
	private Integer timediff;
	private Date starttime;
	private Date workingtime;
	private Date stoptime;
	private Integer Kricevuti;

	
	
	
	public RT(String Matricola,BigDecimal gT, Integer timediff, Date starttime, Date workingtime, Date stoptime, Integer kricevuti) {
	
		this.Matricola=Matricola;
		GT = gT;
		this.timediff = timediff;
		this.starttime = starttime;
		this.workingtime = workingtime;
		this.stoptime = stoptime;
		Kricevuti = kricevuti;
	}
	
	public RT(String Matricola, Date starttime) {
		this.Matricola=Matricola;
		GT = new BigDecimal(0);
		this.timediff = 0;
		this.starttime = starttime;
		this.workingtime = starttime;
		this.stoptime = starttime;
		Kricevuti = 0;
	}
	
	public RT(String Matricola, Date starttime, BigDecimal gt) {
		this.Matricola=Matricola;
		GT = gt;
		this.timediff = 0;
		this.starttime = starttime;
		this.workingtime = starttime;
		this.stoptime = starttime;
		Kricevuti = 0;
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
	public Integer getKricevuti() {
		return Kricevuti;
	}
	public void setKricevuti(Integer kricevuti) {
		Kricevuti = kricevuti;
	}

	@Override
	public String toString() {
		return "RT [Matricola=" + Matricola + ", GT=" + GT + ", timediff=" + timediff + ", starttime=" + starttime
				+ ", workingtime=" + workingtime + ", stoptime=" + stoptime + ", Kricevuti=" + Kricevuti + "]";
	}
	
	
	

}
