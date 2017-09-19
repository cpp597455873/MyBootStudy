package com.cpp.web.business;

import com.cpp.web.bean.response.BaseResponse;
import com.cpp.web.constant.ErrorCode;
import com.cpp.web.framework.annotation.BusinessModule;
import com.cpp.web.framework.annotation.BusinessMethod;
import com.cpp.web.util.ListUtil;
import com.cpp.web.util.SecurityUtil;
import com.cpp.web.util.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by cpp59 on 2017/9/6.
 */
@Component
@BusinessModule
public class UserService extends BaseService {

    /**
     * 登录
     *
     * @param inParam
     * @return
     */
    @BusinessMethod
    public BaseResponse login(Map<String, Object> inParam) {
        String username = (String) inParam.get("username");
        String pwd = (String) inParam.get("pwd");
        List<Map<String, Object>> maps = db.queryForList("SELECT * FROM user WHERE username=:username", inParam);
        if (ListUtil.isEmpty(maps)) {
            return new BaseResponse(ErrorCode.LOGIN_FAIL, "用户名或密码错误");
        } else if (!SecurityUtil.isPwdMathch(pwd, (String) maps.get(0).get("pwd"))) {
            return new BaseResponse(ErrorCode.LOGIN_FAIL, "用户名或密码错误");
        } else {
            return new BaseResponse(ErrorCode.SUCCESS, "登录成功", maps);
        }
    }

    /**
     * 退出
     *
     * @param inParam
     * @return
     */
    @BusinessMethod
    public BaseResponse logout(Map<String, Object> inParam) {
        return BaseResponse.emptySuccessResult();
    }

    /**
     * 注册
     *
     * @param inParam
     * @return
     */
    @BusinessMethod
    public BaseResponse register(Map<String, Object> inParam) {
        String username = (String) inParam.get("username");
        String pwd = (String) inParam.get("pwd");


        if (StringUtils.isEmpty(username)) {
            return new BaseResponse(ErrorCode.PARAM_EMPTY, "用户名不能为空");
        }
        if (StringUtils.isEmpty(pwd)) {
            return new BaseResponse(ErrorCode.PARAM_EMPTY, "密码不能为空");
        }

        boolean userExist = db.queryForObject("SELECT count(*) count FROM user WHERE username=:username", inParam, Integer.class) != 0;
        if (userExist) {
            return new BaseResponse(ErrorCode.USER_EXIST, "该用户名已被注册");
        }
        inParam.put("password_encoded", SecurityUtil.encodePlainPassword(pwd));
        boolean success = db.update("INSERT INTO user (username, pwd) VALUES (:username,:password_encoded)", inParam) == 0;

        if (!success) {
            new BaseResponse(ErrorCode.SERVER_ERROR_UNKNOWN, "注册失败服务器未知异常");
        }
        BaseResponse loginResult = callInterface("login", inParam);
        if (loginResult.successExecute()) {
            loginResult.setMsg("注册成功");
        }
        return loginResult;
    }
}
