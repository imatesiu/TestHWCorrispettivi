package cnr.isti.sse.data.send;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {

	
	
	public static Properties loadp(){
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			/*System.out.println(prop.getProperty("NomeProva"));
			System.out.println(prop.getProperty("grantotale"));
			System.out.println(prop.getProperty("numeroRapportoProva"));
			System.out.println(prop.getProperty("nomeModello"));*/

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return prop;

	  }
		
		
	
}
