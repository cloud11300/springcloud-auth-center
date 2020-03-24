package com.cheryev.crm.auth.model;

public class BaseUserSystem {
    private String id;

    private Integer userId;

    private String systemId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSystemId() {
        return systemId;
    }

    public void setSystemId(String systemId) {
        this.systemId = systemId == null ? null : systemId.trim();
    }
}