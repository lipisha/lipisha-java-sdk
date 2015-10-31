package com.lipisha.sdk;

import com.lipisha.sdk.response.AccountBalance;
import com.lipisha.sdk.response.AccountFloat;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * AccountBalance test.
 */
public class AccountBalanceTest
        extends TestCase
{

    private LipishaClient lipishaClient;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AccountBalanceTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AccountBalanceTest.class );
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        lipishaClient = new LipishaClient(TestConfig.API_KEY, TestConfig.API_SIGNATURE, LipishaClient.SANDBOX_BASE_URL);
    }

    public void testGetBalance()
    {
        AccountBalance balance = lipishaClient.getBalance();
        assertNotNull(balance.getBalance());
        assertNotNull(balance.getCurrency());
        assertEquals(balance.getStatusResponse().getStatusCode(), 0);
        assertEquals(balance.getStatusResponse().getStatus(), "SUCCESS");
    }

    public void testGetFloat(){

        AccountFloat accountFloat = lipishaClient.getFloat(TestConfig.FLOAT_ACCOUNT_NUMBER);
        assertNotNull(accountFloat.getFloat());
        assertNotNull(accountFloat.getCurrency());
        assertNotNull(accountFloat.getAccountNumber());
        assertEquals(accountFloat.getAccountNumber(), TestConfig.FLOAT_ACCOUNT_NUMBER);
        assertEquals(accountFloat.getStatusResponse().getStatus(), "SUCCESS");
    }
}
