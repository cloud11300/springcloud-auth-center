package com.cheryev.crm.auth.model;

public class OauthRefreshTokenWithBLOBs extends OauthRefreshToken {
    private byte[] token;

    private byte[] authentication;

    public byte[] getToken() {
        return token;
    }

    public void setToken(byte[] token) {
        this.token = token;
    }

    public byte[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(byte[] authentication) {
        this.authentication = authentication;
    }
}