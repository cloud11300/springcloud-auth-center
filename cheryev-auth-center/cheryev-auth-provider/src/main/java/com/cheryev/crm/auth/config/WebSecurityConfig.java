package com.cheryev.crm.auth.config;


import com.cheryev.crm.auth.filter.MyLoginAuthenticationFilter;
import com.cheryev.crm.auth.filter.PhoneLoginAuthenticationFilter;
import com.cheryev.crm.auth.filter.QrLoginAuthenticationFilter;
import com.cheryev.crm.auth.handle.MyLoginAuthSuccessHandler;
import com.cheryev.crm.auth.provider.PhoneAuthenticationProvider;
import com.cheryev.crm.auth.provider.QrAuthenticationProvider;
import com.cheryev.crm.auth.service.PhoneUserDetailService;
import com.cheryev.crm.auth.service.QrUserDetailService;
import com.cheryev.crm.auth.service.UsernameUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * security 开启
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsernameUserDetailService usernameUserDetailService;

    @Autowired
    private PhoneUserDetailService phoneUserDetailService;

    @Autowired
    private QrUserDetailService qrUserDetailService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/webjars/**","/static/**");
        super.configure(web);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                // 自定义过滤器
//                .addFilterAt(getMyLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(getPhoneLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(getQrLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                // 配置登陆页/login并允许访问.loginPage("/login")
                .formLogin().permitAll()
                // 登出页
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/backReferer")
                // 其余所有请求全部需要鉴权认证
                .and().authorizeRequests()//.accessDecisionManager(accessDecisionManager)
                .anyRequest().authenticated()
//                .and().httpBasic()
                // 由于使用的是JWT，我们这里不需要csrf
                .and().csrf().disable();

    }

    /**
     * 用户验证
     * @param auth
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(phoneAuthenticationProvider());
        auth.authenticationProvider(daoAuthenticationProvider());
        auth.authenticationProvider(qrAuthenticationProvider());
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder myEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider1 = new DaoAuthenticationProvider();
        // 设置userDetailsService
        provider1.setUserDetailsService(usernameUserDetailService);
        // 禁止隐藏用户未找到异常
        provider1.setHideUserNotFoundExceptions(false);
        // 使用BCrypt进行密码的hash
        provider1.setPasswordEncoder(myEncoder());
        return provider1;
    }

    @Bean
    public PhoneAuthenticationProvider phoneAuthenticationProvider(){
        PhoneAuthenticationProvider provider = new PhoneAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(phoneUserDetailService);
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }

    @Bean
    public QrAuthenticationProvider qrAuthenticationProvider(){
        QrAuthenticationProvider provider = new QrAuthenticationProvider();
        // 设置userDetailsService
        provider.setUserDetailsService(qrUserDetailService);
        // 禁止隐藏用户未找到异常
        provider.setHideUserNotFoundExceptions(false);
        return provider;
    }

    @Bean
    public MyLoginAuthenticationFilter getMyLoginAuthenticationFilter() {
        MyLoginAuthenticationFilter filter = new MyLoginAuthenticationFilter();
        try {
            filter.setAuthenticationManager(this.authenticationManager());
        } catch (Exception e) {
            // TODO: handle exception
        }
        //如果需要直接返回内容，则需自定义success和fail handler
//        filter.setAuthenticationSuccessHandler(new MyLoginAuthSuccessHandler());
//        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));

        return filter;
    }

    /**
     * 手机验证码登陆过滤器
     * @return
     */
    @Bean
    public PhoneLoginAuthenticationFilter getPhoneLoginAuthenticationFilter() {
        PhoneLoginAuthenticationFilter filter = new PhoneLoginAuthenticationFilter();
        try {
            filter.setAuthenticationManager(this.authenticationManagerBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果需要直接返回内容，则需自定义success和fail handler
//        filter.setAuthenticationSuccessHandler(new MyLoginAuthSuccessHandler());
//        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
        return filter;
    }

    @Bean
    public QrLoginAuthenticationFilter getQrLoginAuthenticationFilter() {
        QrLoginAuthenticationFilter filter = new QrLoginAuthenticationFilter();
        try {
            filter.setAuthenticationManager(this.authenticationManagerBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        filter.setAuthenticationSuccessHandler(new MyLoginAuthSuccessHandler());
        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
        return filter;
    }

    @Bean
    public TokenExtractor getTokenExtractor() {
        return new BearerTokenExtractor();
    }


}
