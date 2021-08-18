package org.leolo.trans.london.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.leolo.trans.london.Constants;

public class TestDF {
	
	public static void main(String [] args) {
		System.out.println("2021-08-10T16:33:12.11Z");
		System.out.println("yyyy-MM-dd'T'HH:mm:ss.SS");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS");
		try {
			System.out.println(sdf.parse("2021-08-10T16:33:12.11Z"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
