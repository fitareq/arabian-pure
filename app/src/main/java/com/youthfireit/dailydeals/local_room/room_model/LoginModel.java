package com.youthfireit.dailydeals.local_room.room_model;

import com.google.gson.annotations.SerializedName;

public class LoginModel
{
    @SerializedName("phone_number")
    private String userPhone;
    @SerializedName("password")
    private String loginToken;

    public LoginModel(String userPhone, String loginToken) {
        this.userPhone = userPhone;
        this.loginToken = loginToken;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(String loginToken) {
        this.loginToken = loginToken;
    }
}
