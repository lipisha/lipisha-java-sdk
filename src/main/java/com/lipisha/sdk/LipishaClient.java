package com.lipisha.sdk;

import com.lipisha.sdk.api.LipishaAPI;
import com.lipisha.sdk.api.ServiceGenerator;
import com.lipisha.sdk.response.*;

/**
 * Implements the primary client for access to
 */
public class LipishaClient {

    public static final String SANDBOX_BASE_URL = "http://developer.lipisha.com";
    public static final String PROD_BASE_URL = "https://developer.lipisha.com";
    private static final String API_VERSION = "1.3.0";
    private static final String API_TYPE_CALLBACK = "Callback";
    private static final String API_TYPE_IPN = "Ipn";

    private String apiKey, apiSignature, apiVersion, apiType;
    private LipishaAPI lipishaAPI;

    /**
     * Sets up a {@Link LipishaClient} response with a {@Link LipishaAPI} service bound to production URL;
     *
     * @param apiKey            Lipisha API Key from your production or sandbox account settings
     * @param apiSignature      Lipisha API Signature from production or sandbox settings
     * @param apiVersion        Lipisha API version we're connecting to.
     * @param apiType           Lipisha API type we're connecting to.
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
    public LipishaClient(String apiKey, String apiSignature, String baseUrl){
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
    public AccountBalance getBalance(){
        return this.lipishaAPI.getBalance(this.apiKey, this.apiSignature, this.apiVersion, this.apiType);
    }

    /**
     * Get float balance for given account number.
     *
     * @param accountNumber     Float account number.
     * @return
     */
    public AccountFloat getFloat(String accountNumber){
        return this.lipishaAPI.getFloat(this.apiKey, this.apiSignature, this.apiVersion, this.apiType, accountNumber);
    }

    /**
     * Sends money to mobile number funded by specified payout account
     *
     * @param mobileNumber      Mobile number to send money to
     * @param amount            Amount to send to mobile number
     * @param payoutAccount     Payout account to fund this transaction
     * @return
     */
    public Payout sendMoney(String mobileNumber, int amount, String payoutAccount){
        return this.lipishaAPI.sendMoney(apiKey, apiSignature, apiVersion, apiType, payoutAccount, mobileNumber, amount);
    }

    /**
     * Sends airtime to mobile number funded by specified airtime account
     *
     * @param mobileNumber      Mobile number to send airtime to
     * @param airtimeAmount     Amount of airtime to disburse
     * @param airtimeAccount    Airtime account to fund this disbursement
     * @param networkCode       Network code - e.g. SAF - Safaricom
     *                                              AIR - Airtel
     *                                              YU - Yu
     *                                              ORA - Orange
     * @return
     */
    public AirtimeDisbursement sendAirtime(String mobileNumber, int airtimeAmount, String airtimeAccount, String networkCode){
        return this.lipishaAPI.sendAirtime(apiKey, apiSignature, apiVersion, apiType, airtimeAccount, mobileNumber,
                airtimeAmount, networkCode);
    }

    /**
     *
     * @param mobileNumber      Mobile number to send SMS to
     * @param smsAccount        Account to charge for sending SMS
     * @param message           SMS Message content
     * @return
     */
    public SMSReport sendSMS(String mobileNumber, String smsAccount, String message) {
        return this.lipishaAPI.sendSMS(apiKey, apiSignature, apiVersion, apiType, smsAccount, mobileNumber, message);
    }

}
