package com.fantasi.common.db.process;

import java.util.List;

public class Query {
    private String[] columns;
    private int start = 0;
    private int limit = 30;
    private List<Filter> filters;
    private boolean desc = true;
    private String orderBy = "id";


    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public boolean isDesc() {
        return desc;
    }

    public void setDesc(boolean desc) {
        this.desc = desc;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getOrderByStr() {
        if (desc) {
            return orderBy + " desc";
        } else {
            return orderBy + "asc";
        }
    }

    public String getLimitStr() {
        return start + "," + limit;
    }
}
