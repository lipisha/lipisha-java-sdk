package com.lipisha.sdk;

import com.lipisha.sdk.api.LipishaAPI;
import com.lipisha.sdk.api.ServiceGenerator;
import com.lipisha.sdk.response.*;
import com.sun.istack.internal.Nullable;

import java.util.Date;

/**
 * Implements the primary client for access to
 */
public class LipishaClient {

    public static final String SANDBOX_BASE_URL = "http://developer.lipisha.com";
    public static final String PROD_BASE_URL = "https://lipisha.com/payments/accounts";
    private static final String API_VERSION = "1.3.0";
    private static final String API_TYPE_CALLBACK = "Callback";
    private static final String API_TYPE_IPN = "Ipn";

    private String apiKey, apiSignature, apiVersion, apiType;
    private LipishaAPI lipishaAPI;

    /**
     * Sets up a {@Link LipishaClient} response with a {@Link LipishaAPI} service bound to production URL;
     *
     * @param apiKey       Lipisha API Key from your production or sandbox account settings
     * @param apiSignature Lipisha API Signature from production or sandbox settings
     * @param apiVersion   Lipisha API version we're connecting to.
     * @param apiType      Lipisha API type we're connecting to.
     */
    public LipishaClient(String apiKey, String apiSignature, String apiVersion, String apiType, String baseUrl) {
        this.apiKey = apiKey;
        this.apiSignature = apiSignature;
        this.apiVersion = apiVersion;
        this.apiType = apiType;
        this.lipishaAPI = ServiceGenerator.createService(LipishaAPI.class, baseUrl);
    }

    /**
     * Sets up a {@Link LipishaClient} response with default version and com.lipisha.sdk.api type set up.
     *
     * @param apiKey
     * @param apiSignature
     */
    public LipishaClient(String apiKey, String apiSignature, String baseUrl) {
        this.apiKey = apiKey;
        this.apiSignature = apiSignature;
        this.apiVersion = API_VERSION;
        this.apiType = API_TYPE_CALLBACK;
        this.lipishaAPI = ServiceGenerator.createService(LipishaAPI.class, baseUrl);
    }

    /**
     * Gets account balance.
     *
     * @return
     */
    public AccountBalance getBalance() {
        return this.lipishaAPI.getBalance(this.apiKey, this.apiSignature, this.apiVersion, this.apiType);
    }

    /**
     * Get float balance for given account number.
     *
     * @param accountNumber Float account number.
     * @return
     */
    public AccountFloat getFloat(String accountNumber) {
        return this.lipishaAPI.getFloat(this.apiKey, this.apiSignature, this.apiVersion, this.apiType, accountNumber);
    }

    /**
     * Sends money to mobile number funded by specified payout account
     *
     * @param mobileNumber  Mobile number to send money to
     * @param amount        Amount to send to mobile number
     * @param payoutAccount Payout account to fund this transaction
     * @return
     */
    public Payout sendMoney(String mobileNumber, int amount, String payoutAccount) {
        return this.lipishaAPI.sendMoney(apiKey, apiSignature, apiVersion, apiType, payoutAccount, mobileNumber, amount);
    }

    /**
     * Sends airtime to mobile number funded by specified airtime account
     *
     * @param mobileNumber   Mobile number to send airtime to
     * @param airtimeAmount  Amount of airtime to disburse
     * @param airtimeAccount Airtime account to fund this disbursement
     * @param networkCode    Network code - e.g. SAF - Safaricom
     *                       AIR - Airtel
     *                       YU - Yu
     *                       ORA - Orange
     * @return
     */
    public AirtimeDisbursement sendAirtime(String mobileNumber, int airtimeAmount, String airtimeAccount,
                                           String networkCode) {
        return this.lipishaAPI.sendAirtime(apiKey, apiSignature, apiVersion, apiType, airtimeAccount, mobileNumber,
                airtimeAmount, networkCode);
    }

    /**
     * @param mobileNumber Mobile number to send SMS to
     * @param smsAccount   TransactionAccount to charge for sending SMS
     * @param message      SMS Message content
     * @return
     */
    public SMSReport sendSMS(String mobileNumber, String smsAccount, String message) {
        return this.lipishaAPI.sendSMS(apiKey, apiSignature, apiVersion, apiType, smsAccount, mobileNumber, message);
    }

