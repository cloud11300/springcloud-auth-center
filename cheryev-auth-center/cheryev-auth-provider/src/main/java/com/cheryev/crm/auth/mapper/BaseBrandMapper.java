package com.cheryev.crm.auth.mapper;

import com.cheryev.crm.auth.model.BaseBrand;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseBrandMapper {
    int deleteByPrimaryKey(String id);

    int insert(BaseBrand record);

    int insertSelective(BaseBrand record);

    BaseBrand selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BaseBrand record);

    int updateByPrimaryKey(BaseBrand record);
}
