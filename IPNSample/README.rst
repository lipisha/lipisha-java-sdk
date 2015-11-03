===============================
Lipisha Payment JAVA IPN Examples
===============================


This folder contains a sample implemementation of a Lipisha IPN callback.
Lipisha POST payment data to a URL configured at https://lipisha.com


Running the Sample
==========================

To run this example, install Maven (https://maven.apache.org/)

Then run:

        mvn tomcat:run

At deployment, you need to implement validation and change API_KEY and API_SIGNATURE.

These parameters may be loaded using safer methods such as runtime environment variables.

Sending Sample IPN Callback
===========================

.. code-block:: bash

    IPNTest$ curl --verbose --data "api_type=Initiate&apiVersion=1.3.0&transaction_date=2015-11-01 12:22\
    > &transaction_amount=200.00&transaction_type=Payment&transaction_method=Paybill (MPESA)&\
    > transaction_name=AN.OTHER&transaction_reference=TX900100&\
    > transaction_mobile=0777111111&transaction_paybill=961700&transaction_account=09876&transaction_merchant_reference=45" http://localhost:8080/IPNSample/IPNServlet
    * Hostname was NOT found in DNS cache
    *   Trying 127.0.0.1...
    * Connected to localhost (127.0.0.1) port 8080 (#0)
    > POST /IPNSample/IPNServlet HTTP/1.1
    > User-Agent: curl/7.35.0
    > Host: localhost:8080
    > Accept: */*
    > Content-Length: 328
    > Content-Type: application/x-www-form-urlencoded
    > 
    * upload completely sent off: 328 out of 328 bytes
    < HTTP/1.1 200 OK
    * Server Apache-Coyote/1.1 is not blacklisted
    < Server: Apache-Coyote/1.1
    < Content-Type: application/json
    < Content-Length: 266
    < Date: Tue, 03 Nov 2015 16:18:43 GMT
    < 
    * Connection #0 to host localhost left intact
    {"api_key": "<<YOUR-API-KEY>>","api_signature": "<<YOUR-API-SIGNATURE>>","api_version": "null","api_type": "Receipt","transaction_reference": "TX900100","transaction_status": "Processed","transaction_status_code": "001","transaction_status_description": "Processed"}IPNTest$


