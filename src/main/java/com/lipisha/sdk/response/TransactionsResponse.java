package com.lipisha.sdk.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Response from acknowledging a transaction
 */
public class TransactionsResponse extends BaseStatusResponse {

    @SerializedName("content")
    private List<Transaction> transactions;

    public TransactionsResponse(StatusResponse statusResponse, List<Transaction> transactions) {
        super(statusResponse);
        this.transactions = transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
