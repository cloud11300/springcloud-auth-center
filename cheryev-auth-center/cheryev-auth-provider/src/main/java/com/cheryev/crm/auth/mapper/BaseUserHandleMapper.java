package com.cheryev.crm.auth.mapper;

import com.cheryev.crm.auth.model.OauthClientDetails;
import com.cheryev.crm.auth.pojo.BaseUserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BaseUserHandleMapper {

    BaseUserVO selectUserByUserName(String userName);

    BaseUserVO getUserByPhone(String phone);

    List<OauthClientDetails> getAllClient();
}
