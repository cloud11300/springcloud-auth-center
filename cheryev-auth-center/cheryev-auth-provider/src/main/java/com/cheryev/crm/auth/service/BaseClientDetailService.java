package com.cheryev.crm.auth.service;

import com.cheryev.crm.auth.mapper.BaseUserHandleMapper;
import com.cheryev.crm.auth.mapper.OauthClientDetailsHandleMapper;
import com.cheryev.crm.auth.mapper.OauthClientDetailsMapper;
import com.cheryev.crm.auth.model.OauthClientDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: yueyun.pan
 * @Date: 2020/3/17 09:44
 * @Description:
 */
@Service
public class BaseClientDetailService implements ClientDetailsService {

    private Logger logger = LoggerFactory.getLogger(BaseClientDetailService.class);

    @Autowired
    protected OauthClientDetailsMapper oauthClientDetailsMapper;
    @Autowired
    protected OauthClientDetailsHandleMapper oauthClientDetailsHandleMapper;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        logger.info("BaseClientDetailService>>根据客户id获取客户端信息,clientId:{}",clientId);
        OauthClientDetails oauthClientDetails = oauthClientDetailsMapper.selectByPrimaryKey(clientId);
        if (oauthClientDetails==null){
            throw new ClientRegistrationException("该客户端不存在");
        }

        BaseClientDetails clientDetails = new BaseClientDetails(oauthClientDetails.getClientId(),
                oauthClientDetails.getResourceIds(),
                oauthClientDetails.getScope(),
                oauthClientDetails.getAuthorizedGrantTypes(),
                oauthClientDetails.getAuthorities(),
                oauthClientDetails.getWebServerRedirectUri()
        );
        return clientDetails;
    }

    public List<OauthClientDetails> getAllDetails(){
        List<OauthClientDetails> clientDetails = oauthClientDetailsHandleMapper.getAllDetails();
        return clientDetails;
    }

}
