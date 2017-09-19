package com.cpp.web.framework.http;

import com.cpp.web.util.LogUtil;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by cpp59 on 2017/9/19.
 */
public class HttpRequestTest {
    @Test
    public void testGet() throws Exception {
        LogUtil.info(HttpRequest.newInstance("https://baidu.com", HttpRequest.Method.GET).send());
    }

    @Test
    public void testPost() throws Exception {
        LogUtil.info(HttpRequest.newInstance("http://127.0.0.1:8080/glass/deal/main", HttpRequest.Method.PUT).jsonMediaType().postStr("{\n" +
                "  \"code\": \"login\",\n" +
                "  \"param\": {\n" +
                "    \"username\": \"cpp\",\n" +
                "    \"pwd\": \"1\"\n" +
                "  }\n" +
                "}").send());
    }

}