
package com.lipisha.sdk.response;

import com.google.gson.annotations.SerializedName;

public class Status {

    @SerializedName("status_code")
    private String statusCode;
    @SerializedName("status_description")
    private String statusDescription;
    @SerializedName("status")
    private String status;

    /**
     * 
     * @return
     *     The statusCode
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * 
     * @param statusCode
     *     The status_code
     */
    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * 
     * @return
     *     The statusDescription
     */
    public String getStatusDescription() {
        return statusDescription;
    }

    /**
     * 
     * @param statusDescription
     *     The status_description
     */
    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
