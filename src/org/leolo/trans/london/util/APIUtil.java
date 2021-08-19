package org.leolo.trans.london.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.leolo.trans.london.ConfigurationManager;
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
//		conn.
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
	
	public static URL getURL(String reqPath, Map<String, String> queryString) {
		if(queryString==null) {
			queryString = new HashMap<>();
		}
		if(!queryString.containsKey("api_key") && ConfigurationManager.getInstance().getApiKey()!=null) {
			queryString.put("api_key", ConfigurationManager.getInstance().getApiKey());
		}
		StringBuilder sb = new StringBuilder(Constants.API_BASE_URL);
		if(!Constants.API_BASE_URL.endsWith("/")) {
			sb.append("/");
		}
		sb.append(reqPath);
		if(queryString.size()!=0) {
			sb.append("?");
			Iterator<String> iQueryKey = queryString.keySet().iterator();
			while(true) {
				if(!iQueryKey.hasNext())
					break;
				String key = iQueryKey.next();
				try {
					sb.append(key).append("=").append(URLEncoder.encode(queryString.get(key),"UTF-8"));
				} catch (UnsupportedEncodingException e) {
					log.error(e.getMessage(), e);
				}
				if(iQueryKey.hasNext())
					sb.append("&");
			}
		}
		log.debug("Built URL : {}", sb);
		try {
			return new URL(sb.toString());
		} catch (MalformedURLException e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}
}
