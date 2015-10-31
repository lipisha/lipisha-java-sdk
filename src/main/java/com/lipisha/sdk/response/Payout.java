package com.lipisha.sdk.response;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Payout response
 */
public class Payout {

    private static final String MOBILE_NUMBER_KEY = "mobile_number";
    private static final String AMOUNT_KEY = "amount";
    private static final String REFERENCE_KEY = "reference";
    private static final String CUSTOMER_NAME_KEY = "customer_name";

    @SerializedName("status")
    private StatusResponse statusResponse;
    @SerializedName("content")
    private Map<String, String> contentResponse;

    public Payout(StatusResponse statusResponse, Map<String, String> contentResponse) {
        this.statusResponse = statusResponse;
        this.contentResponse = contentResponse;
    }

    public StatusResponse getStatusResponse() {
        return statusResponse;
    }

    public Map<String, String> getContentResponse() {
        return contentResponse;
    }

    public String getMobileNumber() {
        return contentResponse.get(MOBILE_NUMBER_KEY);
    }

    public String getAmount() {
        return contentResponse.get(AMOUNT_KEY);
    }

    public String getReference() {
        return contentResponse.get(REFERENCE_KEY);
    }

    public String getCustomerName() {
        return contentResponse.get(CUSTOMER_NAME_KEY);
    }

}
