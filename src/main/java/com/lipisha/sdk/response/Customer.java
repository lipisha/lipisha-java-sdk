package com.lipisha.sdk.response;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Customer record.
 * Returned by get_customers API.
 */
public class Customer  {

    @SerializedName("customer_name")
    private String customerName;
    @SerializedName("customer_email")
    private String customerEmail;
    @SerializedName("customer_average")
    private Float customerAverage;
    @SerializedName("customer_first_payment_date")
    private Date firstPaymentDate;
    @SerializedName("customer_last_payment_date")
    private Date lastPaymentDate;
    @SerializedName("customer_mobile_number")
    private String mobileNumber;
    @SerializedName("customer_payments")
    private int paymentsCount;
    @SerializedName("customer_total")
    private float paymentsTotal;

    public Customer(String customerName,
                    String customerEmail,
                    Float customerAverage,
                    Date firstPaymentDate,
                    Date lastPaymentDate,
                    String mobileNumber,
                    int paymentsCount,
                    float paymentsTotal) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerAverage = customerAverage;
        this.firstPaymentDate = firstPaymentDate;
        this.lastPaymentDate = lastPaymentDate;
        this.mobileNumber = mobileNumber;
        this.paymentsCount = paymentsCount;
        this.paymentsTotal = paymentsTotal;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public Float getCustomerAverage() {
        return customerAverage;
    }

    public Date getFirstPaymentDate() {
        return firstPaymentDate;
    }

    public Date getLastPaymentDate() {
        return lastPaymentDate;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public int getPaymentsCount() {
        return paymentsCount;
    }

    public float getPaymentsTotal() {
        return paymentsTotal;
    }
}