    /**
     * Acknowledge transaction id (Useful when working via IPN)
     *
     * @param transactionIds List of transaction ids to acknowledge.
     * @return
     */
    public TransactionResponse confirmTransaction(String[] transactionIds) {
        StringBuilder listBuilder = new StringBuilder();
        String delim = "";
        for (String i : transactionIds) {
            listBuilder.append(delim).append(i);
            delim = ",";
        }
        return this.lipishaAPI.confirmTransaction(apiKey, apiSignature, apiVersion, apiType, listBuilder.toString());
    }

    /**
     * Reverse a transaction and refund payment to customer.
     *
     * @param transactionId Transaction id to reverse (May be comma separated string of transactions)
     * @return
     */
    public MultiTransactionResponse reverseTransaction(String transactionId) {
        return this.lipishaAPI.reverseTransaction(apiKey, apiSignature, apiVersion, apiType, transactionId);
    }

    /**
     * Reverse a list of transactions and refund payment to customers.
     *
     * @param transactionIds List of transaction ids to reverse.
     * @return
     */
    public MultiTransactionResponse reverseTransaction(String[] transactionIds) {
        StringBuilder listBuilder = new StringBuilder();
        String delim = "";
        for (String i : transactionIds) {
            listBuilder.append(delim).append(i);
            delim = ",";
        }
        return this.lipishaAPI.reverseTransaction(apiKey, apiSignature, apiVersion, apiType, listBuilder.toString());
    }

    /**
     * Confirm transaction id (Useful when working via IPN)
     *
     * @param transactionId Transaction id to acknowledge (May be comma separated string of transactions)
     * @return
     */
    public TransactionResponse confirmTransaction(String transactionId) {
        return this.lipishaAPI.confirmTransaction(apiKey, apiSignature, apiVersion, apiType, transactionId);
    }


