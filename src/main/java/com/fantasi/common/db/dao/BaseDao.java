package com.fantasi.common.db.dao;

import com.fantasi.common.db.IDBPool;
import com.fantasi.common.db.annotation.ReadOnlyConnection;
import com.fantasi.common.db.service.UpdateNoneException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseDao extends PrintStackDao{
	
	public BaseDao() {}
	public BaseDao(IDBPool pool) {
		this.pool = pool;
	}
	
	public void setDBPool(IDBPool pool) {
		this.pool = pool;
	}
	
	public IDBPool getDBPool() {
		return this.pool;
	}
	
	/**
	 * 执行一条sql语句
	 * @param sql
	 * @return 返回影响行数
	 */
	public int execute(String sql) {
		return this.execute(sql, null);
	}
	
	/**
	 * 执行一条sql语句
	 * @param sql
	 * @param params
	 * @return 返回影响行数
	 */
	public int execute(String sql, String[] params) {
		Connection conn = null;
		try {
			conn = this.pool.getConnection();
			return this.execute(conn, sql, params);
		} catch (SQLException e) {
			printCallStack(e, sql, params);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				//关闭数据库连接的时候出现异常了
			}
		}
		return -1;
	}
	
	public int execute(Connection conn, String sql, String[] params) throws SQLException {
		this.pool.logSql(sql, params);
		return DBHelper.execute(conn, sql, params);
	}

	public int executeWithException(Connection conn, String sql, String[] params) throws SQLException {
		this.pool.logSql(sql, params);
		int result = DBHelper.execute(conn, sql, params);
		if (result < 1) {
			throw new UpdateNoneException("update none");
		}
		return result;
	}


	/**
	 * 执行一条sql语句
	 * @param sql
	 * @return 返回影响行数
	 */
	public int executeObject(String sql) {
		return this.executeObject(sql, null);
	}

	/**
	 * 执行一条sql语句
	 * @param sql
	 * @param params
	 * @return 返回影响行数
	 */
	public int executeObject(String sql, Object[] params) {
		Connection conn = null;
		try {
			conn = this.pool.getConnection();
			return this.executeObject(conn, sql, params);
		} catch (SQLException e) {
			printObjectCallStack(e, sql, params);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				//关闭数据库连接的时候出现异常了
			}
		}
		return -1;
	}

	public int executeObject(Connection conn, String sql, Object[] params) throws SQLException {
		this.pool.logSql(sql, convertObject(params));
		return DBHelper.executeObject(conn, sql, params);
	}

	public int executeObjectWithException(Connection conn, String sql, Object[] params) throws SQLException {
		this.pool.logSql(sql, convertObject(params));
		int result = DBHelper.execute(conn, sql, params);
		if (result < 1) {
			throw new UpdateNoneException("update none");
		}
		return result;
	}


	/**
	 * 查询
	 * @param sql
	 * @return 当没有结果时返回 null
	 */
	public Map<String, String> rawQueryForMap(String sql) {
		return rawQueryForMap(sql, null);
	}
	
	/**
	 * 查询
	 * @param sql
	 * @param params
	 * @return 当没有结果时返回 null
	 */
	@ReadOnlyConnection
	public Map<String, String> rawQueryForMap(String sql, String[] params) {
		List<Map<String, String>> list = this.rawQuery(sql, params);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查询
	 * @param sql
	 * @return 当没有结果时返回 null
	 */
	public int rawQueryForInt(String sql) {
		return rawQueryForInt(sql, null);
	}
	
	/**
	 * 查询
	 * @param sql
	 * @param params
	 * @return 当没有结果时返回 null
	 */
	@ReadOnlyConnection
	public int rawQueryForInt(String sql, String[] params) {
		Connection conn = null;
		try {
			conn = this.pool.getConnection();
			this.pool.logSql(sql, params);
			return DBHelper.queryForInt(conn, sql, params);
		} catch (SQLException e) {
			printCallStack(e, sql, params);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				//关闭数据库连接的时候出现异常了
			}
		}
		return -1;
	}

	/**
	 * 查询
	 * @param sql
	 * @return 当没有结果时返回 null
	 */
	public long rawQueryForLong(String sql) {
		return rawQueryForLong(sql, null);
	}

	/**
	 * 查询
	 * @param sql
	 * @param params
	 * @return 当没有结果时返回 null
	 */
	@ReadOnlyConnection
	public long rawQueryForLong(String sql, String[] params) {
		Connection conn = null;
		try {
			conn = this.pool.getConnection();
			this.pool.logSql(sql, params);
			return DBHelper.queryForLong(conn, sql, params);
		} catch (SQLException e) {
			printCallStack(e, sql, params);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				//关闭数据库连接的时候出现异常了
			}
		}
		return -1;
	}

	/**
	 * 查询
	 * @param sql
	 * @param params
	 * @return 当没有结果时返回 null
	 */
	@ReadOnlyConnection
	public String rawQueryForString(String sql, String[] params) {
		Connection conn = null;
		try {
			conn = this.pool.getConnection();
			this.pool.logSql(sql, params);
			return DBHelper.queryForString(conn, sql, params);
		} catch (SQLException e) {
			printCallStack(e, sql, params);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				//关闭数据库连接的时候出现异常了
			}
		}
		return null;
	}
	
	/**
	 * 查询
	 * @param sql
	 * @return 当没有结果集时返回 null
	 */
	public List<Map<String, String>> rawQuery(String sql) {
		return this.rawQuery(sql, null);
	}
	
	/**
	 * 查询
	 * @param sql
	 * @param params
	 * @return 当没有结果集时返回 null
	 */
	@ReadOnlyConnection
	public List<Map<String, String>> rawQuery(String sql, String[] params) {
		Connection conn = null;
		try {
			conn = this.pool.getConnection();
			this.pool.logSql(sql, params);
			return DBHelper.query(conn, sql, params);
		} catch (SQLException e) {
			printCallStack(e, sql, params);
		} finally {
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				//关闭数据库连接的时候出现异常了
			}
		}
		return new ArrayList<Map<String,String>>();
	}
}