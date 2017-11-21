package com.fantasi.common.db;

/**
 * Created by zhangyouce on 2017/10/10.
 */
public abstract class LoggerDBPool implements IDBPool{
    private IDBLogger logger = null;
    public void setExceptionLogger(IDBLogger logger) {
        this.logger = logger;
    }

    public interface IDBLogger {
        void logException(Exception e, String position, String sql, String[] params);
        void logSql(String sql, String[] params);
    }

    public static abstract class Logger implements IDBLogger {

    }

    @Override
    public void exceptionCallback(Exception e, String position, String sql, String[] params) {
        if (logger != null) {
            logger.logException(e, position, sql, params);
        }
    }

    @Override
    public void logSql(String sql, String[] params) {
        if (logger != null) {
            logger.logSql(sql, params);
        }
    }

}
