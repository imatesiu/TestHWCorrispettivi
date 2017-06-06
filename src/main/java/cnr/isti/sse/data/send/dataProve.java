package cnr.isti.sse.data.send;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Properties;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class dataProve {
	
	public dataProve(){
		
	}
	public dataProve(String key, BigDecimal grantotale, int num, int diff) {
		this.ipAddress= key;
		this.grantotale = grantotale;
		this.numinvii = num;
		
	}


	@SerializedName("nomeModello")
    @Expose
	private String nomeModello = new String();
	
	@SerializedName("numeroRapportoProva")
    @Expose
	private String numeroRapportoProva = new String();
	
	
	@SerializedName("ipAddress")
    @Expose
	private String ipAddress = new String();
	
	@SerializedName("NomeProva")
    @Expose
	private String nomeprova = new String();
	

	@SerializedName("numinvii")
    @Expose
	private Integer numinvii = new Integer(0);
	
	@SerializedName("grantotale")
    @Expose
	private BigDecimal grantotale = new BigDecimal(0);
	
	
	@SerializedName("difftime")
    @Expose
	private Integer difftime = new Integer(0);
	
	@SerializedName("oldtime")
    @Expose
	private Date oldtime = new Date();


	public String getNomeModello() {
		return nomeModello;
	}


	public void setNomeModello(String nomeModello) {
		this.nomeModello = nomeModello;
	}


	public String getNumeroRapportoProva() {
		return numeroRapportoProva;
	}


	public void setNumeroRapportoProva(String numeroRapportoProva) {
		this.numeroRapportoProva = numeroRapportoProva;
	}


	public String getIpAddress() {
		return ipAddress;
	}


	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}


	public String getNomeprova() {
		return nomeprova;
	}


	public void setNomeprova(String nomeprova) {
		this.nomeprova = nomeprova;
	}


	public Integer getNuminvii() {
		return numinvii;
	}


	public void setNuminvii(Integer numinvii) {
		this.numinvii = numinvii;
	}


	public BigDecimal getGrantotale() {
		return grantotale;
	}


	public void setGrantotale(BigDecimal grantotale) {
		this.grantotale = grantotale;
	}


	public Integer getDifftime() {
		return difftime;
	}


	public void setDifftime(Integer difftime) {
		this.difftime = difftime;
	}


	public Date getOldtime() {
		return oldtime;
	}


	public void setOldtime(Date oldtime) {
		this.oldtime = oldtime;
	}
	public void init() {
		oldtime = new Date();
		difftime = 0 ;
		grantotale = new BigDecimal(0);
		numinvii = 0;
		
	}
	public int updateNuminvii() {
		numinvii++;
		return numinvii;
	}
	
	
	
	
	
}
