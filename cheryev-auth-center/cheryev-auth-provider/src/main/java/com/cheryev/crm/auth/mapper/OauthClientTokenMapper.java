package com.cheryev.crm.auth.mapper;

import com.cheryev.crm.auth.model.OauthClientToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OauthClientTokenMapper {
    int deleteByPrimaryKey(String authenticationId);

    int insert(OauthClientToken record);

    int insertSelective(OauthClientToken record);

    OauthClientToken selectByPrimaryKey(String authenticationId);

    int updateByPrimaryKeySelective(OauthClientToken record);

    int updateByPrimaryKeyWithBLOBs(OauthClientToken record);

    int updateByPrimaryKey(OauthClientToken record);
}
