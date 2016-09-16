package com.lipisha.sdk;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Test configuration
 */
public class TestConfig {


    public static final String BASE_URL = LipishaClient.SANDBOX_BASE_URL;
    public static final String API_KEY = "00fb59d22197e55be82bc2a7661eea9b";
    public static final String API_SIGNATURE = "R3qcDDqMhP4SqvWKFM1T8qIo2DyiZH1dOHw0VQVnzz7JqSczqJTDz9xcv7Yn8Ce6am9vv4riPjw3cODq/P/vNfH71PRiRGSJNECwdFKOmgdDFzPHsIFuOXMHOZnWs4ihRi1tm+1IMGF2vqk1I+iERm7Vl3kzhf3CrGMpFw1Qh/g=";
    public static final String API_VERSION = "1.3.0";
    public static final String API_TYPE = "Callback";
    public static final String FLOAT_ACCOUNT_NUMBER = "02114";
    public static final String PAYOUT_ACCOUNT_NUMBER = "00454";
    public static final String AIRTIME_ACCOUNT_NUMBER = "00455";
    public static final String TEST_MOBILE_NUMBER = "0722123456";
    public static final String TRANSACTION_ID_REVERSE = "CF725395E";
    public static final String TRANSACTION_ID_ACKNOWLEDGE = "CF725395E";
    public static final String TRANSACTION_ID_SEARCH = "CF725395E";
    public static final Date TRANSACTION_SEARCH_DATE_START = new GregorianCalendar(2015, Calendar.NOVEMBER, 1).getTime();
    public static final Date TRANSACTION_SEARCH_DATE_END = new Date();
    public static final String ACCOUNT_MANAGER_LOGIN = "test_account";
    public static final int ACCOUNT_TYPE = 1;
    public static final String TEST_CARD_NUMBER = "4242424242424242";
    public static final String TEST_CARD_NAMES = "Full Names on Card";
    public static final String TEST_CARD_ADDRESS = "PO BOX 11111 9999";
    public static final String TEST_CARD_EXPIRY = "082020";
    public static final String TEST_CARD_STATE = "Nairobi";
    public static final String TEST_CARD_COUNTRY = "Kenya";
    public static final String TEST_CARD_ZIP = "99999";
    public static final String TEST_CARD_SECURITY_CODE = "999";
    public static final String TEST_CARD_CURRENCY = "KES";
    public static final String TEST_REQUEST_MONEY_METHOD = "Paybil (MPESA)";

}
