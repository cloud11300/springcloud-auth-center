//package com.cheryev.crm.auth.config;
//
//import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//
///**
// * @Auther: yueyun.pan
// * @Date: 2020/3/12 13:47
// * @Description:
// */
//@Configuration
//public class AuthResourcesAutoConfiguration implements JwtAccessTokenConverterConfigurer {
//
//    @Bean
//    public JwtAccessTokenConverter jwtAccessTokenConverter() {
//        return new JwtAccessToken();
//    }
//
//    @Override
//    public void configure(JwtAccessTokenConverter jwtAccessTokenConverter) {
//        jwtAccessTokenConverter.setAccessTokenConverter(jwtAccessTokenConverter());
//    }
//}
//
