package examples;

import com.lipisha.sdk.LipishaClient;
import com.lipisha.sdk.response.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * RequestMoneyResponse of API Integration
 */
public class APIExample {

    private static final String BASE_URL = LipishaClient.SANDBOX_BASE_URL;
    public static final String API_KEY = "<YOUR API KEY>";
    public static final String API_SIGNATURE = "<YOUR API SIGNATURE>";
    public static final String API_VERSION = "1.3.0";
    public static final String API_TYPE = "Callback";
    public static final String FLOAT_ACCOUNT_NUMBER = "00455";
    public static final String PAYOUT_ACCOUNT_NUMBER = "00454";
    public static final String AIRTIME_ACCOUNT_NUMBER = "00455";
    public static final String TEST_MOBILE_NUMBER = "0727383066";
    private LipishaClient lipishaClient;

    public APIExample() {
        lipishaClient = new LipishaClient(API_KEY, API_SIGNATURE, BASE_URL);
    }

    public void echo(String label, Object object) {
        System.out.printf("%s :: %s\n", label, object);
    }

    public void getBalance() {
        lipishaClient.getBalance().enqueue(new Callback<AccountBalance>() {
            public void onResponse(Call<AccountBalance> call, Response<AccountBalance> response) {
                AccountBalance balance = response.body();
                echo("Balance", balance.getBalance());
            }

            public void onFailure(Call<AccountBalance> call, Throwable throwable) {

                echo("Get balance", throwable.getMessage());
            }
        });
    }

    public void getFloatBalance() {
        lipishaClient.getFloat(FLOAT_ACCOUNT_NUMBER).enqueue(new Callback<AccountFloat>() {
            public void onResponse(Call<AccountFloat> call, Response<AccountFloat> response) {
                AccountFloat accountFloat = response.body();
                echo("Float", accountFloat.getFloat());
            }

            public void onFailure(Call<AccountFloat> call, Throwable throwable) {

                echo("Get float balance", throwable.getMessage());
            }
        });

    }

    public void sendMoney() {
        lipishaClient.sendMoney(TEST_MOBILE_NUMBER, 100, PAYOUT_ACCOUNT_NUMBER).enqueue(new Callback<Payout>() {
            public void onResponse(Call<Payout> call, Response<Payout> response) {
                Payout payout = response.body();
                echo("Payout:Amount", payout.getAmount());
                echo("Payout:Number", payout.getMobileNumber());
                echo("Payout:Reference", payout.getReference());
            }

            public void onFailure(Call<Payout> call, Throwable throwable) {

                echo("Send Money", throwable.getMessage());
            }
        });
    }

    public void sendAirtime() {
        lipishaClient.sendAirtime(TEST_MOBILE_NUMBER, 100, AIRTIME_ACCOUNT_NUMBER, "SAF")
                .enqueue(new Callback<AirtimeDisbursement>() {
                    public void onResponse(Call<AirtimeDisbursement> call, Response<AirtimeDisbursement> response) {
                        AirtimeDisbursement airtimeDisbursement = response.body();
                        echo("Airtime:Amount", airtimeDisbursement.getAmount());
                        echo("Airtime:Number", airtimeDisbursement.getMobileNumber());
                        echo("Airtime:Reference", airtimeDisbursement.getReference());
                    }

                    public void onFailure(Call<AirtimeDisbursement> call, Throwable throwable) {

                        echo("Send Airtime", throwable.getMessage());
                    }
                });
    }

    public void sendSMS() {
        lipishaClient.sendSMS(TEST_MOBILE_NUMBER, FLOAT_ACCOUNT_NUMBER, "TEST MESSAGE").enqueue(new Callback<SMSReport>() {
            public void onResponse(Call<SMSReport> call, Response<SMSReport> response) {
                SMSReport smsReport = response.body();
                echo("SMS:Message", smsReport.getMessage());
                echo("SMS:Recipient", smsReport.getRecipient());
                echo("SMS:Cost", smsReport.getCost());
            }

            public void onFailure(Call<SMSReport> call, Throwable throwable) {

                echo("Send Sms", throwable.getMessage());
            }
        });
    }

