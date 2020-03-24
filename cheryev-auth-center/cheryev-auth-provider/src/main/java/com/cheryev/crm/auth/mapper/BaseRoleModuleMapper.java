package com.cheryev.crm.auth.mapper;

import com.cheryev.crm.auth.model.BaseRoleModule;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseRoleModuleMapper {
    int deleteByPrimaryKey(Integer rolePrivId);

    int insert(BaseRoleModule record);

    int insertSelective(BaseRoleModule record);

    BaseRoleModule selectByPrimaryKey(Integer rolePrivId);

    int updateByPrimaryKeySelective(BaseRoleModule record);

    int updateByPrimaryKey(BaseRoleModule record);
}
