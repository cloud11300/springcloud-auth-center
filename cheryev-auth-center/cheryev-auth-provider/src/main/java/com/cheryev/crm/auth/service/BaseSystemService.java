package com.cheryev.crm.auth.service;

import com.cheryev.crm.auth.mapper.BaseSystemHandleMapper;
import com.cheryev.crm.auth.mapper.BaseUserHandleMapper;
import com.cheryev.crm.auth.model.BaseSystem;
import com.cheryev.crm.auth.pojo.BaseSystemVO;
import com.cheryev.crm.auth.pojo.BaseUserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: yueyun.pan
 * @Date: 2020/3/13 11:05
 * @Description:
 */
@Service
public class BaseSystemService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected BaseUserHandleMapper baseUserHandleMapper;
    @Autowired
    protected BaseSystemHandleMapper baseSystemHandleMapper;

    public List<BaseSystemVO> getAllSystem(String userName){
        BaseUserVO baseUserVO = baseUserHandleMapper.selectUserByUserName(userName);
        if (baseUserVO==null){
            logger.error("BaseSystemService>>系统用户查不到，用户名：" + userName);
            throw new UsernameNotFoundException("系统用户查不到，用户名：" + userName);
        }
        List<BaseSystemVO> baseSystemList = baseSystemHandleMapper.selectSystemByUserId(baseUserVO.getUserId());
        if(baseSystemList == null && baseSystemList.size()<=0){
            logger.error("该用户没有分配系统，用户名：" + userName);
            throw new UsernameNotFoundException("该用户没有分配系统，用户名：" + userName);
        }
        return baseSystemList;
    }

}
