package com.sendmessage;

import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A class to construct {@link JSONObject} for a food delivery receipt with dummy data.
 */
final class FoodDelivery {

  private FoodDelivery() {
  }

  static JSONObject constructMessage() throws JSONException {
    JSONObject sellerInfo = new JSONObject().put("sellerName", "Smokehouse Deli");
    JSONObject orderDetails = new JSONObject().put("orderNumber", UUID.randomUUID().toString());
    JSONObject deliveryStatus = new JSONObject().put("statusMessage", "Confirmed by restaurant");
    JSONArray deliveryItems =
        new JSONArray()
            .put(new JSONObject().put("itemName", "Cheese pizza").put("itemQuantity", 1))
            .put(new JSONObject().put("itemName", "Garlic bread").put("itemQuantity", 2))
            .put(
                new JSONObject()
                    .put("itemName", "Chilli flake paprika pasta")
                    .put("itemQuantity", 8));
    JSONObject trackingDetails =
        new JSONObject()
            .put(
                "contactNumber",
                new JSONObject().put("countryCode", 91).put("nationalNumber", 9772227121L))
            .put("trackingUrl", "https://www.google.com")
            .put("trackingNumber", "12345");

    return new JSONObject()
        .put("templateType", "FOOD_DELIVERY")
        .put(
            "templateParameters",
            new JSONObject()
                .put("expectedArrivalTime", "2018-05-30T18:00:00Z")
                .put("sellerInfo", sellerInfo)
                .put("orderDetails", orderDetails)
                .put("deliveryStatus", deliveryStatus)
                .put("deliveryItems", deliveryItems)
                .put("trackingDetails", trackingDetails)
                .put(
                    "destinationAddress",
                    buildAddress(
                        "24, Vittal Mallya Road", "UB City", "Bengaluru", "Karnataka 560001")));
  }

  /**
   * Constructs value for postal address fields.
   */
  private static JSONObject buildAddress(String... addressLines) throws JSONException {
    JSONArray addressLinesJSON = new JSONArray();
    for (String addressLine : addressLines) {
      addressLinesJSON.put(addressLine);
    }
    return new JSONObject().put("addressLine", addressLinesJSON);
  }

}
