/*
 * Copyright 2015 Lipisha Consortium Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lipisha.sdk.response;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Returns balance response from Lipisha.
 */
public class AccountBalance {

    private static final String BALANCE_KEY = "balance";
    private static final String CURRENCY_KEY = "currency";

    @SerializedName("status")
    private StatusResponse statusResponse;
    @SerializedName("content")
    private Map<String, String> contentResponse;

    public AccountBalance(StatusResponse statusResponse, Map<String, String> contentResponse) {
        this.statusResponse = statusResponse;
        this.contentResponse = contentResponse;
    }

    public StatusResponse getStatusResponse() {
        return statusResponse;
    }

    public Map<String, String> getContentResponse() {
        return contentResponse;
    }

    public String getBalance(){
        return getContentResponse().get("balance");
    }

    public String getCurrency(){
        return getContentResponse().get("currency");
    }
}
