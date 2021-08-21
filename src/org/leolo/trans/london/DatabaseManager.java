package org.leolo.trans.london;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class DatabaseManager {
	
	protected Logger log = LogManager.getLogger(DatabaseManager.class);
	
	public abstract Connection getConnection() throws SQLException;
	public long getAge(String key) {
		try(
				Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement("SELECT value FROM cache_age WHERE key = ?")
			){
			stmt.setString(1, key);
			try(ResultSet rs = stmt.executeQuery()){
				if(rs.next()) {
					return System.currentTimeMillis() - rs.getTimestamp(1).getTime();
				}
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
		return Long.MAX_VALUE;
	}
	
	public void setCacheTime(String key) {
		try(
				Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement("REPLACE INTO cache_age(key, value) VALUES (?,?)")
			){
			stmt.setString(1, key);
			stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			stmt.executeUpdate();
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	protected void initDb() {
		try(Connection conn = getConnection()){
			//Remove existing tables
			try(
					ResultSet rs = conn.getMetaData().getTables(null, null, null, null);
					Statement stmt = conn.createStatement();
			){
				while(rs.next()) {
					String table = rs.getString("TABLE_NAME");
					log.info("Dropping table {}", table);
					stmt.executeUpdate("DROP TABLE "+table);
				}
			} catch (SQLException e) {
				log.error(e.getMessage(), e);
				log.warn("Some table cannot be removed.");
			}
			log.info("DB is clear. Creating table!");
			try(Statement stmt = conn.createStatement()){
				stmt.executeUpdate("CREATE TABLE metadata(key TEXT NOT NULL PRIMARY KEY, value TEXT NOT NULL)");
				stmt.executeUpdate("CREATE TABLE cache_age(key TEXT NOT NULL PRIMARY KEY, value DATE NOT NULL)");
				stmt.executeUpdate("CREATE TABLE \"line\" ( \"uid\" TEXT NOT NULL, \"id\" TEXT NOT NULL, \"name\" TEXT, \"mode\" TEXT, PRIMARY KEY(\"uid\") )");
				stmt.executeUpdate("CREATE TABLE \"line_section\" ( \"uid\" TEXT NOT NULL, \"route\" TEXT NOT NULL, \"direction\" TEXT, \"origin\" TEXT, \"destination\" TEXT, \"validFrom\" date, \"validTo\" date, PRIMARY KEY(\"uid\") )");
				stmt.executeUpdate("CREATE TABLE \"line_section_point\" ( \"uid\" TEXT NOT NULL, \"name\" TEXT, \"NaPTAN\" TEXT, PRIMARY KEY(\"uid\") )");
			} catch (SQLException e) {
				log.error(e.getMessage(), e);
			}
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
		}
	}
}
