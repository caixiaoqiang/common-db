package com.fantasi.common.db;

/**
 * Created by zhangyouce on 2017/10/10.
 */
public abstract class LoggerDBPool implements IDBPool{
    private IDBExceptionLogger exceptionLogger = null;
    public void setExceptionLogger(IDBExceptionLogger exceptionLogger) {
        this.exceptionLogger = exceptionLogger;
    }

    public interface IDBExceptionLogger {
        void log(Exception e, String position, String sql, String params);
    }

    public static abstract class ExceptionLogger implements IDBExceptionLogger {

    }

    @Override
    public void exceptionCallback(Exception e, String position, String sql, String params) {
        if (exceptionLogger != null) {
            exceptionLogger.log(e, position, sql, params);
        }
    }

}
