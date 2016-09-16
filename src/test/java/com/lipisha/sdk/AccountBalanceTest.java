package com.lipisha.sdk;

import com.lipisha.sdk.response.AccountBalance;
import com.lipisha.sdk.response.AccountFloat;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * AccountBalance test.
 */
public class AccountBalanceTest
        extends TestCase {

    private LipishaClient lipishaClient;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AccountBalanceTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AccountBalanceTest.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        lipishaClient = new LipishaClient(TestConfig.API_KEY, TestConfig.API_SIGNATURE, TestConfig.BASE_URL);
    }

    public void testGetBalance() {
        lipishaClient.getBalance().enqueue(new Callback<AccountBalance>() {
            public void onResponse(Call<AccountBalance> call, Response<AccountBalance> response) {
                AccountBalance balance = response.body();
                assertNotNull(balance.getBalance());
                assertNotNull(balance.getCurrency());
                assertEquals(balance.getStatusResponse().getStatusCode(), 0);
                assertEquals(balance.getStatusResponse().getStatus(), "SUCCESS");
            }

            public void onFailure(Call<AccountBalance> call, Throwable throwable) {

            }
        });
    }

    public void testGetFloat() {

        lipishaClient.getFloat(TestConfig.FLOAT_ACCOUNT_NUMBER).enqueue(new Callback<AccountFloat>() {
            public void onResponse(Call<AccountFloat> call, Response<AccountFloat> response) {
                AccountFloat accountFloat = response.body();
                assertNotNull(accountFloat.getFloat());
                assertNotNull(accountFloat.getCurrency());
                assertNotNull(accountFloat.getAccountNumber());
                assertEquals(accountFloat.getAccountNumber(), TestConfig.FLOAT_ACCOUNT_NUMBER);
                assertEquals(accountFloat.getStatusResponse().getStatus(), "SUCCESS");
            }

            public void onFailure(Call<AccountFloat> call, Throwable throwable) {

            }
        });
    }
}
