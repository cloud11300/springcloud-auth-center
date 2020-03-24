package com.cheryev.crm.auth.config;


import com.cheryev.crm.auth.service.BaseClientDetailService;
import com.cheryev.crm.auth.service.UsernameUserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;

/**
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(AuthorizationServerConfig.class);

    @Autowired
    private UsernameUserDetailService usernameUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BaseClientDetailService baseClientDetailService;

    @Autowired
    private BaseClientDetailService clientDetailsService;

    @Autowired
    private DataSource dataSource;

    @Bean
    public TokenStore getJdbcTokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean // 声明 ClientDetails实现
    public ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        JdbcAuthorizationCodeServices authorizationCodeServices = new JdbcAuthorizationCodeServices(dataSource);
        log.info("Create AuthorizationCodeServices :: " + authorizationCodeServices);
        return authorizationCodeServices;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 使用JdbcClientDetailsService客户端详情服务
//        clients.withClientDetails(baseClientDetailService);
//        clients.withClientDetails(clientDetailsService);
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.GET,HttpMethod.POST)
                // 配置JwtAccessToken转换器
                .accessTokenConverter(jwtAccessTokenConverter())
                .authorizationCodeServices(authorizationCodeServices())
                // refresh_token需要userDetailsService
                .reuseRefreshTokens(false).userDetailsService(usernameUserDetailService)
                .tokenStore(getJdbcTokenStore());

    }

//    public AuthorizationCodeServices authorizationCodeServices() {
//        return new JdbcAuthorizationCodeServices(dataSource);
//    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                .allowFormAuthenticationForClients()
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()");
    }

    /**
     * 使用非对称加密算法来对Token进行签名
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {

        final JwtAccessTokenConverter converter = new JwtAccessToken();
        // 导入证书
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "mypass".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("mytest"));

        return converter;
    }

    /**
     * 跨域, 开发环境使用 vue-cli 代理，正式用nginx
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean =  new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}
