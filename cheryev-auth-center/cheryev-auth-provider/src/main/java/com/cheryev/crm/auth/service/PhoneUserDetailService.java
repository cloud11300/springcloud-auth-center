package com.cheryev.crm.auth.service;

import com.cheryev.crm.auth.mapper.BaseUserHandleMapper;
import com.cheryev.crm.auth.pojo.BaseUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class PhoneUserDetailService extends BaseUserDetailService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected BaseUserHandleMapper baseUserHandleMapper;

    @Override
    protected BaseUserVO getUser(String phone) {
        //根据电话号码查询用户
        BaseUserVO baseUserVO = baseUserHandleMapper.getUserByPhone(phone);
        if(baseUserVO == null){
            logger.error("找不到该用户，手机号码：" + phone);
            throw new UsernameNotFoundException("找不到该用户，手机号码：" + phone);
        }
        return baseUserVO;
    }
}
