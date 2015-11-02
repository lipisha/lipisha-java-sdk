package com.lipisha.sdk.response;

import com.google.gson.annotations.SerializedName;

/**
 * Response from acknowledging a transaction
 */
public class TransactionResponse extends BaseStatusResponse {

    @SerializedName("content")
    private Transaction transaction;

    public TransactionResponse(StatusResponse statusResponse, Transaction transaction) {
        super(statusResponse);
        this.transaction = transaction;
    }

    public Transaction getTransaction() {
        return transaction;
    }
}
