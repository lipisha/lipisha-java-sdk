package com.lipisha.sdk;

import com.lipisha.sdk.response.TransactionAccount;
import com.lipisha.sdk.response.TransactionAccountResponse;
import com.lipisha.sdk.response.WithdrawalAccount;
import com.lipisha.sdk.response.WithdrawalAccountResponse;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
        String newAccountName = "TEST ACCOUNT NAME";
        TransactionAccountResponse transactionAccountResponse = lipishaClient.createTransactionAccount(TestConfig.ACCOUNT_TYPE, newAccountName,
                TestConfig.ACCOUNT_MANAGER_LOGIN);
        assertEquals(true, transactionAccountResponse.isSuccessful());
        assertNotNull(transactionAccountResponse);
        TransactionAccount transactionAccount = transactionAccountResponse.getTransactionAccount();
        assertNotNull(transactionAccount.getAccountNumber());
        assertNotNull(transactionAccount.getAccountName());
        assertEquals(newAccountName, transactionAccount.getAccountName());
    }

    public void testCreateWithdrawalAccount(){
        String newAccountName = "TEST ACCOUNT NAME";
        String bankName = "Some Bank LLC";
        String bankBranch = "HQ1";
        String bankAddress = "12345, SCBXC, Some Street, Some Country, YT78000";
        String swiftCode = "SXD09890";
        String accountNumber = "01000000000009";
        WithdrawalAccountResponse withdrawalAccountResponse = lipishaClient.createWithdrawalAccount(TestConfig.ACCOUNT_TYPE,
                newAccountName, TestConfig.ACCOUNT_MANAGER_LOGIN, accountNumber, bankName, bankBranch, bankAddress, swiftCode);
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
}
