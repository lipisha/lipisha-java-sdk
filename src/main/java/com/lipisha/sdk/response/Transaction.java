package com.lipisha.sdk.response;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Transaction Record. Received from transaction related api calls.
 */
public class Transaction {

    @SerializedName("transaction")
    private String transactionId;
    @SerializedName("transaction_type")
    private String transactionType;
    @SerializedName("transaction_method")
    private String transactionMethod;
    @SerializedName("transaction_date")
    private Date transactionDate;
    @SerializedName("transaction_account_name")
    private String transactionAccountName;
    @SerializedName("transaction_account_number")
    private String transactionAccountNumber;
    @SerializedName("transaction_reference")
    private String transactionReference;
    @SerializedName("transaction_amount")
    private Float transactionAmount;
    @SerializedName("transaction_status")
    private String transactionStatus;
    @SerializedName("transaction_name")
    private String transactionName;
    @SerializedName("transaction_mobile_number")
    private String transactionMobileNumber;
    @SerializedName("transaction_email")
    private String transactionEmail;

    public Transaction(String transactionId,
                       String transactionType,
                       String transactionMethod,
                       Date transactionDate,
                       String transactionAccountName,
                       String transactionAccountNumber,
                       String transactionReference,
                       Float transactionAmount,
                       String transactionStatus,
                       String transactionName,
                       String transactionMobileNumber,
                       String transactionEmail) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.transactionMethod = transactionMethod;
        this.transactionDate = transactionDate;
        this.transactionAccountName = transactionAccountName;
        this.transactionAccountNumber = transactionAccountNumber;
        this.transactionReference = transactionReference;
        this.transactionAmount = transactionAmount;
        this.transactionStatus = transactionStatus;
        this.transactionName = transactionName;
        this.transactionMobileNumber = transactionMobileNumber;
        this.transactionEmail = transactionEmail;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public String getTransactionMethod() {
        return transactionMethod;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionAccountName() {
        return transactionAccountName;
    }

    public String getTransactionAccountNumber() {
        return transactionAccountNumber;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public Float getTransactionAmount() {
        return transactionAmount;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public String getTransactionMobileNumber() {
        return transactionMobileNumber;
    }

    public String getTransactionEmail() {
        return transactionEmail;
    }
}
