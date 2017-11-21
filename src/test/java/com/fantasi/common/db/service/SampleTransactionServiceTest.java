package com.fantasi.common.db.service;

import com.fantasi.common.db.DBPool;
import junit.framework.TestCase;

public class SampleTransactionServiceTest extends TestCase {
	
	public void test() {
		
//		IDBPool pool = new DBPool();
		
		DBPool pool = new DBPool();
		pool.init("com.mysql.jdbc.Driver",
				"jdbc:mysql://localhost:3306/test",
				"root",
				"admin");

		pool.setExceptionLogger(new DBPool.IDBLogger() {
			@Override
			public void logException(Exception e, String position, String sql, String[] params) {
				System.out.println(e);
				System.out.println(position);
			}

			@Override
			public void logSql(String sql, String[] params) {

			}
		});

		SampleTransactionService service = new SampleTransactionService(pool);
		
		service.test();
		
		
	}

}
