package com.cheryev.crm.auth.model;

import java.io.Serializable;

public class BaseModuleMenu implements Serializable {
    private static final long serialVersionUID = -6573805546937209555L;
    private Integer id;

    private String name;

    private String brandId;

    private String textLocal;

    private Integer parentId;

    private Byte status;

    private Integer position;

    private String url;

    private Integer sort;

    private String authUrl;

    private Integer isOld;

    private String iconClass;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId == null ? null : brandId.trim();
    }

    public String getTextLocal() {
        return textLocal;
    }

    public void setTextLocal(String textLocal) {
        this.textLocal = textLocal == null ? null : textLocal.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getAuthUrl() {
        return authUrl;
    }

    public void setAuthUrl(String authUrl) {
        this.authUrl = authUrl == null ? null : authUrl.trim();
    }

    public Integer getIsOld() {
        return isOld;
    }

    public void setIsOld(Integer isOld) {
        this.isOld = isOld;
    }

    public String getIconClass() {
        return iconClass;
    }

    public void setIconClass(String iconClass) {
        this.iconClass = iconClass == null ? null : iconClass.trim();
    }
}
