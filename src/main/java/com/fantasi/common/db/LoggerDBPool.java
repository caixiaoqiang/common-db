package com.fantasi.common.db;

/**
 * Created by cxq on 2017/10/10.
 */
public abstract class LoggerDBPool implements IDBPool{
    private ISqlLogger logger = null;
    private IExceptionLogger exceptionLogger = null;
    private IPackageNameGetter packageNameGetter = null;

    public void setSqlLogger(ISqlLogger logger) {
        this.logger = logger;
    }

    public void setExceptionLogger(IExceptionLogger exceptionLogger) {
        this.exceptionLogger = exceptionLogger;
    }

    public void setPackageNameGetter(IPackageNameGetter packageNameGetter) {
        this.packageNameGetter = packageNameGetter;
    }


    @FunctionalInterface
    public interface ISqlLogger {
        void log(String sql, String[] params);
    }
    @FunctionalInterface
    public interface IExceptionLogger {
        void log(Exception e, String position, String sql, String[] params);
    }

    @FunctionalInterface
    public interface IPackageNameGetter {
        String get();
    }

    @Override
    public void logException(Exception e, String position, String sql, String[] params) {
        if (exceptionLogger != null) {
            exceptionLogger.log(e, position, sql, params);
        }
    }

    @Override
    public void logSql(String sql, String[] params) {
        if (logger != null) {
            logger.log(sql, params);
        }
    }

    @Override
    public String getExceptionPackageName() {
        if (packageNameGetter == null) {
            return "com.fantasi";
        }
        return packageNameGetter.get();
    }
}
