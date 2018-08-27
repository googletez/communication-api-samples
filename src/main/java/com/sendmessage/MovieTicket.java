package com.sendmessage;

import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A class to construct {@link JSONObject} for a movie ticket with dummy data.
 *
 * <p> Unless stated otherwise, all fields are mandatory.
 */
final class MovieTicket {

  private MovieTicket() {
  }

  static JSONObject constructMessage() throws JSONException {
    JSONObject movieTheatreInfo =
        new JSONObject()
            .put("theatreName", "PVR: Phoenix Marketcity Mall")
            .put("theatreAddress", buildAddress("Whitefield Road", "Bengaluru - 560066"));
    JSONObject movieInfo =
        new JSONObject()
            .put("movieName", "Jurassic World: Fallen Kingdom")
            .put("contentRating", "U/A");  // Optional
    JSONObject seat1 = new JSONObject().put("seatType", "CLASSIC").put("seatNumber", "E9");
    JSONObject seat2 = new JSONObject().put("seatType", "CLASSIC").put("seatNumber", "E10");
    return new JSONObject()
        .put("templateType", "MOVIE_TICKET")
        .put(
            "templateParameters",
            new JSONObject()
                .put("merchantBookingId", UUID.randomUUID().toString())
                .put("movieTheatreInfo", movieTheatreInfo)
                .put("movieInfo", movieInfo)
                .put("screenName", "AUDI 03")
                .put("qrCode", "qr code")  // Optional
                .put("startTime", "2018-06-07T22:00:00Z")
                .put("seatInfo", new JSONArray().put(seat1).put(seat2)));
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
