package org.leolo.trans.london.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.leolo.trans.london.CacheManager;
import org.leolo.trans.london.ConfigurationManager;

public class TestMain {
	static Logger log = LogManager.getLogger(TestMain.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ConfigurationManager.getInstance().setApiKey(TestConstant.API_KEY); //This value keep the API key.
		ConfigurationManager.getInstance().setCachePath("cache.db");
		try {
			ConfigurationManager.getInstance().setDatabaseManagerClass("org.leolo.trans.london.DatabaseManagerImpl");
		} catch (ReflectiveOperationException e) {
			log.error(e.getMessage(), e);
			System.exit(1);
		}
		CacheManager.getInstance().initialize();
		log.info("Done!");
	}

}
