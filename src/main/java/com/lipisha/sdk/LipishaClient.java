package com.lipisha.sdk;

import com.lipisha.sdk.api.LipishaAPI;
import com.lipisha.sdk.api.ServiceGenerator;
import com.lipisha.sdk.response.*;

import java.text.SimpleDateFormat;
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
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(DATE_FORMAT);

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
     * @param transactionId Transaction id to acknowledge
     * @return
     */
    public TransactionResponse confirmTransaction(String transactionId) {
        return this.lipishaAPI.confirmTransaction(apiKey, apiSignature, apiVersion, apiType, transactionId);
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
    public MultiTransactionResponse getTransactions(String transactionIds,
                                                    String transactionTypes,
                                                    String transactionMethods,
                                                    Date transactionDateStart,
                                                    Date transactionDateEnd,
                                                    String transactionAccountNames,
                                                    String transactionAccountNumbers,
                                                    String transactionReferences,
                                                    Float transactionAmountMinimum,
                                                    Float transactionAmountMaximum,
                                                    String transactionStatuses,
                                                    String transactionName,
                                                    String transactionMobileNumber,
                                                    String transactionEmail,
                                                    int limit,
                                                    int offset) {

        String strTransactionDateStart = (transactionDateStart == null) ? null : DATE_FORMATTER.format(transactionDateStart);
        String strTransactionDateEnd = (transactionDateEnd == null) ? null : DATE_FORMATTER.format(transactionDateEnd);

        return this.lipishaAPI.getTransactions(apiKey, apiSignature, apiVersion, apiType,
                transactionIds, transactionTypes, transactionMethods, strTransactionDateStart, strTransactionDateEnd,
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
    public MultiTransactionResponse getTransactions(String transactionIds,
                                                    String transactionTypes,
                                                    String transactionMethods,
                                                    Date transactionDateStart,
                                                    Date transactionDateEnd,
                                                    String transactionAccountNames,
                                                    String transactionAccountNumbers,
                                                    String transactionReferences,
                                                    Float transactionAmountMinimum,
                                                    Float transactionAmountMaximum,
                                                    String transactionStatuses,
                                                    String transactionName,
                                                    String transactionMobileNumber,
                                                    String transactionEmail) {


        String strTransactionDateStart = (transactionDateStart == null) ? null : DATE_FORMATTER.format(transactionDateStart);
        String strTransactionDateEnd = (transactionDateEnd == null) ? null : DATE_FORMATTER.format(transactionDateEnd);

        return this.lipishaAPI.getTransactions(apiKey, apiSignature, apiVersion, apiType,
                transactionIds, transactionTypes, transactionMethods, strTransactionDateStart, strTransactionDateEnd,
                transactionAccountNames, transactionAccountNumbers, transactionReferences, transactionAmountMinimum,
                transactionAmountMaximum, transactionStatuses, transactionName, transactionMobileNumber,
                transactionEmail, 1000, 0);
    }

    /**
     * Get customer records.
     *
     * @return
     */
    public CustomerResponse getCustomers() {
        return this.lipishaAPI.getCustomers(apiKey, apiSignature, apiVersion, apiType);
    }

    /**
     * Creates a transaction account
     *
     * @param accountType    TransactionAccount type identifier. e.g. 1 for Mpesa Paybill/Airtel Money
     * @param accountName    Name of the new account
     * @param accountManager Login name of parent account to this new account
     * @return
     */
    public TransactionAccountResponse createTransactionAccount(int accountType, String accountName, String accountManager) {
        return this.lipishaAPI.createTransactionAccount(apiKey, apiSignature, apiVersion, apiType,
                accountType, accountName, accountManager);
    }

    /**
     * Create a withdrawal account (used for settlement)
     *
     * @param accountType    Account Type e.g. 1  for bank accounts
     * @param accountName    Friendly name for account
     * @param accountManager Login name of parent account to this new account
     * @param accountNumber  Bank account number
     * @param bankName       Bank name
     * @param bankBranch     Bank branch
     * @param bankAddress    Bank address
     * @param swiftCode      Swift code for associated bank or bank branch
     * @return
     */
    public WithdrawalAccountResponse createWithdrawalAccount(int accountType, String accountName, String accountManager,
                                                             String accountNumber, String bankName, String bankBranch,
                                                             String bankAddress, String swiftCode) {
        return this.lipishaAPI.createWithdrawalAccount(apiKey, apiSignature, apiVersion, apiType,
                accountType, accountName, accountManager, accountNumber, bankName, bankBranch, bankAddress, swiftCode);
    }

    /**
     * Creates a user
     *
     * @param fullName     Full names for the user
     * @param role         Role to assign to the user e.g. Airtime or Dashboard (Roles must exist on Lipisha)
     * @param email        Email address for this user
     * @param mobileNumber Mobile phone number for this user
     * @param userName     Login for this user
     * @param password     Password for this user
     * @return
     */
    public UserResponse createUser(String fullName, String role, String email, String mobileNumber, String userName,
                                   String password) {
        return this.lipishaAPI.createUser(apiKey, apiSignature, apiVersion, apiType,
                fullName, role, email, mobileNumber, userName, password);
    }

    /**
     * <p>This API call for authorizes a credit card transaction locking in the
     * specified amount in the card holder's bank account.</p>
     * <p/>
     * <p>The transaction then needs to be completed using the {@link #completeCardTransaction(String, String)} call to effect
     * settlement of funds into the merchant's account or reversed using the {@link #reverseCardTransaction(String, String)}
     * API call.</p>
     * <p/>
     * <p>This function reserves funds on the cardholder's account and if successful then you must call the
     * {@link #completeCardTransaction(String, String)} function with the transaction_index and transaction_reference returned by
     * this function to actually move the money to your account.</p>
     * <p/>
     * <p>Kindly note that in some cases, debit card transactions may be settled before the <b>Complete Card
     * Transaction</b> API call is completed and may NOT be reversible depending on the issuing bank.</p>
     *
     * @param accountNumber The lipisha account number to which the transaction will be charged e.g. 00500
     * @param cardNumber    Full 16 digit card number with no spaces
     * @param address1      Address line 1 one of the card holder
     * @param address2      Address line 2 of the card holder (Optional)  <b>May be null</b>
     * @param expiry        Expiry date of the card in this format MMYYYY
     * @param name          Cardholder names as printed on the card
     * @param state         State of the cardholder
     * @param country       Country of the cardholder
     * @param zipCode       Zipcode of the cardholder
     * @param securityCode  Security code (Usually at the back of the card)
     * @param amount        Amount to charge the card
     * @param currency      Currecy code to charge the card in ISO_4217 format
     * @return
     */
    public CardTransactionResponse authorizeCardTransaction(String accountNumber, String cardNumber, String address1,
                                                            String address2, String expiry, String name,
                                                            String state, String country, String zipCode,
                                                            String securityCode, Float amount, String currency) {
        return this.lipishaAPI.authorizeCardTransaction(apiKey, apiSignature, apiVersion, apiType,
                accountNumber, cardNumber, address1, address2, expiry,
                name, state, country, zipCode, securityCode, amount, currency);
    }

    /**
     * <p>This API call completes a credit card transaction and initiates settlement of funds from the cardholder bank
     * account into the merchant's account.</p>
     * <p/>
     * <p>This function moves already reserved funds on the cardholder's account into your account. It's called with
     * the {@param transaction_index} and {@param transaction_reference} returned by the
     * {@link #authorizeCardTransaction} API call to actually move the money to your account.</p>
     *
     * @param transactionIndex
     * @param transactionReference
     * @return
     */
    public CardTransactionResponse completeCardTransaction(String transactionIndex, String transactionReference) {
        return this.lipishaAPI.completeCardTransaction(apiKey, apiSignature, apiVersion, apiType,
                transactionIndex, transactionReference);
    }

    /**
     * <p>This API call reverses an authorized credit card transaction.</p>
     * <p/>
     * <p>This function unreserves funds previously authorized. It's called with the
     * {@param transaction_index} and {@param transaction_reference} returned by the {@link #authorizeCardTransaction}
     * to reverse the authorization.
     *
     * @param transactionIndex
     * @param transactionReference
     * @return
     */
    public CardTransactionResponse reverseCardTransaction(String transactionIndex, String transactionReference) {
        return this.lipishaAPI.reverseCardTransaction(apiKey, apiSignature, apiVersion, apiType,
                transactionIndex, transactionReference);
    }


}
