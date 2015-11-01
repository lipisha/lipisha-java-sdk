/*
 * Copyright 2015 Lipisha Consortium Limited.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lipisha.sdk.api;

import com.lipisha.sdk.response.*;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

import java.util.Date;

/**
 * <p>Sets up a JAVA interface to the Lipisha API</p>
 * <p/>
 * <p>See API documentation here: http://developer.lipisha.com/index.php/app/launch/api</p>
 */
public interface LipishaAPI {

    @FormUrlEncoded
    @POST("/index.php/v2/api/get_balance")
    AccountBalance getBalance(@Field("api_key") String apiKey, @Field("api_signature") String apiSignature,
                              @Field("api_version") String apiVersion, @Field("api_type") String apiType);

    @FormUrlEncoded
    @POST("/index.php/v2/api/get_float")
    AccountFloat getFloat(@Field("api_key") String apiKey, @Field("api_signature") String apiSignature,
                          @Field("api_version") String apiVersion, @Field("api_type") String apiType,
                          @Field("account_number") String accountNumber);

    @FormUrlEncoded
    @POST("/index.php/v2/api/send_money")
    Payout sendMoney(@Field("api_key") String apiKey, @Field("api_signature") String apiSignature,
                     @Field("api_version") String apiVersion, @Field("api_type") String apiType,
                     @Field("account_number") String accountNumber, @Field("mobile_number") String mobileNumber,
                     @Field("amount") int amount);

    @FormUrlEncoded
    @POST("/index.php/v2/api/send_airtime")
    AirtimeDisbursement sendAirtime(@Field("api_key") String apiKey, @Field("api_signature") String apiSignature,
                                    @Field("api_version") String apiVersion, @Field("api_type") String apiType,
                                    @Field("account_number") String accountNumber, @Field("mobile_number") String mobileNumber,
                                    @Field("amount") int airtimeAmount, @Field("network") String network);

    @FormUrlEncoded
    @POST("/index.php/v2/api/send_sms")
    SMSReport sendSMS(@Field("api_key") String apiKey, @Field("api_signature") String apiSignature,
                      @Field("api_version") String apiVersion, @Field("api_type") String apiType,
                      @Field("account_number") String accountNumber, @Field("mobile_number") String mobileNumber,
                      @Field("message") String message);

    @FormUrlEncoded
    @POST("/index.php/v2/api/acknowledge_transaction")
    TransactionResponse confirmTransaction(@Field("api_key") String apiKey, @Field("api_signature") String apiSignature,
                                           @Field("api_version") String apiVersion, @Field("api_type") String apiType,
                                           @Field("transaction") String transactionIds);

    @FormUrlEncoded
    @POST("/index.php/v2/api/reconcile_transaction")
    TransactionResponse reconcileTransaction(@Field("api_key") String apiKey, @Field("api_signature") String apiSignature,
                                             @Field("api_version") String apiVersion, @Field("api_type") String apiType,
                                             @Field("transaction") String transactionId,
                                             @Field("transaction_mobile_number") String mobileNumber,
                                             @Field("transaction_account_number") String accountNumber,
                                             @Field("transaction_reference") String reference);

    @FormUrlEncoded
    @POST("/index.php/v2/api/reverse_transaction")
    TransactionsResponse reverseTransaction(@Field("api_key") String apiKey, @Field("api_signature") String apiSignature,
                                            @Field("api_version") String apiVersion, @Field("api_type") String apiType,
                                            @Field("transaction") String transactionIds);

    @FormUrlEncoded
    @POST("/index.php/v2/api/get_transactions")
    TransactionsResponse getTransactions(@Field("api_key") String apiKey, @Field("api_signature") String apiSignature,
                                         @Field("api_version") String apiVersion, @Field("api_type") String apiType,
                                         @Field("transaction") String transactionIds,
                                         @Field("transaction_type") String transactionTypes,
                                         @Field("transaction_method") String transactionMethods,
                                         @Field("transaction_date_start") Date transactionDateStart,
                                         @Field("transaction_date_end") Date transactionDateEnd,
                                         @Field("transaction_account_name") String transactionAccountNames,
                                         @Field("transaction_account_number") String transactionAccountNumbers,
                                         @Field("transaction_reference") String transactionReferences,
                                         @Field("transaction_amount_minimum") Float transactionAmountMinimum,
                                         @Field("transaction_amount_maximum") Float transactionAmountMaximum,
                                         @Field("transaction_status") String transactionStatuses,
                                         @Field("transaction_name") String transactionName,
                                         @Field("transaction_mobile_number") String transactionMobileNumber,
                                         @Field("transaction_email") String transactionEmail,
                                         @Field("limit") int limit,
                                         @Field("offset") int offset);
}
