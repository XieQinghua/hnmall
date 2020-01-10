package com.your.mall.bean;

/**
 * Created by xieqinghua.
 * 创建时间：2016/10/8
 * 类描述：登录接口返回json
 * 修改备注：
 */
public class LoginBean {

    /**
     * returnCode : 0
     * message : 执行成功
     * openId : 06ed640c46d14130b62bede3933da2eb
     * username : null
     * mobileNumber : 15200917596
     * email : null
     * nickname : null
     * authorizationCode : 2962604fc4bb4caabbfdfdddecd27be0
     * accessToken : f60b0ca2d7d840ae906955b2c01d01ac
     * accessExpires : 1475896965
     * refreshToken : 2a75611c4af943538f96cef0bc836246
     * refreshExpires : 1477102965
     * redirectUrl : null
     * registerTag : null
     * loginTag : null
     * gender : null
     * birthday : null
     * imageUrl : http://imgjf.tepin.com/82a4/bff139fa05ac383fa85a523ab3d110a0909539789e4b451b80200e52d1de3873?Expires=1866240000&OSSAccessKeyId=lmCcWB9EZD2YncQm&Signature=UJG5VONn3UwHKj/x3POU/FOJfcc%3D&response-content-disposition=attachment%3B%20filename%3Dhead.png
     * thirdPrimary : null
     */

    private int returnCode;
    private String message;
    private String openId;
    private Object username;
    private String mobileNumber;
    private Object email;
    private Object nickname;
    private String authorizationCode;
    private String accessToken;
    private int accessExpires;
    private String refreshToken;
    private int refreshExpires;
    private Object redirectUrl;
    private Object registerTag;
    private Object loginTag;
    private Object gender;
    private Object birthday;
    private String imageUrl;
    private Object thirdPrimary;

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Object getUsername() {
        return username;
    }

    public void setUsername(Object username) {
        this.username = username;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public Object getNickname() {
        return nickname;
    }

    public void setNickname(Object nickname) {
        this.nickname = nickname;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getAccessExpires() {
        return accessExpires;
    }

    public void setAccessExpires(int accessExpires) {
        this.accessExpires = accessExpires;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getRefreshExpires() {
        return refreshExpires;
    }

    public void setRefreshExpires(int refreshExpires) {
        this.refreshExpires = refreshExpires;
    }

    public Object getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(Object redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public Object getRegisterTag() {
        return registerTag;
    }

    public void setRegisterTag(Object registerTag) {
        this.registerTag = registerTag;
    }

    public Object getLoginTag() {
        return loginTag;
    }

    public void setLoginTag(Object loginTag) {
        this.loginTag = loginTag;
    }

    public Object getGender() {
        return gender;
    }

    public void setGender(Object gender) {
        this.gender = gender;
    }

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(Object birthday) {
        this.birthday = birthday;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Object getThirdPrimary() {
        return thirdPrimary;
    }

    public void setThirdPrimary(Object thirdPrimary) {
        this.thirdPrimary = thirdPrimary;
    }
}
