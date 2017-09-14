package com.cpp.web.util;

import java.util.List;

/**
 * Created by cpp59 on 2017/9/7.
 */
public class ListUtil {
    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }

    public static int size(List list) {
        return list == null || list.size() == 0 ? 0 : list.size();
    }
}
