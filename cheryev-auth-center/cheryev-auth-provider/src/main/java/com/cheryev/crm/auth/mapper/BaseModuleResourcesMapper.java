package com.cheryev.crm.auth.mapper;

import com.cheryev.crm.auth.model.BaseModuleResources;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseModuleResourcesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseModuleResources record);

    int insertSelective(BaseModuleResources record);

    BaseModuleResources selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseModuleResources record);

    int updateByPrimaryKey(BaseModuleResources record);
}
