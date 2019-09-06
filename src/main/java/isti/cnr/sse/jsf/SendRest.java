package isti.cnr.sse.jsf;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import isti.cnr.sse.rest.impl.RT;



public class SendRest {

	public static Client ignoreSSLClient()  {
		try{
			SSLContext sslcontext = SSLContext.getInstance("TLS");

			sslcontext.init(null,  new TrustManager[]{new X509TrustManager() {
				public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException{}
				public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException{}
				public X509Certificate[] getAcceptedIssuers()
				{
					return new X509Certificate[0];
				}

			}}, new java.security.SecureRandom());


			/*	    return ClientBuilder.newBuilder()
	                        .sslContext(sslcontext)
	                        .hostnameVerifier((s1, s2) -> true)
	                        .build();*/

			HostnameVerifier allowAll = new HostnameVerifier() 
			{
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			return ClientBuilder.newBuilder().sslContext(sslcontext).hostnameVerifier(allowAll).build();
		}catch (Exception e) {
			// TODO: handle exception

		}
		return ClientBuilder.newClient();
	}


	public Response sendGet(String path, String... args){
		Client client = ignoreSSLClient();
		WebTarget target = null;
		if(args!=null){
			 target = client.target("https://127.0.0.1").path("/v1/dispositivi/corrispettivi/"+path).queryParam(args[0], args[1]).queryParam(args[2], args[3]).queryParam(args[4], args[5]);
		}else{
			 target = client.target("https://127.0.0.1").path("/v1/dispositivi/corrispettivi/"+path);
		}
			
		Response allID =  target.request(MediaType.APPLICATION_XML).get();

		return allID;
	}


	public String post(String content, String path){

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost").path("/v1/corrispettivi/"+path);

		Entity<String> entity = Entity.entity(content,MediaType.APPLICATION_JSON);

		Response response =  target.request().post(entity);

		String reply = response.readEntity(String.class);

		return reply;
		//FacesContext context = FacesContext.getCurrentInstance();
		//context.getExternalContext().getRequestMap().put("rest", id);

	}
	
	public Response SendPost(String URL, String path, InputStream f) {
		
		Client client =  ignoreSSLClient();
		WebTarget target = client.target(URL).path(path);

		Entity<InputStream> entity = Entity.entity(f,MediaType.APPLICATION_XML);

		Response response =  target.request().post(entity);
		
		
		return response;
	}

  public Response SendPut(String URL, String path, InputStream f) {

                Client client =  ignoreSSLClient();
                WebTarget target = client.target(URL).path(path);

                Entity<InputStream> entity = Entity.entity(f,MediaType.APPLICATION_XML);

                Response response =  target.request().put(entity);


                return response;
        }


}
