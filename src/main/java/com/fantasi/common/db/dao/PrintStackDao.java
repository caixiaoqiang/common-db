package com.fantasi.common.db.dao;

import com.fantasi.common.db.IDBPool;

/**
 * Created by zhangyouce on 2017/10/18.
 */
public abstract class PrintStackDao {
    protected IDBPool pool = null;
    protected void printCallStack(Exception e, String sql, String[] params) {

        StackTraceElement[] stackElements = e.getStackTrace();
        StringBuffer sb = new StringBuffer();
        if (stackElements != null) {
            for (int i = 0; i < stackElements.length; i++) {
                String className = stackElements[i].getClassName();
                if (className.startsWith(this.pool.getExceptionPackageName())) {
                    sb.append(stackElements[i].getClassName()+"\t");
                    sb.append(stackElements[i].getLineNumber()+"\t");
                    sb.append(stackElements[i].getMethodName() + "\n");
                }
            }
        }
        this.pool.logException(e, sb.toString(), sql, params);
    }
}
