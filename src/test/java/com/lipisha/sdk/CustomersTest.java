package com.lipisha.sdk;

import com.lipisha.sdk.response.Customer;
import com.lipisha.sdk.response.CustomerResponse;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * Customer APIs tests
 */
public class CustomersTest extends TestCase {

    private LipishaClient lipishaClient;

    public CustomersTest(String name) {
        super(name);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(CustomersTest.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        lipishaClient = new LipishaClient(TestConfig.API_KEY, TestConfig.API_SIGNATURE, TestConfig.BASE_URL);
    }

    public void testGetCustomers(){
        CustomerResponse customerResponse = lipishaClient.getCustomers();
        assertEquals(true, customerResponse.isSuccessful());
        List<Customer> customers = customerResponse.getCustomers();
        Customer customer = customers.get(0);
        assertNotNull(customer.getCustomerName());
        assertNotNull(customer.getPaymentsCount());
        assertNotNull(customer.getPaymentsTotal());
    }

}
