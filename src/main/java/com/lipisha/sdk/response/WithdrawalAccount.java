package com.lipisha.sdk.response;

import com.google.gson.annotations.SerializedName;

/**
 * TransactionAccount object returned by account API endpoints.
 */
public class WithdrawalAccount extends TransactionAccount {

    @SerializedName("transaction_account_bank_name")
    private String bankName;
    @SerializedName("transaction_account_bank_branch")
    private String bankBranch;
    @SerializedName("transaction_account_bank_address")
    private String bankAddress;
    @SerializedName("transaction_account_swift_code")
    private String swiftCode;

    public WithdrawalAccount(int accountType,
                             String accountNumber,
                             String accountName,
                             String accountManager,
                             String bankName,
                             String bankBranch,
                             String bankAddress,
                             String swiftCode) {
        super(accountType, accountNumber, accountName, accountManager);
        this.bankName = bankName;
        this.bankBranch = bankBranch;
        this.bankAddress = bankAddress;
        this.swiftCode = swiftCode;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public String getSwiftCode() {
        return swiftCode;
    }
}
