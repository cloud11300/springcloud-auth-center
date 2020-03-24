package com.cheryev.crm.auth.handle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Auther: yueyun.pan
 * @Date: 2020/3/13 12:29
 * @Description:
 */
public class MyLoginAuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger log = LoggerFactory.getLogger(MyLoginAuthSuccessHandler.class);

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功之后的处理");
        System.out.println("onAuthenticationSuccess：");
        request.setAttribute("access_token", "sf");
        request.setAttribute("redfa", "sf");
        getRedirectStrategy().sendRedirect(request, response, "/code");
        super.onAuthenticationSuccess(request, response, authentication);
    }

}
