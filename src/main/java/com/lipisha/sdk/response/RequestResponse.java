package com.lipisha.sdk.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by M on 9/15/2016.
 *
 * Response from requesting money
 *
 */
public class RequestResponse {
    @SerializedName("status")
    private Status status;
    @SerializedName("content")
    private RequestMoneyContent content;

    /**
     * @return The status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return The content
     */
    public RequestMoneyContent getContent() {
        return content;
    }

    /**
     * @param content The content
     */
    public void setContent(RequestMoneyContent content) {
        this.content = content;
    }
}
