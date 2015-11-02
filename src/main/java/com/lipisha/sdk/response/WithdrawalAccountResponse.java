package com.lipisha.sdk.response;

import com.google.gson.annotations.SerializedName;

/**
 * Response received by withdrawal account APIs
 */
public class WithdrawalAccountResponse extends BaseStatusResponse {

    @SerializedName("content")
    private WithdrawalAccount withdrawalAccount;

    public WithdrawalAccountResponse(StatusResponse statusResponse, WithdrawalAccount withdrawalAccount) {
        super(statusResponse);
        this.withdrawalAccount = withdrawalAccount;
    }

    public WithdrawalAccount getWithdrawalAccount() {
        return withdrawalAccount;
    }
}
