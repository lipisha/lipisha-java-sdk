package com.lipisha.examples.ipnsample;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.StringBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author murithi
 */
@WebServlet(urlPatterns = {"/IPNServlet"})
public class IPNServlet extends HttpServlet {

    static final String API_KEY = "<<YOUR-API-KEY>>";
    static final String API_SIGNATURE = "<<YOUR-API-SIGNATURE>>";
    static final String TYPE_INITIATE = "Initiate";
    static final String TYPE_ACKNOWLEDGE = "Acknowledge";
    static final String TYPE_RECEIPT = "Receipt";
    static final String TYPE_PAYMENT = "Payment";
    static final String TYPE_SETTLEMENT = "Settlement";

    static final String STATUS_SUCCESS = "001";
    static final String STATUS_ACKNOWLEDGED = "002";
    static final String STATUS_INITIATE_FAILURE = "002";
    static final String STATUS_INVALID_TRANSACTION = "003";
    static final String STATUS_ERROR_RECEIPT = "004";

    /**
     * class LipishaInitiateSchema(LipishaBaseSchema):
    api_version = OneOf(LIPISHA_API_VERSIONS, not_empty=True)
    api_type = OneOf([TYPE_INITIATE], not_empty=True)
    transaction_date = TimestampValidator(not_empty=True)
    transaction_amount = Number(not_empty=True)
    transaction_type = OneOf(LIPISHA_TRANSACTION_TYPES, not_empty=True)
    transaction_method = String(not_empty=True)
    transaction_name = String(not_empty=True)
    transaction_mobile = String(not_empty=True)
    transaction_paybill = String(not_empty=True)
    transaction_account = String(not_empty=True)
    transaction_merchant_reference = String()
     */

    static final String V_API_KEY = "api_key";
    static final String V_API_SIGNATURE = "api_signature";
    static final String V_API_TYPE = "api_type";
    static final String V_API_VERSION = "api_version";
    static final String V_TRANSACTION_REFERENCE = "transaction_reference";
    static final String V_TRANSACTION_DATE = "transaction_date";
    static final String V_TRANSACTION_AMOUNT = "transaction_amount";
    static final String V_TRANSACTION_TYPE = "transaction_type";
    static final String V_TRANSACTION_METHOD = "transaction_method";
    static final String V_TRANSACTION_NAME = "transaction_name";
    static final String V_TRANSACTION_MOBILE = "transaction_mobile";
    static final String V_TRANSACTION_PAYBILL = "transaction_paybill";
    static final String V_TRANSACTION_ACCOUNT = "transaction_account";
    static final String V_TRANSACTION_MERCHANT_REFERENCE = "transaction_merchant_reference";
    static final String V_TRANSACTION_STATUS = "transaction_status";
    static final String V_TRANSACTION_STATUS_CODE = "transaction_status_code";
    static final String V_TRANSACTION_STATUS_DESCRIPTION = "transaction_status_description";


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String apiType = request.getParameter(V_API_TYPE);
        String apiVersion = request.getParameter(V_API_VERSION);
        String transactionReference = request.getParameter(V_TRANSACTION_REFERENCE);
        String transactionDate = request.getParameter(V_TRANSACTION_DATE);
        String transactionType = request.getParameter(V_TRANSACTION_TYPE);
        String transactionMethod = request.getParameter(V_TRANSACTION_METHOD);
        String transactionName = request.getParameter(V_TRANSACTION_NAME);
        String transactionMobile = request.getParameter(V_TRANSACTION_MOBILE);
        String transactionAccount = request.getParameter(V_TRANSACTION_ACCOUNT);
        String transactionMerchantReference = request.getParameter(
                V_TRANSACTION_MERCHANT_REFERENCE);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String transactionStatusCode = STATUS_SUCCESS;
        String transactionStatus = "Processed";
        String transactionStatusDescription = "Processed";

        // !!!! Do any validation Here
        boolean valid = apiType != null;
        if (valid) {
            if (apiType.equals(TYPE_INITIATE)) {
                //process payment
            } else if (apiType.equals(TYPE_ACKNOWLEDGE)) {
                String transactionStatusCodeAck = request.getParameter(
                    V_TRANSACTION_STATUS_CODE);
                if (transactionStatusCodeAck.equals(STATUS_SUCCESS)) {
                    // Process successful acknowledgement
                } else {
                    // Process invalid acknowledgement
                }
            }
        } else {
            transactionStatusCode = STATUS_INITIATE_FAILURE;
            transactionStatus = "Error";
            transactionStatusDescription = "Error while processing";
        }

        if (apiType == null ? false : apiType.equals(TYPE_INITIATE)){
            JsonBuilder builder = new  JsonBuilder();
            builder.addElement(V_API_KEY, API_KEY);
            builder.addElement(V_API_SIGNATURE, API_SIGNATURE);
            builder.addElement(V_API_VERSION, apiVersion);
            builder.addElement(V_API_TYPE, TYPE_RECEIPT);

            builder.addElement(V_TRANSACTION_REFERENCE, transactionReference);
            builder.addElement(V_TRANSACTION_STATUS, transactionStatus);
            builder.addElement(V_TRANSACTION_STATUS_CODE, transactionStatusCode);
            builder.addLastElement(V_TRANSACTION_STATUS_DESCRIPTION,
                    transactionStatusDescription);
            out.print(builder.getJson());
            out.close();
        } else {
            out.print(new JsonBuilder().getJson());
            out.close();
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>


    /**
     * Simple JSON Builder
     */
    static class JsonBuilder {

        static final String JSON_FORMAT = "\"%s\": \"%s\"";
        static final String JSON_FORMAT_SUFFIXED = "\"%s\": \"%s\",";
        StringBuilder builder;

        public JsonBuilder() {
            builder = new StringBuilder();
            builder.append("{");
        }

        public void addElement(String key, String value){
            builder.append(String.format(JSON_FORMAT_SUFFIXED, key, value));
        }

        public void addLastElement(String key, String value){
            builder.append(String.format(JSON_FORMAT, key, value));
        }

        public String getJson() {
            builder.append("}");
            return builder.toString();
        }
    }

}
