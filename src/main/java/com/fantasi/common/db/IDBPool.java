package com.fantasi.common.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDBPool {
	public Connection getConnection() throws SQLException;
	public void close() throws SQLException;
	public void logException(Exception e, String positon, String sql, String[] params);
	public void logSql(String sql, String[] params);
	public String getExceptionPackageName();
}
