package com.cheryev.crm.auth.pojo;

import com.cheryev.crm.auth.model.*;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: yueyun.pan
 * @Date: 2020/3/12 14:51
 * @Description:
 */
public class BaseRoleVO extends BaseRole{

    private List<BaseModuleResourcesVO> modules;

    public List<BaseModuleResourcesVO> getModules() {
        return modules;
    }

    public void setModules(List<BaseModuleResourcesVO> modules) {
        this.modules = modules;
    }
}
