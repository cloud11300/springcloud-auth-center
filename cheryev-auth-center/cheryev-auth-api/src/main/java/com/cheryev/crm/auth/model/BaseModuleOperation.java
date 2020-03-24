package com.cheryev.crm.auth.model;

import java.io.Serializable;

public class BaseModuleOperation implements Serializable {
    private static final long serialVersionUID = 8912849614993805169L;
    private Integer operId;

    private String operName;

    private String brandId;

    private String operDesc;

    private Integer operMenu;

    private String operCode;

    private Byte status;

    private String operGroupCode;

    private String operGroupName;

    private String parentGroupCode;

    public Integer getOperId() {
        return operId;
    }

    public void setOperId(Integer operId) {
        this.operId = operId;
    }

    public String getOperName() {
        return operName;
    }

    public void setOperName(String operName) {
        this.operName = operName == null ? null : operName.trim();
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId == null ? null : brandId.trim();
    }

    public String getOperDesc() {
        return operDesc;
    }

    public void setOperDesc(String operDesc) {
        this.operDesc = operDesc == null ? null : operDesc.trim();
    }

    public Integer getOperMenu() {
        return operMenu;
    }

    public void setOperMenu(Integer operMenu) {
        this.operMenu = operMenu;
    }

    public String getOperCode() {
        return operCode;
    }

    public void setOperCode(String operCode) {
        this.operCode = operCode == null ? null : operCode.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getOperGroupCode() {
        return operGroupCode;
    }

    public void setOperGroupCode(String operGroupCode) {
        this.operGroupCode = operGroupCode == null ? null : operGroupCode.trim();
    }

    public String getOperGroupName() {
        return operGroupName;
    }

    public void setOperGroupName(String operGroupName) {
        this.operGroupName = operGroupName == null ? null : operGroupName.trim();
    }

    public String getParentGroupCode() {
        return parentGroupCode;
    }

    public void setParentGroupCode(String parentGroupCode) {
        this.parentGroupCode = parentGroupCode == null ? null : parentGroupCode.trim();
    }
}
