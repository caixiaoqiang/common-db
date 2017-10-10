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
        void log(Exception e);
    }

    public static abstract class ExceptionLogger implements IDBExceptionLogger {

    }

    @Override
    public void exceptionCallback(Exception e) {
        if (exceptionLogger != null) {
            exceptionLogger.log(e);
        }
    }

}
