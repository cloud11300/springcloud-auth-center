package com.cheryev.crm.auth.mapper;

import com.cheryev.crm.auth.pojo.BaseSystemVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BaseSystemHandleMapper {

    List<BaseSystemVO> selectSystemByUserId(Integer userId);

}
