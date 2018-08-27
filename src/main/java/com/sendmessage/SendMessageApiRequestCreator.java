package com.sendmessage;

import java.util.Random;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Class for creating a sample SendMessage API request with dummy data.
 */
public final class SendMessageApiRequestCreator {

  /**
   * Prints the API request for a randomly selected template type with dummy data.
   */
  public static void main(String[] args) {
    JSONObject apiRequest = new JSONObject()
        .put(
            "merchantInfo",
            new JSONObject().put("googleMerchantId", "Google assigned unique ID to the merchant"))
        .put(
            "recipients",
            new JSONArray()
                .put(new JSONObject()
                    .put("vendorPaymentTransactionId", UUID.randomUUID().toString())))
        .put(
            "messagePayload",
            new JSONObject()
                .put("merchantMessageId", UUID.randomUUID().toString())
                .put(
                    "components",
                    new JSONArray()
                        .put(new JSONObject().put("template", buildRandomMessageTemplate()))));
    System.out.println(apiRequest.toString(2 /* Indent factor for pretty printing */));
  }

  private static JSONObject buildRandomMessageTemplate() {
    int randomNumber = new Random().nextInt(7);
    switch (randomNumber) {
      case 0:
        return MovieTicket.constructMessage();
      case 1:
        return BusTicket.constructMessage();
      case 2:
        return TrainTicket.constructMessage();
      case 3:
        return HotelReservation.constructMessage();
      case 4:
        return FlightTicket.constructMessage();
      case 5:
        return FoodDelivery.constructMessage();
      case 6:
        return PaymentReminder.constructMessage();
    }
    throw new AssertionError();
  }
}
