package com.cheryev.crm.auth.mapper;

import com.cheryev.crm.auth.model.BaseModuleApis;
import com.cheryev.crm.auth.pojo.BaseModuleApisVO;
import com.cheryev.crm.auth.pojo.BaseModuleMenuVO;
import com.cheryev.crm.auth.pojo.BaseModuleOperationVO;
import com.cheryev.crm.auth.pojo.BaseModuleResourcesVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BaseModuleResourcesHandleMapper {

    List<BaseModuleResourcesVO> selectResourcesByUserId(Integer userId);

    List<BaseModuleResourcesVO> selectResourcesByRoleId(Integer roleId);

    BaseModuleMenuVO selectMenuByMenuId(Integer moduleId);

    BaseModuleOperationVO selectOperationByOperationId(Integer operationId);

    BaseModuleApisVO selectApisByApiId(Integer apiId);
}
