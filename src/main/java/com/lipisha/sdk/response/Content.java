
package com.lipisha.sdk.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Content {

    @SerializedName("account_number")
    @Expose
    private String accountNumber;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("reference")
    @Expose
    private String reference;

    /**
     * 
     * @return
     *     The accountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * 
     * @param accountNumber
     *     The account_number
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    /**
     * 
     * @return
     *     The mobileNumber
     */
    public String getMobileNumber() {
        return mobileNumber;
    }

    /**
     * 
     * @param mobileNumber
     *     The mobile_number
     */
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    /**
     * 
     * @return
     *     The method
     */
    public String getMethod() {
        return method;
    }

    /**
     * 
     * @param method
     *     The method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 
     * @return
     *     The amount
     */
    public String getAmount() {
        return amount;
    }

    /**
     * 
     * @param amount
     *     The amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     * 
     * @return
     *     The reference
     */
    public String getReference() {
        return reference;
    }

    /**
     * 
     * @param reference
     *     The reference
     */
    public void setReference(String reference) {
        this.reference = reference;
    }
}
