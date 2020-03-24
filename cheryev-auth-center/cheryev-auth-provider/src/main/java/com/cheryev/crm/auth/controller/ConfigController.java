package com.cheryev.crm.auth.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: yueyun.pan
 * @Date: 2020/3/11 15:35
 * @Description:
 */
@RestController
@RefreshScope
public class ConfigController {

    @Value("${useLocalCache:false}")
    private boolean useLocalCache;

    /**
     * http://localhost:8080/config/get
     */
    @RequestMapping("/config/get")
    public boolean get() {
        return useLocalCache;
    }
}
