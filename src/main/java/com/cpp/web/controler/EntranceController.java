package com.cpp.web.controler;

import com.cpp.web.bean.request.RequestBean;
import com.cpp.web.bean.response.BaseResponse;
import com.cpp.web.constant.DefineUrl;
import com.cpp.web.framework.AppEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cpp59 on 2017/9/18.
 * 这个就是APP的入口
 */
@RestController
public class EntranceController {

    @Autowired
    AppEngine appEngine;

    /**
     * 入口房阿发
     *
     * @param requestBean
     * @return
     */
    @RequestMapping(DefineUrl.ENTRANCE)
    @ResponseBody
    public BaseResponse doBusiness(@RequestBody RequestBean requestBean) {
        return appEngine.invoke(requestBean);
    }

}
