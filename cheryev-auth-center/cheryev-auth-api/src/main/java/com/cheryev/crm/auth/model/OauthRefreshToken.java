package com.cheryev.crm.auth.model;

import java.util.Date;

public class OauthRefreshToken {
    private String tokenId;

    private Date createTime;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId == null ? null : tokenId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}