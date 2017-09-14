package com.cpp.web.util;

import com.alibaba.fastjson.JSON;
import com.cpp.web.bean.response.BaseResponse;

import java.util.*;

/**
 * Created by cpp59 on 2017/9/7.
 */
public class BeanUtil {

    public static final Map<String, Object> templeteMap = new LinkedHashMap<>();

    public static Map<String, Object> bean2Map(Object o) {
        return JSON.parseObject(JSON.toJSONString(o), Map.class);
    }

    public static List<Map<String, Object>> bean2ListMap(Object o) {
        return Arrays.asList(JSON.parseObject(JSON.toJSONString(o), Map.class));
    }

    public static List<? extends Map> listBean2MapList(List o) {
        return JSON.parseArray(JSON.toJSONString(o), Map.class);
    }
}
