package com.sendmessage;

import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A class to construct {@link JSONObject} for a hotel reservation with dummy data.
 */
final class HotelReservation {

  private HotelReservation() {
  }

  static JSONObject constructMessage() throws JSONException {

    JSONObject hotelInfo =
        new JSONObject()
            .put("hotelName", "Oakwood Premier Prestige")
            .put("cityName", "Bengaluru")
            .put(
                "hotelAddress",
                buildAddress("24, Vittal Mallya Road", "UB City", "Bengaluru", "Karnataka 560001"));
    JSONObject roomInfo = new JSONObject().put("roomType", "Premium");
    JSONObject guestInfo1 = new JSONObject().put("guestName", "Lee Lee");
    JSONObject guestInfo2 = new JSONObject().put("guestName", "Shikha Jain");

    return new JSONObject()
        .put("templateType", "HOTEL_RESERVATION")
        .put(
            "templateParameters",
            new JSONObject()
                .put("merchantBookingId", UUID.randomUUID().toString())
                .put("hotelInfo", hotelInfo)
                .put("roomInfo", roomInfo)
                .put("checkInTime", "2018-03-28T12:00:00Z")
                .put("checkOutTime", "2018-03-29T11:00:00Z")
                .put("guestInfo", new JSONArray().put(guestInfo1).put(guestInfo2)));
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


