package com.cpp.web.util;

import com.cpp.web.constant.SystemConstant;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

/**
 * Created by cpp59 on 2017/9/10.
 */
public class SecurityUtil {
    private static final StandardPasswordEncoder standardPasswordEncoder = new StandardPasswordEncoder(SystemConstant.PWD_KEY);

    public static String encodePlainPassword(String plainPwd) {
        return standardPasswordEncoder.encode(plainPwd);
    }

    public static boolean isPwdMathch(String plainPwd, String encodedPwd) {
        return standardPasswordEncoder.matches(plainPwd, encodedPwd);
    }


}
