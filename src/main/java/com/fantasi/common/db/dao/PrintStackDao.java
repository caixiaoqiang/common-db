package com.fantasi.common.db.dao;

import com.fantasi.common.db.IDBPool;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxq on 2017/10/18.
 */
public abstract class PrintStackDao {
    protected IDBPool pool = null;

    private StringBuffer getStatk(Exception e) {
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
        return sb;
    }

    protected String[] convertObject(Object[] objects) {
        String[] sParams = null;
        if (objects != null) {
            sParams = new String[objects.length];
            for (int i = 0; i < objects.length; i++) {
                if (objects[i] == null) {
                    sParams[i] = "";
                } else {
                    if (objects[i] instanceof BigDecimal) {
                        sParams[i] = ((BigDecimal)objects[i]).toPlainString();
                    } else if (objects[i] instanceof Double) {
                        sParams[i] = ((Double) objects[i]).toString();
                    } else {
                        sParams[i] = (String)objects[i];
                    }

                }

            }
        }
        return sParams;
    }

    protected void printCallStack(Exception e, String sql, String[] params) {
        this.pool.logException(e, getStatk(e).toString(), sql, params);
    }

    protected void printObjectCallStack(Exception e, String sql, Object[] params) {
        this.pool.logException(e, getStatk(e).toString(), sql, convertObject(params));
    }
}
