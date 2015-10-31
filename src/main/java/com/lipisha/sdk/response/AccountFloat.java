package com.lipisha.sdk.response;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Returns float in account
 */
public class AccountFloat {

    private static final String FLOAT_KEY = "float";
    private static final String ACCCOUNT_NUMBER_KEY = "account_number";
    private static final String CURRENCY_KEY = "currency";

    @SerializedName("status")
    private StatusResponse statusResponse;
    @SerializedName("content")
    private Map<String, String> contentResponse;

    public AccountFloat(StatusResponse statusResponse, Map<String, String> contentResponse) {
        this.statusResponse = statusResponse;
        this.contentResponse = contentResponse;
    }

    public StatusResponse getStatusResponse() {
        return statusResponse;
    }

    public Map<String, String> getContentResponse() {
        return contentResponse;
    }

    public String getFloat(){
        return contentResponse.get(FLOAT_KEY);
    }

    public String getAccountNumber(){
        return contentResponse.get(ACCCOUNT_NUMBER_KEY);
    }

    public String getCurrency(){
        return contentResponse.get(CURRENCY_KEY);
    }
}
