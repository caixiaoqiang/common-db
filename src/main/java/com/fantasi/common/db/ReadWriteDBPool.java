package com.fantasi.common.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zhangyouce on 2017/6/8.
 */
public class ReadWriteDBPool implements IDBPool{

    private DataSource writeDataSource = null;
    private List<DataSource> readDataSources = null;
    private int readDataSourceCount = 0;
    private int index = 0;

    public ReadWriteDBPool(DataSource writeDataSource, List<DataSource> readDataSources) {
        this.setWriteDataSource(writeDataSource);
        this.setReadDataSource(readDataSources);
    }

    public void setWriteDataSource(DataSource dataSource) {
        this.writeDataSource = dataSource;
    }

    public void setReadDataSource(List<DataSource> dataSources) {
        readDataSources = dataSources;
        if (dataSources != null) {
            readDataSourceCount = dataSources.size();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (DbContextHolder.getDbType() == DbContextHolder.DbType.MASTER) {
            return writeDataSource.getConnection();
        } else {
            if (index == readDataSourceCount) {
                index = index - readDataSourceCount;
            }
            return readDataSources.get(index).getConnection();
        }
    }

    @Override
    public void close() throws SQLException {

    }
}
