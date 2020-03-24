package com.cheryev.crm.auth.mapper;

import com.cheryev.crm.auth.model.BaseUserRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseUserRoleMapper {
    int deleteByPrimaryKey(Integer userRoleId);

    int insert(BaseUserRole record);

    int insertSelective(BaseUserRole record);

    BaseUserRole selectByPrimaryKey(Integer userRoleId);

    int updateByPrimaryKeySelective(BaseUserRole record);

    int updateByPrimaryKey(BaseUserRole record);
}
