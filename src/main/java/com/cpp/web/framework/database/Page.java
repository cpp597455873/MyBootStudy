package com.cpp.web.framework.database;

/**
 * 分页处理
 * Created by cpp59 on 2017/9/25.
 */
public class Page {
    private int startRow;
    private int pageSize;


    public Page(int startRow, int pageSize) {
        this.startRow = startRow;
        this.pageSize = pageSize;
    }

    public static Page newPage(int startRow, int pageSize){
        return new Page(startRow, pageSize);
    }

    public Page() {
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
