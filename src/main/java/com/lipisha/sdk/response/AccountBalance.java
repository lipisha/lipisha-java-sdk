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

import java.util.Map;

/**
 * Returns balance response from Lipisha.
 */
public class AccountBalance extends BaseResponse {

    private static final String BALANCE_KEY = "balance";
    private static final String CURRENCY_KEY = "currency";

    public AccountBalance(StatusResponse statusResponse, Map<String, String> contentResponse) {
        super(statusResponse, contentResponse);
    }

    public String getBalance(){
        return getContentResponse().get("balance");
    }

    public String getCurrency(){
        return getContentResponse().get("currency");
    }
}
