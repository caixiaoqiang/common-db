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
			public void log(Exception e) {
				System.out.println("error:" + e);
//                    logger.error(e);
			}
		});

		SampleTransactionService service = new SampleTransactionService(pool);
		
		service.test();
		
		
	}

}
