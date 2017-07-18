package com.fantasi.common.db.interceptor;

import com.fantasi.common.db.DbContextHolder;
import com.fantasi.common.db.annotation.ReadOnlyConnection;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * Created by zhangyouce on 2017/6/8.
 */
@Aspect
@Component
public class ReadOnlyConnectionInterceptor implements Ordered {
    @Around("@annotation(readOnlyConnection)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint, ReadOnlyConnection readOnlyConnection) throws Throwable {
        try {
//                logger.info("set database connection to read only");
            DbContextHolder.setDbType(DbContextHolder.DbType.SLAVE);
            Object result = proceedingJoinPoint.proceed();
            return result;
        } finally {
            DbContextHolder.clearDbType();
//                logger.info("restore database connection");
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
