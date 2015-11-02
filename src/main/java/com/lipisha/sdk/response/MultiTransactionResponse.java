package com.lipisha.sdk.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Response from acknowledging a transaction
 */
public class MultiTransactionResponse extends BaseStatusResponse {

    @SerializedName("content")
    private List<Transaction> transactions;

    public MultiTransactionResponse(StatusResponse statusResponse, List<Transaction> transactions) {
        super(statusResponse);
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
