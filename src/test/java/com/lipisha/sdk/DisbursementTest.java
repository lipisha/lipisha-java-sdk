package com.lipisha.sdk;

import com.lipisha.sdk.response.AirtimeDisbursement;
import com.lipisha.sdk.response.Payout;
import com.lipisha.sdk.response.SMSReport;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Test money/sms/airtime disbursement
 */
public class DisbursementTest extends TestCase {

    private LipishaClient lipishaClient;

    public DisbursementTest(String name) {
        super(name);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(DisbursementTest.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        lipishaClient = new LipishaClient(TestConfig.API_KEY, TestConfig.API_SIGNATURE, TestConfig.BASE_URL);
    }

    public void testSendMoney() {
        lipishaClient.sendMoney(TestConfig.TEST_MOBILE_NUMBER, 10, TestConfig.PAYOUT_ACCOUNT_NUMBER).enqueue(new Callback<Payout>() {
            public void onResponse(Call<Payout> call, Response<Payout> response) {
                Payout payout = response.body();
                assertEquals(true, payout.isSuccessful());
                assertNotNull(payout.getAmount());
                assertNotNull(payout.getCustomerName());
                assertNotNull(payout.getMobileNumber());
                assertNotNull(payout.getReference());
                assertEquals(payout.getStatusResponse().getStatus(), "SUCCESS");
            }

            public void onFailure(Call<Payout> call, Throwable throwable) {

            }
        });

    }

    public void testSendAirtime() {
        lipishaClient.sendAirtime(TestConfig.TEST_MOBILE_NUMBER, 10,
                TestConfig.AIRTIME_ACCOUNT_NUMBER, "SAF").enqueue(new Callback<AirtimeDisbursement>() {
            public void onResponse(Call<AirtimeDisbursement> call, Response<AirtimeDisbursement> response) {
                AirtimeDisbursement airtimeDisbursement = response.body();
                assertEquals(true, airtimeDisbursement.isSuccessful());
                assertNotNull(airtimeDisbursement.getMobileNumber());
                assertNotNull(airtimeDisbursement.getReference());
                assertNotNull(airtimeDisbursement.getAmount());
                assertEquals(airtimeDisbursement.getStatusResponse().getStatus(), "SUCCESS");
            }

            public void onFailure(Call<AirtimeDisbursement> call, Throwable throwable) {

            }
        });
    }

    public void testSendAirtimeInvalidAmount() {
        lipishaClient.sendAirtime(TestConfig.TEST_MOBILE_NUMBER, 0,
                TestConfig.AIRTIME_ACCOUNT_NUMBER, "SAF").enqueue(new Callback<AirtimeDisbursement>() {
            public void onResponse(Call<AirtimeDisbursement> call, Response<AirtimeDisbursement> response) {
                AirtimeDisbursement airtimeDisbursement = response.body();
                assertEquals(false, airtimeDisbursement.isSuccessful());
                assertEquals(airtimeDisbursement.getStatusResponse().getStatus(), "FAIL");
            }

            public void onFailure(Call<AirtimeDisbursement> call, Throwable throwable) {

            }
        });

    }
    
    public void testSendSMS(){
        lipishaClient.sendSMS(TestConfig.TEST_MOBILE_NUMBER, TestConfig.AIRTIME_ACCOUNT_NUMBER,
                "TEST MESSAGE").enqueue(new Callback<SMSReport>() {
            public void onResponse(Call<SMSReport> call, Response<SMSReport> response) {
                SMSReport smsReport = response.body();
                assertEquals(true, smsReport.isSuccessful());
                assertNotNull(smsReport.getMessage());
                assertNotNull(smsReport.getRecipient());
                assertNotNull(smsReport.getCost());
            }

            public void onFailure(Call<SMSReport> call, Throwable throwable) {

            }
        });

    }
}