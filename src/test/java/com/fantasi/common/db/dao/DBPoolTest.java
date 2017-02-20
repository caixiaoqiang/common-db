package com.fantasi.common.db.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Random;

import junit.framework.TestCase;

import com.fantasi.common.db.DBPool;
import com.fantasi.common.db.codegenerator.BeanGeneratorTest;

public class DBPoolTest extends TestCase{
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
	
	public void testDao() {
		
	}
	
	public void test() {
		final Random r = new Random();
		
//		Runnable runable1 = new Runnable() {
//			public void run() {
//				for (int i = 1; i <= 5; i++) {
//					
//					
//					try {
//						Thread.sleep(r.nextInt(10));
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					try {
//						pool.getConnection().close();
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					System.out.println("runable1：" + pool.getConnectionInfo());
//                }
//			}
//		};
//		Runnable runable2 = new Runnable() {
//			public void run() {
//				for (int i = 1; i <= 5; i++) {
//					try {
//						Thread.sleep(r.nextInt(10));
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					try {
//						pool.getConnection().close();
//					} catch (SQLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//                    System.out.println("runable2：" + pool.getConnectionInfo());
//                    
//                    
//                }
//			}
//		};
//		
//		
//		new Thread(runable1).start();
//		new Thread(runable2).start();
//		
//		while(true) {
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("now：" + pool.getConnectionInfo());	
//		}
		
		
	}
}
