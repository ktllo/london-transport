package org.leolo.trans.london;

public class Constants {
	//Database schema related
	public static final int DB_SCHEMA_VERSION = 1;
	
	//Cache age related
	public static final long MAX_LINE_AGE = 21_600_000;//6 hours
	
	//Key to cache age
	public static final String CACHE_KEY_LINE = "000-line";
	
	//API related
	public static final String API_BASE_URL = "https://api.tfl.gov.uk/";
	public static final String SERVICE_TYPE_REGULAR = "Regular";
	public static final String SERVICE_TYPE_NIGHT = "Night";
	public static final String SERVICE_TYPE_ALL = "Regular,Night";
	public static final String DATE_FORMAT_STR = "yyyy-MM-dd'T'HH:mm:ss.SS";
	
	//API connection related
	public static final int REQUEST_TIMEOUT = 5000;
	public static final int MAX_THREAD_POOL_SIZE = 10;
}
