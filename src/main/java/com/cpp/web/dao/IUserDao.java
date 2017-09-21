package com.cpp.web.dao;

import com.cpp.web.bean.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by cpp on 2017/9/22.
 */
public interface IUserDao {

    @Select("SELECT * FROM user")
    List<User> getAllUser();

    @Select("SELECT * FROM user where username=#{0} and pwd=#{1}")
    User login2(String username, String pwd);
}
