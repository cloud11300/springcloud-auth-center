package com.cheryev.crm.auth.mapper;

import com.cheryev.crm.auth.model.BaseModuleApis;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseModuleApisMapper {
    int deleteByPrimaryKey(Integer apiId);

    int insert(BaseModuleApis record);

    int insertSelective(BaseModuleApis record);

    BaseModuleApis selectByPrimaryKey(Integer apiId);

    int updateByPrimaryKeySelective(BaseModuleApis record);

    int updateByPrimaryKey(BaseModuleApis record);
}
