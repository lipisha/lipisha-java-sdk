package com.lipisha.sdk.response;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Airtime disbursement response.
 */
public class AirtimeDisbursement {

    private static final String MOBILE_NUMBER_KEY = "mobile_number";
    private static final String AMOUNT_KEY = "amount";
    private static final String REFERENCE_KEY = "reference";
    @SerializedName("status")
    private StatusResponse statusResponse;
    @SerializedName("content")
    private Map<String, String> contentResponse;

    public AirtimeDisbursement(StatusResponse statusResponse, Map<String, String> contentResponse) {
        this.statusResponse = statusResponse;
        this.contentResponse = contentResponse;
    }

    public StatusResponse getStatusResponse() {
        return statusResponse;
    }

    public void setStatusResponse(StatusResponse statusResponse) {
        this.statusResponse = statusResponse;
    }

    public Map<String, String> getContentResponse() {
        return contentResponse;
    }

    public void setContentResponse(Map<String, String> contentResponse) {
        this.contentResponse = contentResponse;
    }

    public String getAmount() {
        return contentResponse.get(AMOUNT_KEY);
    }

    public String getMobileNumber() {
        return contentResponse.get(MOBILE_NUMBER_KEY);
    }

    public  String getReference() {
     return contentResponse.get(REFERENCE_KEY);
    }
}
