package com.fantasi.common.db.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import org.junit.experimental.theories.suppliers.TestedOn;

import com.fantasi.common.db.IDBPool;
import com.fantasi.common.db.dao.BaseDictDao;


public class SampleTransactionService extends TransactionService {

	public SampleTransactionService(IDBPool pool) {
		super(pool);
		// TODO Auto-generated constructor stub
	}
	
	BaseDictDao aDao;
	public void test() {
		aDao = new BaseDictDao(pool, "sample_a");
		final BaseDictDao bDao = new BaseDictDao(pool, "sample_b");
		
		
		final Map<String, String> a = new HashMap<String, String>();
		a.put("title", "a");
		
		final Map<String, String> b = new HashMap<String, String>();
		b.put("title", "c");
		
		Integer result = 0;
		try {
			result = this.execute(new Callback<Integer>() {
				public Integer run (Connection conn) throws Exception {
					int result = 0;
					aDao.insert(conn, a);
					result = bDao.insert(conn, b);
					return result;
				}
			});
		} catch (TransactionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(result);
	}

}
