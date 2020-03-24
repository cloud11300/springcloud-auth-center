package com.cheryev.crm.auth.service;

import com.cheryev.crm.auth.mapper.BaseUserHandleMapper;
import com.cheryev.crm.auth.pojo.BaseUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class UsernameUserDetailService extends BaseUserDetailService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected BaseUserHandleMapper baseUserHandleMapper;

    @Override
    protected BaseUserVO getUser(String username) {
        // 根据用户名查询用户
        BaseUserVO baseUserVO = baseUserHandleMapper.selectUserByUserName(username);
        if(baseUserVO == null){
            logger.error("找不到该用户，用户名：" + username);
            throw new UsernameNotFoundException("找不到该用户，用户名：" + username);
        }
        return baseUserVO;
    }

    public BaseUserVO getUserByName(String username) {
        // 根据用户名查询用户
        BaseUserVO baseUserVO = baseUserHandleMapper.selectUserByUserName(username);
        if (baseUserVO!=null){
            baseUserVO.setPassWord(null);
        }
        if(baseUserVO == null){
            logger.error("找不到该用户，用户名：" + username);
            throw new UsernameNotFoundException("找不到该用户，用户名：" + username);
        }
        return baseUserVO;
    }
}
