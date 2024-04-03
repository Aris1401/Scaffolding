package com.scaffolding.test.auth;

public class LoginRequest {
    String loginName;
    String loginPass;

    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getLoginPass() {
        return loginPass;
    }
    public void setLoginPass(String loginPass) {
        this.loginPass = loginPass;
    }
}
