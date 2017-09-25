package com.cpp.web.business;

import com.cpp.web.bean.response.BaseResponse;
import com.cpp.web.framework.AppEngine;
import com.cpp.web.framework.database.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by cpp59 on 2017/9/19.
 */
public abstract class BaseService {
    @Autowired
    public NamedParameterJdbcTemplate db;

    @Autowired
    AppEngine appEngine;

    public BaseResponse callInterface(String code, Map inParam) {
        return appEngine.invoke(code, inParam);
    }

    public List<Map<String, Object>> exeuteMySqlPage(String sql, Map param, Page page) {
        if (page != null) {
            int pageSize = page.getPageSize();
            int startRow = page.getStartRow();
            StringBuilder sqlBuilder = new StringBuilder(sql).append(" limit ").append(startRow).append(",").append(pageSize);
            return db.queryForList(sqlBuilder.toString(), param);
        }
        return db.queryForList(sql, param);
    }

    public <T> List<T> exeuteMySqlPage(String sql, Map param, Page page, Class<T> classType) {
        if (page != null) {
            int pageSize = page.getPageSize();
            int startRow = page.getStartRow();
            StringBuilder sqlBuilder = new StringBuilder(sql).append(" limit ").append(startRow).append(",").append(pageSize);
            return db.queryForList(sqlBuilder.toString(), param);
        }
        return db.queryForList(sql, param);
    }

}
