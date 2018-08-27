package com.sendmessage;

import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A class to construct {@link JSONObject} for a payment reminder with dummy data.
 *
 * <p> Unless stated otherwise, all fields are mandatory.
 */
final class PaymentReminder {

  private PaymentReminder() {
  }

  static JSONObject constructMessage() throws JSONException {
    JSONObject orderDetails = new JSONObject().put("orderNumber", "12345");
    JSONArray purchaseItems =
        new JSONArray()
            .put(
                new JSONObject()
                    .put("itemName", "Mirae Asset India Equity Fund - Direct Plan - Growth")
                    .put(
                        "totalPrice",  // Optional
                        new JSONObject()
                            .put("currencyCode", "INR")
                            .put("units", 400)
                            .put("nanos", 0)))
            .put(
                new JSONObject()
                    .put("itemName", "Kotak Standard Multicap Fund- Direct Plan - Growth")
                    .put(
                        "totalPrice",  // Optional
                        new JSONObject()
                            .put("currencyCode", "INR")
                            .put("units", 500)
                            .put("nanos", 0)))
            .put(
                new JSONObject()
                    .put("itemName", "Aditya Bsl Frontline Equity Fund - Direct - Growth")
                    .put(
                        "totalPrice",  // Optional
                        new JSONObject()
                            .put("currencyCode", "INR")
                            .put("units", 200)
                            .put("nanos", 0)));
    JSONObject header =
        new JSONObject()
            .put("title", "Payment reminder")
            .put("description", "Upcoming SIP order");
    JSONObject currentBalance =
        new JSONObject().put("currencyCode", "INR").put("units", 100).put("nanos", 0);

    return new JSONObject()
        .put("templateType", "PAYMENT_REMINDER")
        .put(
            "templateParameters",
            new JSONObject()
                .put("header", header) // Optional
                .put("orderDetails", orderDetails)  // Optional
                .put("reminderMessage", "Please ensure your account has ₹1100 before 28th Aug.")
                .put("currentBalance", currentBalance)  // Optional
                .put("paymentDueDate", "2018-08-28T18:00:00Z")  // Optional
                .put("purchaseItems", purchaseItems)  // Optional
                .put(
                    "paymentActions",
                    new JSONArray()
                        .put(
                            new JSONObject()
                                .put(
                                    "paymentAmount",
                                    new JSONObject()
                                        .put("currencyCode", "INR")
                                        .put("units", 1100)
                                        .put("nanos", 0))
                                .put("merchantTransactionId", UUID.randomUUID().toString())
                                .put("merchantCategoryCode", "3747"))));
  }
}
