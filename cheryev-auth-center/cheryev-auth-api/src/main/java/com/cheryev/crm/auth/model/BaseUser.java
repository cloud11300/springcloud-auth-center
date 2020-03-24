package com.cheryev.crm.auth.model;

import java.util.Date;

public class BaseUser {

    private Integer userId;

    private String passWord;

    private String userName;

    private String nickName;

    private String empno;

    private String depart;

    private String mobile;

    private String email;

    private String isAcrossBrand;

    private Byte status;

    private String salt;

    private Integer userSource;

    private Byte isMonitor;

    private Byte cccAccount;

    private Integer isWebChat;

    private Byte isCreate;

    private Date lastLoginTime;

    private Date modPwdTime;

    private String headImg;

    private String bg;

    private Date lastSigninTime;

    private Integer signinNum;

    private Integer continuSignin;

    private String uuid;

    private Date modifyPwdApplyTime;

    private String deviceType;

    private String deviceToken;

    private String dealerId;

    private Date accessPreTime;

    private Date accessSufTime;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    private Integer userType;

    private String ssoUserCert;

    private String wxUserId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getEmpno() {
        return empno;
    }

    public void setEmpno(String empno) {
        this.empno = empno == null ? null : empno.trim();
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart == null ? null : depart.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIsAcrossBrand() {
        return isAcrossBrand;
    }

    public void setIsAcrossBrand(String isAcrossBrand) {
        this.isAcrossBrand = isAcrossBrand == null ? null : isAcrossBrand.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Integer getUserSource() {
        return userSource;
    }

    public void setUserSource(Integer userSource) {
        this.userSource = userSource;
    }

    public Byte getIsMonitor() {
        return isMonitor;
    }

    public void setIsMonitor(Byte isMonitor) {
        this.isMonitor = isMonitor;
    }

    public Byte getCccAccount() {
        return cccAccount;
    }

    public void setCccAccount(Byte cccAccount) {
        this.cccAccount = cccAccount;
    }

    public Integer getIsWebChat() {
        return isWebChat;
    }

    public void setIsWebChat(Integer isWebChat) {
        this.isWebChat = isWebChat;
    }

    public Byte getIsCreate() {
        return isCreate;
    }

    public void setIsCreate(Byte isCreate) {
        this.isCreate = isCreate;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Date getModPwdTime() {
        return modPwdTime;
    }

    public void setModPwdTime(Date modPwdTime) {
        this.modPwdTime = modPwdTime;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg == null ? null : headImg.trim();
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg == null ? null : bg.trim();
    }

    public Date getLastSigninTime() {
        return lastSigninTime;
    }

    public void setLastSigninTime(Date lastSigninTime) {
        this.lastSigninTime = lastSigninTime;
    }

    public Integer getSigninNum() {
        return signinNum;
    }

    public void setSigninNum(Integer signinNum) {
        this.signinNum = signinNum;
    }

    public Integer getContinuSignin() {
        return continuSignin;
    }

    public void setContinuSignin(Integer continuSignin) {
        this.continuSignin = continuSignin;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public Date getModifyPwdApplyTime() {
        return modifyPwdApplyTime;
    }

    public void setModifyPwdApplyTime(Date modifyPwdApplyTime) {
        this.modifyPwdApplyTime = modifyPwdApplyTime;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken == null ? null : deviceToken.trim();
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId == null ? null : dealerId.trim();
    }

    public Date getAccessPreTime() {
        return accessPreTime;
    }

    public void setAccessPreTime(Date accessPreTime) {
        this.accessPreTime = accessPreTime;
    }

    public Date getAccessSufTime() {
        return accessSufTime;
    }

    public void setAccessSufTime(Date accessSufTime) {
        this.accessSufTime = accessSufTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getSsoUserCert() {
        return ssoUserCert;
    }

    public void setSsoUserCert(String ssoUserCert) {
        this.ssoUserCert = ssoUserCert == null ? null : ssoUserCert.trim();
    }

    public String getWxUserId() {
        return wxUserId;
    }

    public void setWxUserId(String wxUserId) {
        this.wxUserId = wxUserId == null ? null : wxUserId.trim();
    }
}
