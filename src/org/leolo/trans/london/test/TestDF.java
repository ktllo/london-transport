package org.leolo.trans.london.test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.leolo.trans.london.Constants;

public class TestDF {
	
	public static void main(String [] args) throws Throwable{
		System.out.println("2021-08-10T16:33:12.11Z");
		System.out.println("yyyy-MM-dd'T'HH:mm:ss.SS");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS");
		try {
			System.out.println(sdf.parse("2021-08-10T16:33:12.11Z"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		URL url = new URL("https://www.leolo.org/?a=bcd&b=efg");
		HttpURLConnection  conn = (HttpURLConnection)url.openConnection();
		for(String key:conn.getRequestProperties().keySet()) {
			for(String val:conn.getRequestProperties().get(key)) {
				System.out.println(key + " : "+val);
			}
		}
	}
	
	
}
