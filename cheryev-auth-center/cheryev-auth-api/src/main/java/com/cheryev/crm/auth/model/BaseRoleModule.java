package com.cheryev.crm.auth.model;

public class BaseRoleModule {
    private Integer rolePrivId;

    private Integer roleId;

    private Integer moduleId;

    public Integer getRolePrivId() {
        return rolePrivId;
    }

    public void setRolePrivId(Integer rolePrivId) {
        this.rolePrivId = rolePrivId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }
}