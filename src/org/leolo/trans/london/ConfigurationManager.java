package org.leolo.trans.london;

import java.lang.reflect.InvocationTargetException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ConfigurationManager {
	
	private static ConfigurationManager instance;
	Logger log = LogManager.getLogger(ConfigurationManager.class);
	private String apiKey;
	private String cachePath;
	private boolean cacheInMemory;
	private DatabaseManager databaseManager;
	private Class<? extends DatabaseManager> databaseManagerClass;
	
	static {
		instance = new ConfigurationManager();
		System.setProperty("user.timezone", "GMT");
	}
	
	public static ConfigurationManager getInstance() {
		return instance;
	}
	
	//To avoid more than 1 instance to be created
	private ConfigurationManager() {
		
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getCachePath() {
		return cachePath;
	}

	public void setCachePath(String cachePath) {
		this.cachePath = cachePath;
		cacheInMemory = ":memory:".equals(cachePath);
	}
	
	public boolean isCacheInMemory() {
		return cacheInMemory;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setDatabaseManagerClass(String className) throws ReflectiveOperationException {
		Class clazz = Class.forName(className);
		if(DatabaseManager.class.isAssignableFrom(clazz)) {
			databaseManagerClass = clazz;
		}else {
			throw new ClassCastException(className+" cannot be casted as org.leolo.trans.london.DatabaseManager");
		}
	}
	
	public final synchronized DatabaseManager getDatabaseManager() {
		if(databaseManager!=null) {
			return databaseManager;
		}
		if(databaseManagerClass!=null) {
			try {
				databaseManager = databaseManagerClass.getConstructor().newInstance();
				return databaseManager;
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
			return null;
		}
		return null;
	}
}
