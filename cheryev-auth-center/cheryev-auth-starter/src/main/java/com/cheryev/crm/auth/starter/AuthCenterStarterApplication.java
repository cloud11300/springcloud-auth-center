package com.cheryev.crm.auth.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Auther: yueyun.pan
 * @Date: 2020/3/11 15:29
 * @Description:
 */
@ComponentScan(basePackages = {
        "com.cheryev.crm.auth.controller",
        "com.cheryev.crm.auth.mapper",
        "com.cheryev.crm.auth.service",
        "com.cheryev.crm.auth.properties",
        "com.cheryev.crm.auth.config",
        "com.cheryev.crm.auth.config",
})
@EnableDiscoveryClient
@SpringBootApplication
public class AuthCenterStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthCenterStarterApplication.class,args);
    }
}
