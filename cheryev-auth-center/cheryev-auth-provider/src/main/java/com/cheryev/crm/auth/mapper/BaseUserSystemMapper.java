package com.cheryev.crm.auth.mapper;

import com.cheryev.crm.auth.model.BaseUserSystem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseUserSystemMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseUserSystem record);

    int insertSelective(BaseUserSystem record);

    BaseUserSystem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseUserSystem record);

    int updateByPrimaryKey(BaseUserSystem record);
}
