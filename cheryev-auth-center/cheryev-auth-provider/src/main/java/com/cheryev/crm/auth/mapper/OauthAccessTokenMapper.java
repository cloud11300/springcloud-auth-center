package com.cheryev.crm.auth.mapper;

import com.cheryev.crm.auth.model.OauthAccessToken;
import com.cheryev.crm.auth.model.OauthAccessTokenWithBLOBs;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OauthAccessTokenMapper {
    int deleteByPrimaryKey(String authenticationId);

    int insert(OauthAccessTokenWithBLOBs record);

    int insertSelective(OauthAccessTokenWithBLOBs record);

    OauthAccessTokenWithBLOBs selectByPrimaryKey(String authenticationId);

    int updateByPrimaryKeySelective(OauthAccessTokenWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(OauthAccessTokenWithBLOBs record);

    int updateByPrimaryKey(OauthAccessToken record);
}
