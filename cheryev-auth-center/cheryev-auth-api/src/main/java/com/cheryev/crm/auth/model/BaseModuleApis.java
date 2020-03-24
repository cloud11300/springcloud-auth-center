package com.cheryev.crm.auth.model;

import java.io.Serializable;

public class BaseModuleApis implements Serializable {
    private static final long serialVersionUID = -164448095073608473L;
    private Integer apiId;

    private String apiName;

    private String brandId;

    private String apiDesc;

    private Integer apiMenu;

    private String apiUrl;

    private Byte status;

    private String apiGroupCode;

    private String apiGroupName;

    private String parentGroupCode;

    public Integer getApiId() {
        return apiId;
    }

    public void setApiId(Integer apiId) {
        this.apiId = apiId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName == null ? null : apiName.trim();
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId == null ? null : brandId.trim();
    }

    public String getApiDesc() {
        return apiDesc;
    }

    public void setApiDesc(String apiDesc) {
        this.apiDesc = apiDesc == null ? null : apiDesc.trim();
    }

    public Integer getApiMenu() {
        return apiMenu;
    }

    public void setApiMenu(Integer apiMenu) {
        this.apiMenu = apiMenu;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl == null ? null : apiUrl.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getApiGroupCode() {
        return apiGroupCode;
    }

    public void setApiGroupCode(String apiGroupCode) {
        this.apiGroupCode = apiGroupCode == null ? null : apiGroupCode.trim();
    }

    public String getApiGroupName() {
        return apiGroupName;
    }

    public void setApiGroupName(String apiGroupName) {
        this.apiGroupName = apiGroupName == null ? null : apiGroupName.trim();
    }

    public String getParentGroupCode() {
        return parentGroupCode;
    }

    public void setParentGroupCode(String parentGroupCode) {
        this.parentGroupCode = parentGroupCode == null ? null : parentGroupCode.trim();
    }
}
