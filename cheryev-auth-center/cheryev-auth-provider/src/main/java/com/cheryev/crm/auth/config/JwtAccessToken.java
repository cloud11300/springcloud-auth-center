package com.cheryev.crm.auth.config;

import com.cheryev.crm.auth.pojo.*;
import com.cheryev.crm.auth.service.BaseRoleService;
import com.cheryev.crm.auth.token.MyAuthenticationToken;
import com.cheryev.crm.auth.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.token.JdbcClientTokenServices;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.*;

/**
 *
 * 自定义JwtAccessToken转换器
 */
public class JwtAccessToken extends JwtAccessTokenConverter {

    @Autowired
    private BaseRoleService baseRoleService;
    @Autowired
    private RedisTemplate<String, BaseRoleVO> redisTemplate;

    /**
     * 生成token
     * @param accessToken
     * @param authentication
     * @return
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
        System.out.println("JwtAccessToken++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        final Map<String, Object> additionalInfo = new HashMap<>();

        Authentication userAuthentication = authentication.getUserAuthentication();

        // client credential 客户端认证 需要指定对应的角色
        if (authentication.isClientOnly()) {
            String clientId = authentication.getOAuth2Request().getClientId();
            List<BaseRoleVO> roleList = baseRoleService.selectRoleByRoleName(clientId);
            List<GrantedAuthority> authorities = new ArrayList();
            // 清除 Redis 中用户的角色
            redisTemplate.delete(clientId+"-Role");
            roleList.forEach(e -> {
                // 存储用户、角色信息到GrantedAuthority，并放到GrantedAuthority列表
                GrantedAuthority authority = new SimpleGrantedAuthority(e.getRoleId().toString());
                authorities.add(authority);
                //存储角色到redis
                redisTemplate.opsForList().rightPush(clientId+"-Role", e);
            });
            // 加入角色
            additionalInfo.put("authorities", authorities.stream().map(auth -> auth.getAuthority()).toArray());
            defaultOAuth2AccessToken.setAdditionalInformation(additionalInfo);
            defaultOAuth2AccessToken.getAdditionalInformation().put(Constant.OAUTH_TYPE_CLIENT, Constant.CLIENT_TYPE.OAUTH_TYPE_CLIENT.getValue());
        }

        // 密码、手机等其他登录 自定义认证，增加detail
        if (userAuthentication!=null &&
                (UsernamePasswordAuthenticationToken.class.isAssignableFrom(userAuthentication.getClass()) |
                MyAuthenticationToken.class.isAssignableFrom(userAuthentication.getClass()))) {
            // 设置额外用户信息
            BaseUserVO baseUser = ((BaseUserDetail) authentication.getPrincipal()).getBaseUser();
            baseUser.setPassWord(null);
            // 将用户信息添加到token额外信息中
            defaultOAuth2AccessToken.getAdditionalInformation().put(Constant.USER_INFO, baseUser);
            defaultOAuth2AccessToken.getAdditionalInformation().put(Constant.OAUTH_TYPE_CLIENT, Constant.CLIENT_TYPE.OAUTH_TYPE_USER.getValue());
        }

        return super.enhance(defaultOAuth2AccessToken, authentication);
    }

    /**
     * 解析token
     * @param value
     * @param map
     * @return
     */
    @Override
    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map){
        OAuth2AccessToken oauth2AccessToken = super.extractAccessToken(value, map);
        convertData(oauth2AccessToken, oauth2AccessToken.getAdditionalInformation());
        return oauth2AccessToken;
    }

    private void convertData(OAuth2AccessToken accessToken,  Map<String, ?> map) {
        accessToken.getAdditionalInformation().put(Constant.USER_INFO,convertUserData(map.get(Constant.USER_INFO)));

    }

    private BaseUserVO convertUserData(Object map) {
        String json = JsonUtils.deserializer(map);
        BaseUserVO user = JsonUtils.serializable(json, BaseUserVO.class);
        return user;
    }
}
