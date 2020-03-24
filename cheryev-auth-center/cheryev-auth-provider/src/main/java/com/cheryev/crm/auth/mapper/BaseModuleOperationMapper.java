package com.cheryev.crm.auth.mapper;

import com.cheryev.crm.auth.model.BaseModuleOperation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseModuleOperationMapper {
    int deleteByPrimaryKey(Integer operId);

    int insert(BaseModuleOperation record);

    int insertSelective(BaseModuleOperation record);

    BaseModuleOperation selectByPrimaryKey(Integer operId);

    int updateByPrimaryKeySelective(BaseModuleOperation record);

    int updateByPrimaryKey(BaseModuleOperation record);
}
