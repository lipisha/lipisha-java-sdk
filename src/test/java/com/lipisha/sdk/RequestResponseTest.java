package com.lipisha.sdk;

import com.lipisha.sdk.response.RequestResponse;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by M on 9/16/2016.
 */
public class RequestResponseTest extends TestCase {
    private LipishaClient lipishaClient;

    public RequestResponseTest(String name) {
        super(name);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(RequestResponseTest.class);
    }

    public void setUp() throws Exception {
        super.setUp();
        lipishaClient = new LipishaClient(TestConfig.API_KEY, TestConfig.API_SIGNATURE, TestConfig.BASE_URL);
    }

    public void testRequestMoney() throws Exception {
        lipishaClient.requestMoney(
                TestConfig.API_KEY,
                TestConfig.API_SIGNATURE,
                TestConfig.FLOAT_ACCOUNT_NUMBER,
                TestConfig.TEST_MOBILE_NUMBER,
                TestConfig.TEST_REQUEST_MONEY_METHOD,
                "001",
                TestConfig.TEST_CARD_CURRENCY,
                TestConfig.TEST_MOBILE_NUMBER).enqueue(new Callback<RequestResponse>() {
            public void onResponse(Call<RequestResponse> call, Response<RequestResponse> response) {
                if (response.isSuccessful()) {
                    System.out.print(response.body().getStatus().getStatusCode());
                    assertEquals("SUCCESS", response.body().getStatus().getStatus());
                    assertNotNull(response.body().getStatus().getStatusCode());
                    assertNotNull(response.body().getStatus().getStatusDescription());
                    assertNotNull(response.body().getContent().getAccountNumber());
                    assertNotNull(response.body().getContent().getAmount());
                    assertNotNull(response.body().getContent().getMethod());
                    assertNotNull(response.body().getContent().getMobileNumber());
                    assertNotNull(response.body().getContent().getReference());
                }
            }

            public void onFailure(Call<RequestResponse> call, Throwable throwable) {
                throwable.printStackTrace();
                System.out.print(throwable.getMessage());
            }
        });
    }
}