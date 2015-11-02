package com.lipisha.sdk.response;

import com.google.gson.annotations.SerializedName;

/**
 * Response from user API operations.
 */
public class UserResponse extends BaseStatusResponse {

    @SerializedName("content")
    private User user;

    public UserResponse(StatusResponse statusResponse, User user) {
        super(statusResponse);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