    public void acknowledgeTransaction() {
        lipishaClient.confirmTransaction(new String[]{"B4F16908F"}).enqueue(new Callback<TransactionResponse>() {
            public void onResponse(Call<TransactionResponse> call, Response<TransactionResponse> response) {
                TransactionResponse transactionResponse = response.body();
                Transaction transaction = transactionResponse.getTransaction();
                echo("Transaction:Id", transaction.getTransactionId());
                echo("Transaction:Status", transaction.getTransactionStatus());
            }

            public void onFailure(Call<TransactionResponse> call, Throwable throwable) {

                echo("Acknowledge Transactions", throwable.getMessage());
            }
        });
    }

    public void reverseTransaction() {
        lipishaClient.reverseTransaction("B4F16908F").enqueue(new Callback<MultiTransactionResponse>() {
            public void onResponse(Call<MultiTransactionResponse> call, Response<MultiTransactionResponse> response) {
                MultiTransactionResponse transactionResponse = response.body();
                Transaction transaction = transactionResponse.getTransactions().get(0);
                echo("Transaction:Id", transaction.getTransactionId());
                echo("Transaction:Status", transaction.getTransactionStatus());
            }

            public void onFailure(Call<MultiTransactionResponse> call, Throwable throwable) {

                echo("Reverse Transactions", throwable.getMessage());
            }
        });
    }

    public void getTransactions() {
        lipishaClient.getTransactions("B4F16908F", null, null, null,
                null, null, null, null, null, null, null, null, null, null, 10, 0).enqueue(new Callback<MultiTransactionResponse>() {
            public void onResponse(Call<MultiTransactionResponse> call, Response<MultiTransactionResponse> response) {
                MultiTransactionResponse transactionResponse = response.body();
                for (Transaction transaction : transactionResponse.getTransactions()) {
                    echo("Transaction:Id", transaction.getTransactionId());
                    echo("Transaction:Type", transaction.getTransactionType());
                    echo("Transaction:Amount", transaction.getTransactionAmount());
                    echo("Transaction:Date", transaction.getTransactionDate());
                }
            }

            public void onFailure(Call<MultiTransactionResponse> call, Throwable throwable) {
                echo("Get Transactions", throwable.getMessage());
            }
        });

    }

    public void requestMoney() {
        lipishaClient.requestMoney(API_KEY, API_SIGNATURE, FLOAT_ACCOUNT_NUMBER, TEST_MOBILE_NUMBER, "Paybill (M-Pesa)", "1000",
                "KES", TEST_MOBILE_NUMBER).enqueue(new Callback<RequestResponse>() {
            public void onResponse(Call<RequestResponse> call, Response<RequestResponse> response) {
                RequestResponse requestResponse = response.body();
                echo("RequestMoney:Reference", requestResponse.getContent().getReference());
                echo("RequestMoney:MobileNumber", requestResponse.getContent().getMobileNumber());
                echo("RequestMoney:Method", requestResponse.getContent().getMethod());
                echo("RequestMoney:Amount", requestResponse.getContent().getAmount());
                echo("RequestMoney:AccountNumber", requestResponse.getContent().getAccountNumber());

                echo("RequestMoney:StatusCode", requestResponse.getStatus().getStatusCode());
                echo("RequestMoney:Status", requestResponse.getStatus().getStatus());
                echo("RequestMoney:StatusDescription", requestResponse.getStatus().getStatusDescription());
            }

            public void onFailure(Call<RequestResponse> call, Throwable throwable) {
                echo("RequestMoney", throwable.getMessage());
            }
        });
    }

    public static void main(String[] args) {
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
        //apiExample.requestMoney();
    }

}
