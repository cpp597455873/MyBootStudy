package com.cpp.web.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by cpp59 on 2017/9/7.
 */
@Component
public abstract class BaseController {
    @Autowired
    JdbcTemplate db;
}
