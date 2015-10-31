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

/**
 * Common StatusResponse object
 */
public class StatusResponse {

    @SerializedName("status")
    private String status;
    @SerializedName("status_code")
    private int statusCode;
    @SerializedName("status_description")
    private String statusDescription;

    public StatusResponse(String status, int statusCode, String statusDescription) {
        this.status = status;
        this.statusCode = statusCode;
        this.statusDescription = statusDescription;
    }

    public String getStatus() {
        return status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusDescription() {
        return statusDescription;
    }

}
