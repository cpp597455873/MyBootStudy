package com.cpp.web.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

/**
 * Created by cpp59 on 2017/9/19.
 */
public abstract class BaseService {
    @Autowired
    public NamedParameterJdbcTemplate db;
}
