package com.cheryev.crm.auth.mapper;

import com.cheryev.crm.auth.pojo.BaseRoleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BaseRoleHandleMapper {

    List<BaseRoleVO> selectRoleByUserId(Integer userId);

    List<BaseRoleVO> selectRoleByRoleName(String roleName);

}
