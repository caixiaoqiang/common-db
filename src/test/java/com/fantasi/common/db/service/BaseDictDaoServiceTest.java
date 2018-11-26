package com.fantasi.common.db.service;

import com.fantasi.common.db.IDBPool;
import com.fantasi.common.db.tools.Column;

/**
 * Created by cxq on 2017/6/10.
 */
public class BaseDictDaoServiceTest extends BaseDictDaoService<Column> {
    protected BaseDictDaoServiceTest(IDBPool pool) {
        super(pool);
    }

    public static void main(String[] args) {
        BaseDictDaoServiceTest test = new BaseDictDaoServiceTest(null);
    }
}
