package com.cheryev.crm.auth.mapper;

import com.cheryev.crm.auth.model.BaseUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(BaseUser record);

    int insertSelective(BaseUser record);

    BaseUser selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(BaseUser record);

    int updateByPrimaryKey(BaseUser record);
}
