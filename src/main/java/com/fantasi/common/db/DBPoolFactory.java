package com.fantasi.common.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;


import com.fantasi.common.db.task.CheckDBConnectionTask;

public class DBPoolFactory {

	

	public static DBPool getWebDefautDBPool() {
		URL url = DBPoolFactory.class.getResource("/jdbc.properties");
		if (url != null) {
			String path = url.getPath();
			return initFromProperties(path);
		}
		return null;
	}
	
	private static DBPool initFromProperties(String path) {
		Properties props = new Properties();
		String connectURI = "";
		String username = "";
		String password = "";
		String driver = "";
		int refreshInterval = 10 * 1000;
		int printInterval = 10 * 60000;
		boolean enable = true;
		try {
			props.load(new FileInputStream(path));
			driver = props.getProperty("jdbc.driverClassName");
			connectURI = props.getProperty("jdbc.url");
			username = props.getProperty("jdbc.username");
			password = props.getProperty("jdbc.password");
			if (props.getProperty("jdbc.log.enable") != null) {
				enable = Boolean.parseBoolean(props.getProperty("jdbc.log.enable").trim());
			}
			if (props.getProperty("jdbc.log.refreshInterval") != null) {
				refreshInterval = Integer.parseInt(props.getProperty("jdbc.log.refreshInterval").trim());
			}
			if (props.getProperty("jdbc.log.printInterval") != null) {
				printInterval = Integer.parseInt(props.getProperty("jdbc.log.printInterval").trim());
			}

			DBPool pool = new DBPool();
			pool.init(driver, connectURI, username, password);
			
			if (enable) {
				CheckDBConnectionTask task = new CheckDBConnectionTask(pool, refreshInterval, printInterval);
				task.start();
			}
			return pool;
		} catch (IOException e) {

		}
		return null;
	}
}
