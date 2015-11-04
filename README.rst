===============================
Lipisha Payment JAVA SDK
===============================


This package provides bindings for the Lipisha Payments API (http://developer.lipisha.com/)

* Free software: Apache 2.0 Licence

Features
--------

- Send money
- Get float balanace
- Get account balance
- Get Transactions
- Complete transactions
- Reverse transactions
- Authorize card transactions
- Capture card transactions
- Get Customers
- Create sub-users with specific roles
- Send airtime
- Send SMS

Documentation
--------------

- Lipisha API: http://developer.lipisha.com/index.php/app/launch/api
- JavaDoc: http://static.javadoc.io/com.lipisha.sdk/lipisha-sdk/1.1/com/lipisha/sdk/LipishaClient.html

Installation and Download
-------------------------

Download the JAR from here:

https://github.com/lipisha/lipisha-java-sdk/releases

Add as a maven dependency:

.. code-block:: xml

    <dependency>
      <groupId>com.lipisha.sdk</groupId>
      <artifactId>lipisha-sdk</artifactId>
      <version>1.1</version>
    </dependency>

Or Gradle:

.. code-block:: groovy

    compile 'com.lipisha.sdk:lipisha-sdk:1.1'


SDK Examples
------------

See examples here (https://github.com/lipisha/lipisha-java-sdk/src/main/java/examples)

IPN Examples
-------------

https://github.com/lipisha/lipisha-java-sdk/tree/master/IPNSample

Quick start
-----------

The class below showcases integration with the API.

*Note that all responses from the API implement these methods. These can be used to determine if the request was successful.*

.. code-block:: java

    response.isSuccessful()
    response.getStatusMessage()
    response.getStatusDescription()
    response.getStatusCode()

Example Integration
-------------------

.. code-block:: java


    package examples;

    import com.lipisha.sdk.LipishaClient;
    import com.lipisha.sdk.response.*;

    /**
     * Example of API Integration
     */
    public class APIExample {

        private static final String BASE_URL = LipishaClient.SANDBOX_BASE_URL;
        public static final String API_KEY = "<YOUR LIPISHA API KEY>";
        public static final String API_SIGNATURE = "<YOUR LIPISHA API SIGNATURE>";
        public static final String API_VERSION = "1.3.0";
        public static final String API_TYPE = "Callback";
        public static final String FLOAT_ACCOUNT_NUMBER = "00454";
        public static final String PAYOUT_ACCOUNT_NUMBER = "00454";
        public static final String AIRTIME_ACCOUNT_NUMBER = "00455";
        public static final String TEST_MOBILE_NUMBER = "0722123456";
        private LipishaClient lipishaClient;

        public APIExample() {
            lipishaClient = new LipishaClient(API_KEY, API_SIGNATURE, BASE_URL);
        }

        public void echo(String label, Object object) {
            System.out.printf("%s :: %s\n", label, object);
        }

        public void getBalance() {
            AccountBalance balance = lipishaClient.getBalance();
            echo("Balance", balance.getBalance());
        }

        public void getFloatBalance() {
            AccountFloat accountFloat = lipishaClient.getFloat(FLOAT_ACCOUNT_NUMBER);
            echo("Float", accountFloat.getFloat());
        }

        public void sendMoney() {
            Payout payout = lipishaClient.sendMoney(TEST_MOBILE_NUMBER, 100, PAYOUT_ACCOUNT_NUMBER);
            echo("Payout:Amount", payout.getAmount());
            echo("Payout:Number", payout.getMobileNumber());
            echo("Payout:Reference", payout.getReference());
        }

        public void sendAirtime() {
            AirtimeDisbursement airtimeDisbursement = lipishaClient.sendAirtime(TEST_MOBILE_NUMBER, 100, AIRTIME_ACCOUNT_NUMBER, "SAF");
            echo("Airtime:Amount", airtimeDisbursement.getAmount());
            echo("Airtime:Number", airtimeDisbursement.getMobileNumber());
            echo("Airtime:Reference", airtimeDisbursement.getReference());
        }

        public void sendSMS(){
            SMSReport smsReport = lipishaClient.sendSMS(TEST_MOBILE_NUMBER, FLOAT_ACCOUNT_NUMBER, "TEST MESSAGE");
            echo("SMS:Message", smsReport.getMessage());
            echo("SMS:Recipient", smsReport.getRecipient());
            echo("SMS:Cost", smsReport.getCost());
        }

        public void acknowledgeTransaction() {
            TransactionResponse transactionResponse = lipishaClient.confirmTransaction(new String[]{"B4F16908F"});
            Transaction transaction = transactionResponse.getTransaction();
            echo("Transaction:Id", transaction.getTransactionId());
            echo("Transaction:Status", transaction.getTransactionStatus());
        }

        public void reverseTransaction() {
            MultiTransactionResponse transactionResponse = lipishaClient.reverseTransaction("B4F16908F");
            Transaction transaction = transactionResponse.getTransactions().get(0);
            echo("Transaction:Id", transaction.getTransactionId());
            echo("Transaction:Status", transaction.getTransactionStatus());
        }

        public void getTransactions(){
            MultiTransactionResponse transactionResponse = lipishaClient.getTransactions("B4F16908F", null, null, null,
                    null, null, null, null, null, null, null, null, null, null, 10, 0);
            for (Transaction transaction: transactionResponse.getTransactions()) {
                echo("Transaction:Id", transaction.getTransactionId());
                echo("Transaction:Type", transaction.getTransactionType());
                echo("Transaction:Amount", transaction.getTransactionAmount());
                echo("Transaction:Date", transaction.getTransactionDate());
            }
        }

        public static void main( String[] args )
        {
            APIExample apiExample = new APIExample();
            //apiExample.getBalance();
            //apiExample.getFloatBalance();
            //apiExample.sendMoney();
            //apiExample.sendAirtime();
            //apiExample.sendSMS();
            //apiExample.confirmTransaction();
            //apiExample.reconcileTransaction();
            //apiExample.reverseTransaction();
            //apiExample.getTransactions();
        }

    }

*See documentation for detailed API. Refer to Lipisha API for parameters required for each method.*
