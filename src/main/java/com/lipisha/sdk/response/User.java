package com.lipisha.sdk.response;

import com.google.gson.annotations.SerializedName;

/**
 * User record
 */
public class User {

    @SerializedName("user_name")
    private String userName;
    @SerializedName("email")
    private String email;

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }
}
