package com.lipisha.sdk.response;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class BaseResponse extends BaseStatusResponse {

    @SerializedName("content")
    private Map<String, String> contentResponse;


    public BaseResponse(StatusResponse statusResponse, Map<String, String> contentResponse) {
        super(statusResponse);
        this.contentResponse = contentResponse;
    }

    public Map<String, String> getContentResponse() {
        return contentResponse;
    }

}