    /**
     * Reconcile records for specified transaction. Updates transaction properties.
     *
     * @param transactionId Transaction id to update
     * @param mobileNumber  New transaction mobile number
     * @param accountNumber New transaction account number to which to move the transacition
     * @param reference     New transaction reference
     * @return
     */
    @Deprecated
    public TransactionResponse reconcileTransaction(String transactionId, String mobileNumber,
                                                    String accountNumber, String reference) {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns transactions based on provided filter parameters.
     * Note that all parameters are nullable.
     *
     * @param transactionIds            Transaction ids to filter against e.g. TC090000,TR909090
     * @param transactionTypes          Transaction types to filter against e,g, Payment,Fees
     * @param transactionMethods        Transaction methods to filter against e.g. Paybill (M-Pesa)
     * @param transactionDateStart      Date of first transaction
     * @param transactionDateEnd        Date of last transaction
     * @param transactionAccountNames   Transaction account names to look up e.g. Test TransactionAccount
     * @param transactionAccountNumbers Transaction account number to filter against e.g. 00155,00200
     * @param transactionReferences     Transaction references to filter against e.g.
     * @param transactionAmountMinimum  Lowest value of transaction to return
     * @param transactionAmountMaximum  Highest value of transaction to return
     * @param transactionStatuses       Transaction statuses to filter against e.g. Reversed,Completed,Acknowledged
     * @param transactionName           Name of filter against (Name of person or entity) e.g.Jane Doe
     * @param transactionMobileNumber   Mobile number of transaction
     * @param transactionEmail          Email address associated with transaction
     * @param limit                     Max number of records to return
     * @param offset                    First transaction to return (0 - start at the beginning, 9 - Start at 10th record)
     * @return
     */
    public MultiTransactionResponse getTransactions(@Nullable String transactionIds,
                                                @Nullable String transactionTypes,
                                                @Nullable String transactionMethods,
                                                @Nullable Date transactionDateStart,
                                                @Nullable Date transactionDateEnd,
                                                @Nullable String transactionAccountNames,
                                                @Nullable String transactionAccountNumbers,
                                                @Nullable String transactionReferences,
                                                @Nullable Float transactionAmountMinimum,
                                                @Nullable Float transactionAmountMaximum,
                                                @Nullable String transactionStatuses,
                                                @Nullable String transactionName,
                                                @Nullable String transactionMobileNumber,
                                                @Nullable String transactionEmail,
                                                @Nullable int limit,
                                                @Nullable int offset) {
        return this.lipishaAPI.getTransactions(apiKey, apiSignature, apiVersion, apiType,
                transactionIds, transactionTypes, transactionMethods, transactionDateStart, transactionDateEnd,
                transactionAccountNames, transactionAccountNumbers, transactionReferences, transactionAmountMinimum,
                transactionAmountMaximum, transactionStatuses, transactionName, transactionMobileNumber,
                transactionEmail, limit, offset);
    }

    /**
     * Get transactions with default for limit and offset
     *
     * @param transactionIds            Transaction ids to filter against e.g. TC090000,TR909090
     * @param transactionTypes          Transaction types to filter against e,g, Payment,Fees
     * @param transactionMethods        Transaction methods to filter against e.g. Paybill (M-Pesa)
     * @param transactionDateStart      Date of first transaction
     * @param transactionDateEnd        Date of last transaction
     * @param transactionAccountNames   Transaction account names to look up e.g. Test TransactionAccount
     * @param transactionAccountNumbers Transaction account number to filter against e.g. 00155,00200
     * @param transactionReferences     Transaction references to filter against e.g.
     * @param transactionAmountMinimum  Lowest value of transaction to return
     * @param transactionAmountMaximum  Highest value of transaction to return
     * @param transactionStatuses       Transaction statuses to filter against e.g. Reversed,Completed,Acknowledged
     * @param transactionName           Name of filter against (Name of person or entity) e.g.Jane Doe
     * @param transactionMobileNumber   Mobile number of transaction
     * @param transactionEmail          Email address associated with transaction
     * @return
     */
    public MultiTransactionResponse getTransactions(@Nullable String transactionIds,
                                                @Nullable String transactionTypes,
                                                @Nullable String transactionMethods,
                                                @Nullable Date transactionDateStart,
                                                @Nullable Date transactionDateEnd,
                                                @Nullable String transactionAccountNames,
                                                @Nullable String transactionAccountNumbers,
                                                @Nullable String transactionReferences,
                                                @Nullable Float transactionAmountMinimum,
                                                @Nullable Float transactionAmountMaximum,
                                                @Nullable String transactionStatuses,
                                                @Nullable String transactionName,
                                                @Nullable String transactionMobileNumber,
                                                @Nullable String transactionEmail) {
        return this.lipishaAPI.getTransactions(apiKey, apiSignature, apiVersion, apiType,
                transactionIds, transactionTypes, transactionMethods, transactionDateStart, transactionDateEnd,
                transactionAccountNames, transactionAccountNumbers, transactionReferences, transactionAmountMinimum,
                transactionAmountMaximum, transactionStatuses, transactionName, transactionMobileNumber,
                transactionEmail, 1000, 0);
    }

    /**
     * Get customer records.
     *
     * @return
     */
    public CustomerResponse getCustomers(){
        return this.lipishaAPI.getCustomers(apiKey, apiSignature, apiVersion, apiType);
    }

    /**
     * Creates a transaction account
     *
     * @param accountType       TransactionAccount type identifier. e.g. 1 for Mpesa Paybill/Airtel Money
     * @param accountName       Name of the new account
     * @param accountManager    Login name of parent account of this new account
     * @return
     */
    public TransactionAccountResponse createTransactionAccount(int accountType, String accountName, String accountManager){
        return this.lipishaAPI.createTransactionAccount(apiKey, apiSignature, apiVersion, apiType,
                accountType, accountName,accountManager);
    }

    public WithdrawalAccountResponse createWithdrawalAccount(int accountType, String accountName, String accountManager,
                                                             String accountNumber, String bankName, String bankBranch,
                                                             String bankAddress, String swiftCode){
        return this.lipishaAPI.createWithdrawalAccount(apiKey, apiSignature, apiVersion, apiType,
                accountType, accountName, accountManager, accountNumber, bankName, bankBranch, bankAddress, swiftCode);
    }


}
