package cnr.isti.sse.data.send;

import java.math.BigDecimal;
import java.util.Properties;

import javax.annotation.Generated;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class dataProve {
	
	
	public dataProve(String key, BigDecimal grantotale, int num, int diff) {
		this.ipAddress= key;
		this.grantotale = grantotale.intValueExact();
		this.numinvii = num;
		Properties p = LoadProperties.loadp();
		this.nomeModello = p.getProperty("nomeModello");
		this.nomeprova = p.getProperty("NomeProva");
		this.numeroRapportoProva = p.getProperty("numeroRapportoProva");
		this.difftime = diff;
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
	private Integer grantotale = new Integer(0);
	
	
	@SerializedName("difftime")
    @Expose
	private Integer difftime = new Integer(0);
	
	
}
