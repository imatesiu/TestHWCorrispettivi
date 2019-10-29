package isti.cnr.sse.jsf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;



public class Config {
	
	private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Config.class);
	
	private  String url;
	private  String port;
	private  String secureport;

	Config(){
		try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")){

			//try (InputStream input = new FileInputStream("path/to/config.properties")) {

	            Properties prop = new Properties();

	            // load a properties file
	            prop.load(input);

	            // get the property value and print it out
	            
	            log.trace(prop.getProperty("server.url"));
	            log.trace(prop.getProperty("server.port"));
	            log.trace(prop.getProperty("server.secure.port"));

	            url = prop.getProperty("server.url");
	            port =  prop.getProperty("server.port");
	            secureport = prop.getProperty("server.secure.port");
	           

	        } catch (IOException ex) {
	        	log.error(ex,ex);
	        }
	}

	
	public String getServerUrl() {
		try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")){

		//try (InputStream input = new FileInputStream("path/to/config.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            
            log.trace(prop.getProperty("server.url"));
            return prop.getProperty("server.url");
           

        } catch (IOException ex) {
        	log.error(ex,ex);
        }
		return "localhost";
	}
	
	public String getServerPort() {
		try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")){

		//try (InputStream input = new FileInputStream("path/to/config.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            
            log.trace(prop.getProperty("server.port"));
            return prop.getProperty("server.port");
           

        } catch (IOException ex) {
        	log.error(ex,ex);
        }
		return "9090";
	}
	
	public String getServerPortSecure() {
		try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")){

		//try (InputStream input = new FileInputStream("path/to/config.properties")) {

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            // get the property value and print it out
            
            log.trace(prop.getProperty("server.secure.port"));
            return prop.getProperty("server.secure.port");
           

        } catch (IOException ex) {
        	log.error(ex,ex);
        }
		return "443";
	}


	public String getUrl() {
		return url;
	}


	public  String getPort() {
		return port;
	}


	public  String getSecureport() {
		return secureport;
	}

	
	
	
	
	
}
