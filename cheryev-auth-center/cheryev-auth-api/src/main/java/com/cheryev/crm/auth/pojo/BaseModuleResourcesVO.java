package com.cheryev.crm.auth.pojo;

import com.cheryev.crm.auth.model.BaseModuleResources;

import java.io.Serializable;

/**
 * @Auther: yueyun.pan
 * @Date: 2020/3/12 15:02
 * @Description:
 */
public class BaseModuleResourcesVO extends BaseModuleResources{

    private BaseModuleMenuVO moduleMenu;
    private BaseModuleOperationVO moduleOperation;
    private BaseModuleApisVO moduleApi;

    private String projectName;

    public BaseModuleMenuVO getModuleMenu() {
        return moduleMenu;
    }

    public void setModuleMenu(BaseModuleMenuVO moduleMenu) {
        this.moduleMenu = moduleMenu;
    }

    public BaseModuleOperationVO getModuleOperation() {
        return moduleOperation;
    }

    public void setModuleOperation(BaseModuleOperationVO moduleOperation) {
        this.moduleOperation = moduleOperation;
    }

    public BaseModuleApisVO getModuleApi() {
        return moduleApi;
    }

    public void setModuleApi(BaseModuleApisVO moduleApi) {
        this.moduleApi = moduleApi;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

}
