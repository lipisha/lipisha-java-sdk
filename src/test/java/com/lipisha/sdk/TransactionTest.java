package com.lipisha.sdk;

import com.lipisha.sdk.response.MultiTransactionResponse;
import com.lipisha.sdk.response.Transaction;
import com.lipisha.sdk.response.TransactionResponse;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

/**
 * Transaction apis tests.
 */
public class TransactionTest extends TestCase {

    private LipishaClient lipishaClient;

    public TransactionTest(String name) {
        super(name);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(TransactionTest.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        lipishaClient = new LipishaClient(TestConfig.API_KEY, TestConfig.API_SIGNATURE, TestConfig.BASE_URL);
    }

    public void testConfirmTransaction() {
        lipishaClient.confirmTransaction(TestConfig.TRANSACTION_ID_ACKNOWLEDGE)
                .enqueue(new Callback<TransactionResponse>() {
                    public void onResponse(Call<TransactionResponse> call, Response<TransactionResponse> response) {
                        TransactionResponse acknowledgement = response.body();
                        assertEquals(true, acknowledgement.isSuccessful());
                        Transaction transaction = acknowledgement.getTransaction();
                        assertNotNull(transaction);
                        assertNotNull(transaction.getTransactionId());
                        assertNotNull(transaction.getTransactionMethod());
                        assertNotNull(transaction.getTransactionDate());
                        assertNotNull(transaction.getTransactionStatus());
                        assertNotNull(transaction.getTransactionType());
                    }

                    public void onFailure(Call<TransactionResponse> call, Throwable throwable) {

                    }
                });

    }

    public void testReverseTransaction() {
        lipishaClient.reverseTransaction(TestConfig.TRANSACTION_ID_REVERSE)
        .enqueue(new Callback<MultiTransactionResponse>() {
            public void onResponse(Call<MultiTransactionResponse> call, Response<MultiTransactionResponse> response) {
                MultiTransactionResponse transactionResponse = response.body();
                assertEquals(true, transactionResponse.isSuccessful());
                Transaction transaction = transactionResponse.getTransactions().get(0);
                assertNotNull(transaction);
                assertEquals("Reversed", transaction.getTransactionStatus());
            }

            public void onFailure(Call<MultiTransactionResponse> call, Throwable throwable) {

            }
        });

    }

    public void testGetTransactionsById() {
        lipishaClient.getTransactions(TestConfig.TRANSACTION_ID_SEARCH,
                null, null, null, null, null, null, null, null, null, null, null, null, null)
        .enqueue(new Callback<MultiTransactionResponse>() {
            public void onResponse(Call<MultiTransactionResponse> call, Response<MultiTransactionResponse> response) {
                MultiTransactionResponse transactionResponse = response.body();
                List<Transaction> transactions = transactionResponse.getTransactions();
                assertEquals(true, transactionResponse.isSuccessful());
                assertNotNull(transactions);
                Transaction transaction = transactions.get(0);
                assertEquals(TestConfig.TRANSACTION_ID_SEARCH, transaction.getTransactionId());
            }

            public void onFailure(Call<MultiTransactionResponse> call, Throwable throwable) {

            }
        });

    }

    public void testGetTransactionsByDate() {
        lipishaClient.getTransactions(null, null, null,
                TestConfig.TRANSACTION_SEARCH_DATE_START, TestConfig.TRANSACTION_SEARCH_DATE_END,
                null, null, null, null, null, null, null, null, null).enqueue(new Callback<MultiTransactionResponse>() {
            public void onResponse(Call<MultiTransactionResponse> call, Response<MultiTransactionResponse> response) {
                MultiTransactionResponse transactionResponse = response.body();
                List<Transaction> transactions = transactionResponse.getTransactions();
                assertEquals(true, transactionResponse.isSuccessful());
                assertNotNull(transactions);
                assertEquals(true, (transactions.size() > 0));
            }

            public void onFailure(Call<MultiTransactionResponse> call, Throwable throwable) {

            }
        });
    }
}
