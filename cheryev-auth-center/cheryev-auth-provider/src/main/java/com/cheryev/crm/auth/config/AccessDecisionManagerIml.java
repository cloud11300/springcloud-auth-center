package com.cheryev.crm.auth.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.cheryev.crm.auth.pojo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
@Component
public class AccessDecisionManagerIml implements AccessDecisionManager {

    private static final Logger logger = LoggerFactory.getLogger(AccessDecisionManagerIml.class);

    private AntPathMatcher matcher = new AntPathMatcher();

    private String[] ignoreds;

    @Value("${auth-ignored}")
    private String ignored;
//    private String ignored ="/login,/css/**,/js/**,/favicon.ico,/webjars/**,/images/**,/static/**,/hystrix.stream/**,/info,/error,/health,/env,/metrics,/trace,/dump,/jolokia,/configprops,/activiti,/logfile,/refresh,/flyway,/liquibase,/loggers,/druid/**,/oauth/deleteToken,/backReferer";

    @Value("${spring.application.name}")
    private String applicationName;

    private String url;

    private String httpMethod;

    public String getIgnored() {
        return ignored;
    }

    @Autowired
    private AccessTokenUtils accessTokenUtils;

    @Autowired
    private JwtAccessTokenConverter tokenStore;

    @Autowired
    private TokenExtractor tokenExtractor;

    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        // 请求路径
        url = getUrl(o);
        // http 方法
        httpMethod = getMethod(o);

        this.setIgnored(ignored);
        // 不拦截的请求
        for(String path : ignoreds){
            String temp = path.trim();
            if (matcher.match(temp, url)) {
                return;
            }
        }

        logger.info("authentication url:{}",url);
        logger.info("authentication httpMethod:{}",httpMethod);

        Authentication a = tokenExtractor.extract(((FilterInvocation)o).getRequest());
        DecodedJWT decodeToken= JWT.decode((String)a.getPrincipal());
        Map<String, Claim> map1 =  decodeToken.getClaims();//debug

        String clientType = decodeToken.getClaim(Constant.OAUTH_TYPE_CLIENT).asString();
        accessTokenUtils.setClientType(clientType);
        if (Constant.CLIENT_TYPE.OAUTH_TYPE_USER.getValue().equals(clientType)){//用户鉴权
            BaseUserVO baseUserVO = decodeToken.getClaim(Constant.USER_INFO).as(BaseUserVO.class);
            accessTokenUtils.setBaseUserVO(baseUserVO);
            // URL 鉴权
            Iterator<BaseRoleVO> iterator = accessTokenUtils.getRoleInfo().iterator();
            while (iterator.hasNext())
            {   BaseRoleVO baseRole = iterator.next();
                if (baseRole.getModules()!=null && baseRole.getModules().size() > 0 && checkSubModule(baseRole.getModules())) {
                    return;
                }
            }
        }

        if (Constant.CLIENT_TYPE.OAUTH_TYPE_CLIENT.getValue().equals(clientType)){//客户端鉴权
            String clientId = decodeToken.getClaim(Constant.OAUTH_CLIENT_ID).asString();
            accessTokenUtils.setClientId(clientId);
            // URL 鉴权
            Iterator<BaseRoleVO> iterator = accessTokenUtils.getRoleInfo().iterator();
            while (iterator.hasNext())
            {   BaseRoleVO baseRole = iterator.next();
                if (baseRole.getModules()!=null && baseRole.getModules().size() > 0 && checkSubModule(baseRole.getModules())) {
                    return;
                }
            }
        }

        throw new AccessDeniedException("no permission!");

    }

    /**
     *  获取请求中的url
     */
    private String getUrl(Object o) {
        //获取当前访问url
        String url = ((FilterInvocation)o).getRequestUrl();
        int firstQuestionMarkIndex = url.indexOf("?");
        if (firstQuestionMarkIndex != -1) {
            return url.substring(0, firstQuestionMarkIndex);
        }
        return url;
    }

    private String getMethod(Object o) {
        return ((FilterInvocation)o).getRequest().getMethod();
    }

    // 检查子模块权限
    private boolean checkSubModule(List<BaseModuleResourcesVO> modules) {

        Iterator<BaseModuleResourcesVO> iterator = modules.iterator();

        logger.info("authentication modules size:{}",modules.size());

        while (iterator.hasNext())
        {
            BaseModuleResourcesVO e = iterator.next();
            logger.info("authentication ModuleType:{}",e.getProjectName());
            // 匹配当前应用的资源
//            if(applicationName.equals(e.getProjectName())) {
                logger.info("authentication ModuleType:{}",e.getModuleType());
                //菜单权限匹配
                if (Constant.MODULE_REPSPIRCES_TYPE_MENU.equals(e.getModuleType())){//菜单
                    BaseModuleMenuVO moduleMenuVO = e.getModuleMenu();
                    if (moduleMenuVO.getTextLocal() != null && !"".equals(moduleMenuVO.getTextLocal())) {
                        if (matchUrl(url,moduleMenuVO.getTextLocal())) {
                            return true;
                        }
                    }
                    // 递归检查子模块的权限
                    if (moduleMenuVO.getSubModules()!=null && moduleMenuVO.getSubModules().size() > 0) {
                        if (checkSubModuleMenu(moduleMenuVO.getSubModules())) {
                            return true;
                        }
                    }
                }else if(Constant.MODULE_REPSPIRCES_TYPE_OPERATION.equals(e.getModuleType())){//操作权限

                }else if(Constant.MODULE_REPSPIRCES_TYPE_API.equals(e.getModuleType())){//api接口
                    BaseModuleApisVO moduleApisVO = e.getModuleApi();
                    if (moduleApisVO.getApiUrl() != null && !"".equals(moduleApisVO.getApiUrl())) {
                        if (matchUrl(url, moduleApisVO.getApiUrl())) {
                            return true;
                        }
                    }
                }
//            }
        }
        return false;
    }

    private boolean checkSubModuleMenu(List<BaseModuleMenuVO> subModules){
        if (subModules==null || subModules.size()<1){
            return false;
        }
        for (BaseModuleMenuVO moduleMenuVO :subModules ) {
            if (moduleMenuVO.getTextLocal() != null && !"".equals(moduleMenuVO.getTextLocal())) {
                if (matchUrl(url,moduleMenuVO.getTextLocal())) {
                    return true;
                }
            }
            // 递归检查子模块的权限
            if (moduleMenuVO.getSubModules().size() > 0) {
                if (checkSubModuleMenu(moduleMenuVO.getSubModules())) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean matchUrl(String url, String modulePath) {

        List urls = Arrays.asList(url.split("/")).stream().filter(e -> !"".equals(e)).collect(Collectors.toList());
        Collections.reverse(urls);

        List paths = Arrays.asList(modulePath.split("/")).stream().filter(e -> !"".equals(e)).collect(Collectors.toList());
        Collections.reverse(paths);

        // 如果数量不相等
        if (urls.size() != paths.size()) {
            return false;
        }

        for(int i = 0; i < paths.size(); i++){
            // 如果是 PathVariable 则忽略
            String item = (String) paths.get(i);
            if (item.charAt(0) != '{' && item.charAt(item.length() - 1) != '}') {
                // 如果有不等于的，则代表 URL 不匹配
                if (!item.equals(urls.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    public void setIgnored(String ignored) {
        this.ignored = ignored;
        if(ignored != null && !"".equals(ignored))
            this.ignoreds = ignored.split(",");
        else
            this.ignoreds = new String[]{};
    }

}
