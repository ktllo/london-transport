package org.leolo.trans.london;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

import org.sqlite.SQLiteDataSource;

public class DatabaseManagerImpl extends DatabaseManager {
	SQLiteDataSource ds;
	@Override
	public Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
	
	public DatabaseManagerImpl() {
		if(!ConfigurationManager.getInstance().isCacheInMemory()) {
			new File(ConfigurationManager.getInstance().getCachePath()).delete();
		}
		ds = new SQLiteDataSource();
		ds.setUrl("jdbc:sqlite:"+ConfigurationManager.getInstance().getCachePath());
		initDb();
	}

}
