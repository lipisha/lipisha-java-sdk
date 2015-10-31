===============================
Lipisha Payment JAVA SDK
===============================


This package provides bindings for the Lipisha Payments API (http://developer.lipisha.com/)

* Free software: Apache 2.0 Licence

Features
--------

* Send money
* Send SMS
* Get Float
* Get Balance
* Send airtime

Examples
--------

See examples here (https://github.com/lipisha/lipisha-java-sdk/src/main/java/examples)


Quick start
-----------

The class below showcases integration with the API.

.. code-block:: java


    package com.lipisha.sdk.examples;

    import com.lipisha.sdk.LipishaClient;
    import com.lipisha.sdk.response.AccountBalance;
    import com.lipisha.sdk.response.AccountFloat;
    import com.lipisha.sdk.response.AirtimeDisbursement;
    import com.lipisha.sdk.response.Payout;

    /**
     * Example of API Integration
     */
    public class APIExample {

        public static final String API_KEY = "e6a3b6c1a00f06a0b6042749ff909d5b";
        public static final String API_SIGNATURE = "E6esgl/VTkQsY6El71MN+N78gu3oo3lPnEnLZRW3bMiv/useen09mZyDbNiYRaI05pAh5aScARQVQ+BxLURLwvWX6BMDTWiHj1b3To6+Npv1yU1VRF2d3r5NIrBolgBTn7Xkz50ouzraDgxYcFvC/MYvTiDU6rkpprGUJ7VwhgQ=";
        public static final String API_VERSION = "1.3.0";
        public static final String API_TYPE = "Callback";
        public static final String FLOAT_ACCOUNT_NUMBER = "00454";
        public static final String PAYOUT_ACCOUNT_NUMBER = "00454";
        public static final String AIRTIME_ACCOUNT_NUMBER = "00455";
        public static final String TEST_MOBILE_NUMBER = "0722123456";
        private LipishaClient lipishaClient;

        public APIExample() {
            lipishaClient = new LipishaClient(API_KEY, API_SIGNATURE, LipishaClient.SANDBOX_BASE_URL);
        }

        public void echo(String label, Object object) {
            System.out.printf("%s :: %s\n", label, object);
        }

        public void getBalanceTest() {
            AccountBalance balance = lipishaClient.getBalance();
            echo("Balance", balance.getBalance());
        }

        public void getFloatBalanceTest() {
            AccountFloat accountFloat = lipishaClient.getFloat(FLOAT_ACCOUNT_NUMBER);
            echo("Float", accountFloat.getFloat());
        }

        public void sendMoneyTest() {
            Payout payout = lipishaClient.sendMoney(TEST_MOBILE_NUMBER, 100, PAYOUT_ACCOUNT_NUMBER);
            echo("Payout:Amount", payout.getAmount());
            echo("Payout:Number", payout.getMobileNumber());
            echo("Payout:Reference", payout.getReference());
        }

        public void sendAirtimeTest() {
            AirtimeDisbursement airtimeDisbursement = lipishaClient.sendAirtime(TEST_MOBILE_NUMBER, 100, AIRTIME_ACCOUNT_NUMBER, "SAF");
            echo("Airtime:Amount", airtimeDisbursement.getAmount());
            echo("Airtime:Number", airtimeDisbursement.getMobileNumber());
            echo("Airtime:Reference", airtimeDisbursement.getReference());
        }

        public static void main( String[] args )
        {
            APIExample apiExample = new APIExample();
            apiExample.getBalanceTest();
            apiExample.getFloatBalanceTest();
            apiExample.sendMoneyTest();
            apiExample.sendAirtimeTest();
        }

    }


Installation
------------

Download the JAR from here:

https://github.com/lipisha/lipisha-java-sdk/releases

Add a as maven dependency::

    <dependency>
      <groupId>com.lipisha.sdk</groupId>
      <artifactId>lipisha-sdk</artifactId>
      <version>1.0</version>
    </dependency>

See documentation for detailed API. Refer to Lipisha API for parameters required for each method.
