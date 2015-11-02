package com.lipisha.sdk.response;

import java.util.Map;

/**
 * Payout response
 */
public class Payout extends BaseResponse {

    private static final String MOBILE_NUMBER_KEY = "mobile_number";
    private static final String AMOUNT_KEY = "amount";
    private static final String REFERENCE_KEY = "reference";
    private static final String CUSTOMER_NAME_KEY = "customer_name";

    public Payout(StatusResponse statusResponse, Map<String, String> contentResponse) {
        super(statusResponse, contentResponse);
    }

    public String getMobileNumber() {
        return getContentResponse().get(MOBILE_NUMBER_KEY);
    }

    public String getAmount() {
        return getContentResponse().get(AMOUNT_KEY);
    }

    public String getReference() {
        return getContentResponse().get(REFERENCE_KEY);
    }

    public String getCustomerName() {
        return getContentResponse().get(CUSTOMER_NAME_KEY);
    }

}
