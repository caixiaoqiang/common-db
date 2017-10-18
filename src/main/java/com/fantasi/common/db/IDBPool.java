package com.fantasi.common.db;

import java.sql.Connection;
import java.sql.SQLException;

public interface IDBPool {
	public Connection getConnection() throws SQLException;
	public void close() throws SQLException;
	public void exceptionCallback(Exception e, String positon, String sql, String params);
}
