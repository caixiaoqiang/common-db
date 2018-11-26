package com.fantasi.common.db.service;

import com.fantasi.common.db.IDBPool;
import com.fantasi.common.db.annotation.Column;
import com.fantasi.common.db.annotation.UUIDColumn;
import com.fantasi.common.db.dao.BaseDictDao;
import com.fantasi.common.db.tools.DBUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by cxq on 2017/6/9.
 */
public abstract class BaseDictDaoService  <T extends Object> extends TransactionService {

    private BaseDictDao dao;

    protected BaseDictDaoService(IDBPool pool) {
        super(pool);

        ParameterizedType type = (ParameterizedType)this.getClass().getGenericSuperclass();
        String typeName = type.getActualTypeArguments()[0].getTypeName();
        String name = DBUtils.getTableNameFromClassName(typeName.substring(typeName.lastIndexOf(".") + 1));
        this.dao = new BaseDictDao(pool, name);
    }

    protected Map<String, String> flushParams(T t) {
        Class<?> clazz = t.getClass();
        Map<String, String> result = new HashMap<>();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
//                clazz.getAnnotationsByType(Column.class);
                Field[] fields = clazz.getDeclaredFields();

                for (int j = 0; j < fields.length; j++) { // 遍历所有属性

                    Field field = fields[j];

                    if (field.isAnnotationPresent(Column.class)) {
                        String name = fields[j].getName(); // 获取属性的名字
                        Method method = t.getClass().getMethod("get" + name.substring(0, 1).toUpperCase() + name.substring(1));
                        Object value = method.invoke(t);
                        if (value != null) {
                            result.put(name, value.toString());
                        }
                    } else if (field.isAnnotationPresent(UUIDColumn.class)) {
                        String name = fields[j].getName();
                        result.put(name, UUID.randomUUID().toString().replace("-", ""));
                    }
                }
            } catch (Exception e) {
            }
        }
        return result;
    }

    protected BaseDictDao getDao() {
        return this.dao;
    }

//    protected abstract BaseDictDao getDao();

    public int save(T t) {
        return dao.insert(flushParams(t));
    }
}