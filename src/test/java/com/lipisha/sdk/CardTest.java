package com.lipisha.sdk;

import com.lipisha.sdk.response.CardTransactionResponse;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Card APIs tests.
 */
public class CardTest extends TestCase {

    private LipishaClient lipishaClient;

    public CardTest(String name) {
        super(name);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(CardTest.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        lipishaClient = new LipishaClient(TestConfig.API_KEY, TestConfig.API_SIGNATURE, TestConfig.BASE_URL);
    }

    public void testCardAuthorizeComplete() {
        lipishaClient.authorizeCardTransaction(TestConfig.FLOAT_ACCOUNT_NUMBER,
                TestConfig.TEST_CARD_NUMBER,
                TestConfig.TEST_CARD_ADDRESS,
                "",
                TestConfig.TEST_CARD_EXPIRY,
                TestConfig.TEST_CARD_NAMES,
                TestConfig.TEST_CARD_STATE,
                TestConfig.TEST_CARD_COUNTRY,
                TestConfig.TEST_CARD_ZIP,
                TestConfig.TEST_CARD_SECURITY_CODE,
                100.00f,
                TestConfig.TEST_CARD_CURRENCY).enqueue(new Callback<CardTransactionResponse>() {
            public void onResponse(Call<CardTransactionResponse> call, Response<CardTransactionResponse> response) {
                CardTransactionResponse cardTransactionResponse = response.body();
                assertNotNull(cardTransactionResponse);
                assertEquals(true, cardTransactionResponse.isSuccessful());
                String transactionIndex = cardTransactionResponse.getTransactionIndex();
                String transactionReference = cardTransactionResponse.getTransactionReference();
                assertNotNull(transactionIndex);
                assertNotNull(transactionReference);

                completeCardTransaction(transactionIndex, transactionReference);
            }

            public void onFailure(Call<CardTransactionResponse> call, Throwable throwable) {

            }
        });
    }

    public void completeCardTransaction(final String transactionIndex, final String transactionReference) {
        lipishaClient.completeCardTransaction(transactionIndex,
                transactionReference).enqueue(new Callback<CardTransactionResponse>() {
            public void onResponse(Call<CardTransactionResponse> call, Response<CardTransactionResponse> response) {
                CardTransactionResponse completeResponse = response.body();
                assertNotNull(completeResponse);
                assertEquals(true, completeResponse.isSuccessful());
                assertEquals(transactionIndex, completeResponse.getTransactionIndex());
                assertEquals(transactionReference, completeResponse.getTransactionReference());
            }

            public void onFailure(Call<CardTransactionResponse> call, Throwable throwable) {

            }
        });

    }

    public void testCardAuthorizeReverse() {
        lipishaClient.authorizeCardTransaction(TestConfig.FLOAT_ACCOUNT_NUMBER,
                TestConfig.TEST_CARD_NUMBER,
                TestConfig.TEST_CARD_ADDRESS,
                "",
                TestConfig.TEST_CARD_EXPIRY,
                TestConfig.TEST_CARD_NAMES,
                TestConfig.TEST_CARD_STATE,
                TestConfig.TEST_CARD_COUNTRY,
                TestConfig.TEST_CARD_ZIP,
                TestConfig.TEST_CARD_SECURITY_CODE,
                100.00f,
                TestConfig.TEST_CARD_CURRENCY).enqueue(new Callback<CardTransactionResponse>() {
            public void onResponse(Call<CardTransactionResponse> call, Response<CardTransactionResponse> response) {
                CardTransactionResponse cardTransactionResponse = response.body();
                assertNotNull(cardTransactionResponse);
                assertEquals(true, cardTransactionResponse.isSuccessful());
                String transactionIndex = cardTransactionResponse.getTransactionIndex();
                String transactionReference = cardTransactionResponse.getTransactionReference();
                assertNotNull(transactionIndex);
                assertNotNull(transactionReference);

                reverseCardTransaction(transactionIndex, transactionReference);
            }

            public void onFailure(Call<CardTransactionResponse> call, Throwable throwable) {

            }
        });
    }

    public void reverseCardTransaction(final String transactionIndex, final String transactionReference) {
        lipishaClient.reverseCardTransaction(transactionIndex,
                transactionReference).enqueue(new Callback<CardTransactionResponse>() {
            public void onResponse(Call<CardTransactionResponse> call, Response<CardTransactionResponse> response) {
                CardTransactionResponse reverseResponse = response.body();
                assertNotNull(reverseResponse);
                assertEquals(true, reverseResponse.isSuccessful());
                assertEquals(transactionIndex, reverseResponse.getTransactionIndex());
                assertEquals(transactionReference, reverseResponse.getTransactionReference());
            }

            public void onFailure(Call<CardTransactionResponse> call, Throwable throwable) {

            }
        });

    }
}
