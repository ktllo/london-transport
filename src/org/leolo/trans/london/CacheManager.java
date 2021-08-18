package org.leolo.trans.london;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.leolo.trans.london.requestor.LineAPI;
import org.sqlite.SQLiteDataSource;

public final class CacheManager {

	Logger log = LogManager.getLogger(CacheManager.class);
	
	
	static {
		instance = new CacheManager();
	}
	
	private static CacheManager instance;
	private DatabaseManager db;
	
	private CacheManager() {
		db = ConfigurationManager.getInstance().getDatabaseManager();
		init();
	}
	
	public static CacheManager getInstance() {
		return instance;
	}
	
	private void init() {
	}
	
	public void updateLineInfo() {
		log.info("Updating line info");
		LineAPI.getLines(Constants.SERVICE_TYPE_ALL);
		
		db.setCacheTime(Constants.CACHE_KEY_LINE);
	}
	//Pre-fetches data from TfL
	public void initialize() {
		ExecutorService taskExecutor = Executors.newFixedThreadPool(Constants.MAX_THREAD_POOL_SIZE);
		if(db.getAge(Constants.CACHE_KEY_LINE) >= Constants.MAX_LINE_AGE) {
			taskExecutor.submit(new Runnable() {
				@Override
				public void run() {
					updateLineInfo();
				}
			});
		}
		//Pre-fetch other info
		taskExecutor.shutdown();
		try {
			taskExecutor.awaitTermination(Long.MAX_VALUE, TimeUnit.MICROSECONDS);
		} catch (InterruptedException e) {
			log.error(e.getMessage(), e);
		}
	}
	
}
