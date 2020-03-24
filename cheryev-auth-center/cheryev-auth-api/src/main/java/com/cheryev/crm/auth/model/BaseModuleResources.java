package com.cheryev.crm.auth.model;

import java.io.Serializable;
import java.util.Date;

public class BaseModuleResources implements Serializable {
    private static final long serialVersionUID = -7614579036586642301L;
    private Integer id;

    private Integer moduleId;

    private String moduleCode;

    private String moduleType;

    private Integer isOperating;

    private Integer sort;

    private String systemId;

    private Integer active;

    private Date createDate;

    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getModuleId() {
        return moduleId;
    }

    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }

    public String getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(String moduleCode) {
        this.moduleCode = moduleCode == null ? null : moduleCode.trim();
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType == null ? null : moduleType.trim();
    }

    public Integer getIsOperating() {
        return isOperating;
    }

    public void setIsOperating(Integer isOperating) {
        this.isOperating = isOperating;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
