package com.cheryev.crm.auth.mapper;

import com.cheryev.crm.auth.model.BaseRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseRoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(BaseRole record);

    int insertSelective(BaseRole record);

    BaseRole selectByPrimaryKey(Integer roleId);

    int updateByPrimaryKeySelective(BaseRole record);

    int updateByPrimaryKey(BaseRole record);
}
