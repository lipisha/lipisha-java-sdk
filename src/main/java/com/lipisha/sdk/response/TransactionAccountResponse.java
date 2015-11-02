package com.lipisha.sdk.response;

import com.google.gson.annotations.SerializedName;

/**
 * Response received by transactionAccount APIs
 */
public class TransactionAccountResponse extends BaseStatusResponse {

    @SerializedName("content")
    private TransactionAccount transactionAccount;

    public TransactionAccountResponse(StatusResponse statusResponse, TransactionAccount transactionAccount) {
        super(statusResponse);
        this.transactionAccount = transactionAccount;
    }

    public TransactionAccount getTransactionAccount() {
        return transactionAccount;
    }
}
