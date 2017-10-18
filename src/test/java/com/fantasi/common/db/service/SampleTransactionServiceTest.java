package com.fantasi.common.db.service;

import junit.framework.TestCase;

import com.fantasi.common.db.DBPool;

public class SampleTransactionServiceTest extends TestCase {
	
	public void test() {
		
//		IDBPool pool = new DBPool();
		
		DBPool pool = new DBPool();
		pool.init("com.mysql.jdbc.Driver",
				"jdbc:mysql://localhost:3306/test",
				"root",
				"admin");

		pool.setExceptionLogger(new DBPool.ExceptionLogger() {
			@Override
			public void log(Exception e, String position, String sql, String params) {
				System.out.println(e);
				System.out.println(position);
			}
		});

		SampleTransactionService service = new SampleTransactionService(pool);
		
		service.test();
		
		
	}

}
