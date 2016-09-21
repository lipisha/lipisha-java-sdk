package com.lipisha.sdk;

import com.lipisha.sdk.response.TransactionAccount;
import com.lipisha.sdk.response.TransactionAccountResponse;
import com.lipisha.sdk.response.WithdrawalAccount;
import com.lipisha.sdk.response.WithdrawalAccountResponse;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * TransactionAccount APIs tests.
 */
public class AccountsTest extends TestCase {

    private LipishaClient lipishaClient;

    public AccountsTest(String name) {
        super(name);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AccountsTest.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        lipishaClient = new LipishaClient(TestConfig.API_KEY, TestConfig.API_SIGNATURE, TestConfig.BASE_URL);
    }

    public void testCreateTransactionAccount(){
        final String newAccountName = "TEST ACCOUNT NAME";lipishaClient.createTransactionAccount(TestConfig.ACCOUNT_TYPE, newAccountName,
                TestConfig.ACCOUNT_MANAGER_LOGIN).enqueue(new Callback<TransactionAccountResponse>() {
            public void onResponse(Call<TransactionAccountResponse> call, Response<TransactionAccountResponse> response) {
                TransactionAccountResponse transactionAccountResponse =  response.body();
                assertEquals(true, transactionAccountResponse.isSuccessful());
                assertNotNull(transactionAccountResponse);
                TransactionAccount transactionAccount = transactionAccountResponse.getTransactionAccount();
                assertNotNull(transactionAccount.getAccountNumber());
                assertNotNull(transactionAccount.getAccountName());
                assertEquals(newAccountName, transactionAccount.getAccountName());
            }

            public void onFailure(Call<TransactionAccountResponse> call, Throwable throwable) {

            }
        });
    }

    public void testCreateWithdrawalAccount(){
        final String newAccountName = "TEST ACCOUNT NAME";
        final String bankName = "Some Bank LLC";
        final String bankBranch = "HQ1";
        final String bankAddress = "12345, SCBXC, Some Street, Some Country, YT78000";
        final String swiftCode = "SXD09890";
        final String accountNumber = "01000000000009";

        lipishaClient.createWithdrawalAccount(TestConfig.ACCOUNT_TYPE,
                newAccountName, TestConfig.ACCOUNT_MANAGER_LOGIN, accountNumber, bankName, bankBranch, bankAddress, swiftCode)
        .enqueue(new Callback<WithdrawalAccountResponse>() {
            public void onResponse(Call<WithdrawalAccountResponse> call, Response<WithdrawalAccountResponse> response) {
                WithdrawalAccountResponse withdrawalAccountResponse = response.body();
                assertNotNull(withdrawalAccountResponse);
                assertEquals(true, withdrawalAccountResponse.isSuccessful());
                WithdrawalAccount withdrawalAccount = withdrawalAccountResponse.getWithdrawalAccount();
                assertEquals(accountNumber, withdrawalAccount.getAccountNumber());
                assertEquals(newAccountName, withdrawalAccount.getAccountName());
                assertEquals(bankName, withdrawalAccount.getBankName());
                assertEquals(bankBranch, withdrawalAccount.getBankBranch());
                assertEquals(bankAddress, withdrawalAccount.getBankAddress());
                assertEquals(swiftCode, withdrawalAccount.getSwiftCode());
            }

            public void onFailure(Call<WithdrawalAccountResponse> call, Throwable throwable) {

            }
        });
    }
}
