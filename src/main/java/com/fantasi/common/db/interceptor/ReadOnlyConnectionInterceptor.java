package com.fantasi.common.db.interceptor;

import com.fantasi.common.db.DbContextHolder;
import com.fantasi.common.db.annotation.ReadOnlyConnection;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * Created by cxq on 2017/6/8.
 */
@Aspect
@Component
public class ReadOnlyConnectionInterceptor implements Ordered {

//    @Pointcut("execution(public * com.fantasi.common.db.dao.BaseDao.rawQuer*(..))")
//    @Pointcut("execution(* com.fantasi.common.db.dao.DBHelper.*(..))")
    @Pointcut("@annotation(com.fantasi.common.db.annotation.ReadOnlyConnection)")
    public void queryAspect() {
    }


    @Around("queryAspect()")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        try {
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
