package com.cheryev.crm.auth.controller;

import com.cheryev.crm.auth.model.OauthClientDetails;
import com.cheryev.crm.auth.pojo.BaseSystemVO;
import com.cheryev.crm.auth.pojo.BaseUserDetail;
import com.cheryev.crm.auth.pojo.BaseUserVO;
import com.cheryev.crm.auth.service.BaseClientDetailService;
import com.cheryev.crm.auth.service.BaseSystemService;
import com.cheryev.crm.auth.service.BaseUserDetailService;
import com.cheryev.crm.auth.service.UsernameUserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
@SessionAttributes({"authorizationRequest"})
public class MvcController {

    private Logger logger = LoggerFactory.getLogger(MvcController.class);

    @Autowired
    private BaseClientDetailService baseClientDetailService;

    @Autowired
    private UsernameUserDetailService usernameUserDetailService;

    /**
     * 登出回调
     * @param request
     * @param response
     */
    @RequestMapping("/backReferer")
    public void sendBack(HttpServletRequest request, HttpServletResponse response) {

        try {
            //sending back to client app
            String referer = request.getHeader("referer");
            if (referer != null) {
                int index = referer.indexOf("?");
                if(index != -1)
                    referer = referer.substring(0, index);
                response.sendRedirect(referer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    @RequestMapping("/oauth/authorize")
//    public ModelAndView  authorize(Map<String, Object> model,HttpServletRequest request) {
//        logger.info("==================logger:"+model);
//        return new ModelAndView("authorize",model);
//    }

    @RequestMapping("/user")
    @ResponseBody
    public BaseUserVO getUser() {
        // 获取用户名
        String userName = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
        BaseUserVO baseUserVO = usernameUserDetailService.getUserByName(userName);
        return baseUserVO;
    }

    /**
     * 授权页面
     * @param model
     * @return
     */
    @RequestMapping("/oauth/confirm_access")
    public ModelAndView authorizePage(Map<String, Object> model,HttpServletRequest request) {
        // 获取用户名
        String userName = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
        model.put("userName", userName);
        return new ModelAndView("authorize", model);
    }

    /**
     * 主页，未从客户端跳转直接登陆会显示
     * @param model
     * @return
     */
    @RequestMapping("/")
    public ModelAndView index(Map<String, Object> model) {
        // 获取用户名
        String userName = ((UserDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal())
                .getUsername();
        model.put("userName", userName);
        // 获取全部客户端应用
        List<OauthClientDetails> clientDetails = baseClientDetailService.getAllDetails();
//        List<BaseSystemVO> baseSystemList = baseSystemService.getAllSystem(userName);
        if(clientDetails!= null && clientDetails.size()>0) {
            model.put("client", clientDetails);
        } else {
            model.put("client", new ArrayList<>());
        }
        return new ModelAndView("index", model);
    }


}
