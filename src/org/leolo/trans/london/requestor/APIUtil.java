package org.leolo.trans.london.requestor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.leolo.trans.london.Constants;

public class APIUtil {
	static Logger log = LogManager.getLogger(APIUtil.class);
	
	public static String sendRequest(String url, Map<String, String> property) throws IOException{
		return sendRequest(new URL(url), property);
	}
	public static String sendRequest(String url) throws IOException{
		return sendRequest(new URL(url), null);
	}
	
	public static String sendRequest(URL url) throws IOException{
		return sendRequest(url, null);
	}
	
	public static String sendRequest(URL url, Map<String, String> property) throws IOException {
		HttpURLConnection  conn = (HttpURLConnection)url.openConnection();
		if(property!=null) {
			for(String propKey:property.keySet()) {
				conn.setRequestProperty(propKey, property.get(propKey));
			}
		}
		conn.setConnectTimeout(Constants.REQUEST_TIMEOUT);
		conn.setReadTimeout(Constants.REQUEST_TIMEOUT);
		conn.setRequestMethod("GET");
		conn.setInstanceFollowRedirects(true);
		conn.connect();
		StringBuilder sb = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()))){
			while(true) {
				String line = br.readLine();
				if(line==null) break;
				sb.append(line);
			}
		}
		conn.disconnect();
		log.info("Response size : {}", sb.length());
		return sb.toString();
	}
	
}
