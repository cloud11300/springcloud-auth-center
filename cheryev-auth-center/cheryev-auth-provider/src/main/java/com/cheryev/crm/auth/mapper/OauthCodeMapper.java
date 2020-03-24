package com.cheryev.crm.auth.mapper;

import com.cheryev.crm.auth.model.OauthCode;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OauthCodeMapper {
    int insert(OauthCode record);

    int insertSelective(OauthCode record);
}
