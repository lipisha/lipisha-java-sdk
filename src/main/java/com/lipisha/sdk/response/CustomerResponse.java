package com.lipisha.sdk.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * API response from customer
 */
public class CustomerResponse extends BaseStatusResponse {

    @SerializedName("content")
    private List<Customer> customers;

    public CustomerResponse(StatusResponse statusResponse, List<Customer> customers) {
        super(statusResponse);
        this.customers = customers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
