package com.lipisha.sdk;

import com.lipisha.sdk.response.Transaction;
import com.lipisha.sdk.response.TransactionResponse;
import com.lipisha.sdk.response.TransactionsResponse;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

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
        TransactionResponse acknowledgement = lipishaClient.confirmTransaction(TestConfig.TRANSACTION_IDS);
        assertEquals(true, acknowledgement.isSuccessful());
        Transaction transaction = acknowledgement.getTransaction();
        assertNotNull(transaction);
        assertNotNull(transaction.getTransactionId());
        assertNotNull(transaction.getTransactionMethod());
        assertNotNull(transaction.getTransactionDate());
        assertNotNull(transaction.getTransactionStatus());
        assertNotNull(transaction.getTransactionType());
    }

    public void testReconcileTransaction() {
        String newReference = "NEW-TX-REF";
        TransactionResponse transactionResponse = lipishaClient.reconcileTransaction(TestConfig.TRANSACTION_ID_RECONCILE,
                TestConfig.TEST_MOBILE_NUMBER, TestConfig.FLOAT_ACCOUNT_NUMBER, newReference);
        assertEquals(true, transactionResponse.isSuccessful());
        Transaction transaction = transactionResponse.getTransaction();
        assertNotNull(transaction);
        assertEquals(transaction.getTransactionReference(), newReference);
    }

    public void testReverseTransaction() {
        TransactionsResponse transactionResponse = lipishaClient.reverseTransaction(TestConfig.TRANSACTION_ID_REVERSE);
        assertEquals(true, transactionResponse.isSuccessful());
        Transaction transaction = transactionResponse.getTransactions().get(0);
        assertNotNull(transaction);
        assertEquals("Reversed", transaction.getTransactionStatus());
    }

    public void testGetTransactionsById() {
        TransactionsResponse transactionResponse = lipishaClient.getTransactions(TestConfig.TRANSACTION_ID_SEARCH,
                null, null, null, null, null, null, null, null, null, null, null, null, null);
        List<Transaction> transactions = transactionResponse.getTransactions();
        assertEquals(true, transactionResponse.isSuccessful());
        assertNotNull(transactions);
        Transaction transaction = transactions.get(0);
        assertEquals(TestConfig.TRANSACTION_ID_SEARCH, transaction.getTransactionId());
    }

}
