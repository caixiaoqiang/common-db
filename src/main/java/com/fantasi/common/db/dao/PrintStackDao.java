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
                if (className.contains("com.fantasi")) {
                    sb.append(stackElements[i].getClassName()+"\t");
//					 sb.append(stackElements[i].getFileName()+"\t");
                    sb.append(stackElements[i].getLineNumber()+"\t");
                    sb.append(stackElements[i].getMethodName() + "\n");
                }
            }
        }

        StringBuffer sb2 = new StringBuffer();
        if (params != null) {
            for(int i = 0; i < params.length; i++){
                sb2.append(params[i]);
            }
        }
        this.pool.exceptionCallback(e, sb.toString(), sql, sb2.toString());
    }
}
