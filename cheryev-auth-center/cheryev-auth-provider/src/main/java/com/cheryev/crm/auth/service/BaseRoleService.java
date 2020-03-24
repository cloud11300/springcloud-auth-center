package com.cheryev.crm.auth.service;

import com.cheryev.crm.auth.mapper.BaseModuleResourcesHandleMapper;
import com.cheryev.crm.auth.mapper.BaseRoleHandleMapper;
import com.cheryev.crm.auth.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: yueyun.pan
 * @Date: 2020/3/24 10:31
 * @Description:
 */
@Service
public class BaseRoleService {

    @Autowired
    private BaseRoleHandleMapper baseRoleHandleMapper;
    @Autowired
    private BaseModuleResourcesHandleMapper baseModuleResourcesHandleMapper;


    public List<BaseRoleVO> selectRoleByRoleName(String roleName){
        List<BaseRoleVO> roles = baseRoleHandleMapper.selectRoleByRoleName(roleName);
        roles.forEach(role->{
            List<BaseModuleResourcesVO> resourcesList = baseModuleResourcesHandleMapper.selectResourcesByRoleId(role.getRoleId());
            resourcesList.forEach(resources->{
                if (Constant.MODULE_REPSPIRCES_TYPE_MENU.equals(resources.getModuleType())){//菜单
                    BaseModuleMenuVO baseModuleMenu = baseModuleResourcesHandleMapper.selectMenuByMenuId(resources.getModuleId());
                    resources.setModuleMenu(baseModuleMenu);
                }else if (Constant.MODULE_REPSPIRCES_TYPE_OPERATION.equals(resources.getModuleType())){//操作权限
                    BaseModuleOperationVO baseModuleOperation = baseModuleResourcesHandleMapper.selectOperationByOperationId(resources.getModuleId());
                    resources.setModuleOperation(baseModuleOperation);
                }else if (Constant.MODULE_REPSPIRCES_TYPE_API.equals(resources.getModuleType())){//接口API
                    BaseModuleApisVO baseModuleApis = baseModuleResourcesHandleMapper.selectApisByApiId(resources.getModuleId());
                    resources.setModuleApi(baseModuleApis);
                }
            });
            role.setModules(resourcesList);
        });
        return roles;
    }

}
