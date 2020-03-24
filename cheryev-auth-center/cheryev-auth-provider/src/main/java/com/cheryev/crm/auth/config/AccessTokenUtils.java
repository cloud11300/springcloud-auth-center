package com.cheryev.crm.auth.config;

import com.cheryev.crm.auth.pojo.BaseModuleResourcesVO;
import com.cheryev.crm.auth.pojo.BaseRoleVO;
import com.cheryev.crm.auth.pojo.BaseUserVO;
import com.cheryev.crm.auth.pojo.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Auther: yueyun.pan
 * @Date: 2020/3/12 14:41
 * @Description:
 */
public class AccessTokenUtils {

    @Autowired
    private RedisTemplate<String, BaseRoleVO> baseRoleTemplate;

    @Autowired
    private RedisTemplate<String, BaseModuleResourcesVO> baseModelTemplate;

    private BaseUserVO baseUserVO;
    private String clientId;
    private String clientType;

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * 从token获取用户信息
     * @return
     */
    public BaseUserVO getBaseUserVO() {
        return baseUserVO;
    }

    public void setBaseUserVO(BaseUserVO baseUserVO) {
        this.baseUserVO = baseUserVO;
    }

    public List<BaseRoleVO> getRoleInfo(){
        String roleKey = "";
        long size = 0;
        if (Constant.CLIENT_TYPE.OAUTH_TYPE_USER.getValue().equals(clientType)) {//用户鉴权
            roleKey = getBaseUserVO().getUserId().toString() + "-Role";
            size = baseRoleTemplate.opsForList().size(roleKey);
        }
        if (Constant.CLIENT_TYPE.OAUTH_TYPE_CLIENT.getValue().equals(clientType)) {//客户端鉴权
            String clientId = getClientId().trim();
            roleKey = clientId + "-Role";
            size = baseRoleTemplate.opsForList().size(roleKey);
        }
        return baseRoleTemplate.opsForList().range(roleKey, 0, size);
    }

    public List<BaseModuleResourcesVO> getMenuInfo(){
        String resourceKey = getBaseUserVO().getUserId() + "-Resource";
        long size = baseModelTemplate.opsForList().size(resourceKey);
        return baseModelTemplate.opsForList().range(resourceKey, 0, size);
    }

}

