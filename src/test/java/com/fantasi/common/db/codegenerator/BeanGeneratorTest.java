package com.fantasi.common.db.codegenerator;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.fantasi.common.db.DBPool;
import com.fantasi.common.db.codegenerator.BeanGenerator;
import com.fantasi.common.db.tools.DBTable;

import junit.framework.TestCase;

public class BeanGeneratorTest extends TestCase{
	DBPool pool = new DBPool();
	public void setUp() {
		Properties props = new Properties();
		InputStream inStream8 = BeanGeneratorTest.class.getResourceAsStream("/jdbc.properties");
		try {
			props.load(inStream8);
			String driver = props.getProperty("jdbc.driverClassName");
			String connectURI = props.getProperty("jdbc.url");
			String username = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password");
			
			pool.init(driver, connectURI, username, password);
		} catch (IOException e) {
		}
	}
	
	public void testGeneratro() {
		
		//pool.init(driver, connectURI, usename, password);
		DBTable table = new DBTable(pool, "ana_hot_word");
		BeanGenerator bean = new BeanGenerator(table);
		System.out.println(bean.generate());
	}
}
