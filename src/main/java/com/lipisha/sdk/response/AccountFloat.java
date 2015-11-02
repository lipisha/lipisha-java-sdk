package com.lipisha.sdk.response;

import java.util.Map;

/**
 * Returns float in account
 */
public class AccountFloat extends  BaseResponse {

    private static final String FLOAT_KEY = "float";
    private static final String ACCCOUNT_NUMBER_KEY = "account_number";
    private static final String CURRENCY_KEY = "currency";


    public AccountFloat(StatusResponse statusResponse, Map<String, String> contentResponse) {
        super(statusResponse, contentResponse);
    }

    public String getFloat(){
        return getContentResponse().get(FLOAT_KEY);
    }

    public String getAccountNumber(){
        return getContentResponse().get(ACCCOUNT_NUMBER_KEY);
    }

    public String getCurrency(){
        return getContentResponse().get(CURRENCY_KEY);
    }
}
