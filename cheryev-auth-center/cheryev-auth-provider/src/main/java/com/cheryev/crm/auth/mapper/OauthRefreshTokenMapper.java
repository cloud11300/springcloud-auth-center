package com.cheryev.crm.auth.mapper;

import com.cheryev.crm.auth.model.OauthRefreshToken;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OauthRefreshTokenMapper {
    int insert(OauthRefreshToken record);

    int insertSelective(OauthRefreshToken record);
}
