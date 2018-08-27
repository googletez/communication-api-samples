package com.sendmessage;

import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A class to construct {@link JSONObject} for a bus ticket with dummy data.
 *
 * <p> Unless stated otherwise, all fields are mandatory.
 */
final class BusTicket {

  private BusTicket() {
  }

  static JSONObject constructMessage() throws JSONException {
    JSONObject passenger1 =
        new JSONObject()
            .put("name", "Varun Saraf")
            .put("ageYears", 25)
            .put("seatType", "Upper Birth")
            .put("seatNumber", "23A");
    JSONObject passenger2 =
        new JSONObject()
            .put("name", "Renganathan Ramamoorthy")
            .put("ageYears", 30)
            .put("seatType", "Upper Birth")
            .put("seatNumber", "24A");

    JSONObject departureStop =
        new JSONObject()
            .put("cityName", "Bangalore")
            .put("stationName", "SBC")
            .put("stationAddress", buildAddress("Kadubesanahalli", "Bangalore - 5600083"));
    JSONObject arrivalStop =
        new JSONObject()
            .put("cityName", "Pune")
            .put("stationName", "PNQ")
            .put("stationAddress", buildAddress("Shivajinagar", "Pune - 202203"));

    JSONObject busInfo =
        new JSONObject().put("operatorName", "Eagle Travels").put("busNumber", "06");
    JSONObject busLeg =
        new JSONObject()
            .put("busInfo", busInfo)
            .put("pnrNumber", UUID.randomUUID().toString())
            .put("departureStop", departureStop)
            .put("departureTime", "2018-05-30T15:00:00Z")
            .put("arrivalStop", arrivalStop)
            .put("arrivalTime", "2018-06-30T15:00:00Z")
            .put("passengers", new JSONArray().put(passenger1).put(passenger2));

    return new JSONObject()
        .put("templateType", "BUS_TICKET")
        .put(
            "templateParameters",
            new JSONObject()
                .put("merchantTicketId", UUID.randomUUID().toString())
                .put("travelLegs", new JSONArray().put(busLeg)));
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
