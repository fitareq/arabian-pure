package com.youthfireit.arabianpure.model;

public class Login {
    private String phone_number;
    private String password;
    private String loggedinId;

    public String getLoggedinId() {
        return loggedinId;
    }

    public void setLoggedinId(String loggedinId) {
        this.loggedinId = loggedinId;
    }

    public Login(String phone_number, String password) {
        this.phone_number = phone_number;
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
