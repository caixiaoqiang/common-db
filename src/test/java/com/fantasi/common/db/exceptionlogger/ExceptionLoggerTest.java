package com.fantasi.common.db.exceptionlogger;

import com.fantasi.common.db.DBPool;
import com.fantasi.common.db.codegenerator.BeanGeneratorTest;
import com.fantasi.common.db.dao.BaseDao;
import com.fantasi.common.db.dao.BaseTableDao;
import junit.framework.TestCase;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by zhangyouce on 2017/10/10.
 */
public class ExceptionLoggerTest  extends TestCase {

    DBPool pool = new DBPool();
    public void setUp() {
        Properties props = new Properties();
        InputStream inStream8 = BeanGeneratorTest.class.getResourceAsStream("/jdbc.properties");
        try {
            props.load(inStream8);
            String driver = props.getProperty("jdbc.driverClassName");
            String connectURI = props.getProperty("jdbc.url");
            String username = props.getProperty("jdbc.username");
            String password = props.getProperty("jdbc.password");

            pool.init(driver, connectURI, username, password);


            pool.setExceptionLogger(new DBPool.IDBLogger() {
                @Override
                public void logException(Exception e, String position, String sql, String[] params) {
                    System.out.println(e);
                    System.out.println(position);
                }

                @Override
                public void logSql(String sql, String[] params) {

                }

            });

        } catch (IOException e) {
        }
    }

    public void test() {
        BaseDao baseDao = new BaseDao(pool);

        baseDao.execute("insert into test(id) values(1)");

        BaseTableDao baseTableDao = new BaseTableDao(pool);

        Map<String, String> data = new HashMap<>();
        data.put("id", "1");

        List<Map<String, String>> datas = new ArrayList<>();
        datas.add(data);

        baseTableDao.insertTable("test", data);


        baseTableDao.insertTable("test", datas);

    }
}
