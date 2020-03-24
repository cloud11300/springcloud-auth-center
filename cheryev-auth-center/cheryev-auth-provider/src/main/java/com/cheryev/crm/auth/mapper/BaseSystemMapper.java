package com.cheryev.crm.auth.mapper;

import com.cheryev.crm.auth.model.BaseSystem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseSystemMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseSystem record);

    int insertSelective(BaseSystem record);

    BaseSystem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseSystem record);

    int updateByPrimaryKey(BaseSystem record);
}
