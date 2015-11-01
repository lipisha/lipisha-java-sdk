package com.lipisha.sdk.response;

import java.util.Map;

/**
 * Response received after sending SMS
 */
public class SMSReport extends BaseResponse {

    private static final String RECIPIENT_KEY = "recipient";
    private static final String COST_KEY = "cost";
    private static final String MESSAGE_KEY = "message";

    public SMSReport(StatusResponse statusResponse, Map<String, String> contentResponse) {
        super(statusResponse, contentResponse);
    }


    public String getMessage() {
        return getContentResponse().get(MESSAGE_KEY);
    }

    public String getRecipient() {
        return getContentResponse().get(RECIPIENT_KEY);
    }

    public String getCost() {
        return getContentResponse().get(COST_KEY);
    }

}
