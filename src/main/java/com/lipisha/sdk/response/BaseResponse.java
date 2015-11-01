package com.lipisha.sdk.response;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class BaseResponse {

    @SerializedName("status")
    private StatusResponse statusResponse;
    @SerializedName("content")
    private Map<String, String> contentResponse;


    public BaseResponse(StatusResponse statusResponse, Map<String, String> contentResponse) {
        this.statusResponse = statusResponse;
        this.contentResponse = contentResponse;
    }

    public StatusResponse getStatusResponse() {
        return statusResponse;
    }

    public Map<String, String> getContentResponse() {
        return contentResponse;
    }

    public String getStatus() {
        return getStatusResponse().getStatus();
    }

    public String getStatusDescription() {
        return getStatusResponse().getStatusDescription();
    }

    private int getStatusCode() {
        return getStatusResponse().getStatusCode();
    }

    public boolean isSuccessful() {
        return getStatusResponse().isSuccessful();
    }
}
