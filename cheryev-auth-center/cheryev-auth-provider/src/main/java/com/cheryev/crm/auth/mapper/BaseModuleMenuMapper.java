package com.cheryev.crm.auth.mapper;

import com.cheryev.crm.auth.model.BaseModuleMenu;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseModuleMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BaseModuleMenu record);

    int insertSelective(BaseModuleMenu record);

    BaseModuleMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BaseModuleMenu record);

    int updateByPrimaryKey(BaseModuleMenu record);
}
