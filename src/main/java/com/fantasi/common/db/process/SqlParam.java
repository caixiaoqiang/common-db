package com.fantasi.common.db.process;

import java.util.Map;

public class SqlParam {
	private String sql;
	private String[] params;

	public SqlParam (String sql, String[] params) {
		this.sql = sql;
		this.params = params;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

	public static SqlParam getSqlParam(String table, Map<String, String> data) {
		String columnStr = "";
		String valueStr = "";
		String[] params = new String[data.keySet().size()];
		int index = 0;
		for (String key : data.keySet()) {
			columnStr += '`' + key + "`,";
			valueStr += "?,";
			params[index++] = data.get(key);
		}
		columnStr = columnStr.substring(0, columnStr.length() - 1);
		valueStr = valueStr.substring(0, valueStr.length() - 1);

		String sql = "insert into " + table + "(" + columnStr + ")"
				+ " values (" + valueStr + ");";


		return new SqlParam(sql, params);
	}
}
