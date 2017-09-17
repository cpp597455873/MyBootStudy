package com.cpp.web.constant;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by cpp59 on 2017/9/16.
 */
@Component
@ConfigurationProperties(prefix = "config")
public class Config {


    private String ak;

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        if (this.ak != null) {
            return;
        }
        this.ak = ak;
    }

}
