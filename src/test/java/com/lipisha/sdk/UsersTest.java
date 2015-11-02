package com.lipisha.sdk;

import com.lipisha.sdk.response.User;
import com.lipisha.sdk.response.UserResponse;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * User Management APIs tests.
 */
public class UsersTest extends TestCase {

    private LipishaClient lipishaClient;

    public UsersTest(String name) {
        super(name);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(UsersTest.class);
    }


    @Override
    protected void setUp() throws Exception {
        super.setUp();
        lipishaClient = new LipishaClient(TestConfig.API_KEY, TestConfig.API_SIGNATURE, TestConfig.BASE_URL);
    }

    public void testCreateUser() {
        String userName = "test_user_001";
        String userEmail = "test_user_001@example.com";
        UserResponse userResponse = lipishaClient.createUser("TEST USER", "Airtime", userEmail, "07999111111",
                userName, "testUserXXX-$$");
        assertNotNull(userResponse);
        assertEquals(true, userResponse.isSuccessful());
        User user = userResponse.getUser();
        assertNotNull(user);
        assertEquals(userName, user.getUserName());
        assertEquals(userEmail, user.getEmail());
    }
}
