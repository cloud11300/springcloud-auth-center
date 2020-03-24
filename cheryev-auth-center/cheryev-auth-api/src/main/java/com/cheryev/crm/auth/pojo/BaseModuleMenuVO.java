package com.cheryev.crm.auth.pojo;

import com.cheryev.crm.auth.model.BaseModuleMenu;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: yueyun.pan
 * @Date: 2020/3/12 14:54
 * @Description:
 */
public class BaseModuleMenuVO extends BaseModuleMenu{

    private List<BaseModuleMenuVO> subModules;

    public List<BaseModuleMenuVO> getSubModules() {
        return subModules;
    }

    public void setSubModules(List<BaseModuleMenuVO> subModules) {
        this.subModules = subModules;
    }
}
