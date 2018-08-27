package com.sendmessage;

import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A class to construct {@link JSONObject} for a train ticket with dummy data.
 */
final class TrainTicket {

  private TrainTicket() {
  }

  static JSONObject constructMessage() throws JSONException {
    JSONObject passenger1 =
        new JSONObject()
            .put("name", "Rajesh Batheja")
            .put("ageYears", 30)
            .put("seatType", "Upper Birth")
            .put("seatNumber", "23A");
    JSONObject passenger2 =
        new JSONObject()
            .put("name", "Max Mateev")
            .put("ageYears", 30)
            .put("seatType", "Lower Birth")
            .put("seatNumber", "24B");

    JSONObject departureStation =
        new JSONObject()
            .put("cityName", "Bangalore")
            .put("stationName", "Hyderabad Station")
            .put("stationCode", "HYD");
    JSONObject arrivalStation =
        new JSONObject()
            .put("cityName", "Pune")
            .put("stationName", "Pune Station")
            .put("stationCode", "CST");

    JSONObject trainInfo =
        new JSONObject()
            .put("trainNumber", "12927")
            .put("trainName", "Konkan Express")
            .put("operatorName", "Indian Railways");
    JSONObject trainLeg =
        new JSONObject()
            .put("trainInfo", trainInfo)
            .put("pnrNumber", UUID.randomUUID().toString())
            .put("departureStation", departureStation)
            .put("departureTime", "2018-05-30T15:00:00Z")
            .put("arrivalStation", arrivalStation)
            .put("arrivalTime", "2018-06-30T15:00:00Z")
            .put("passengers", new JSONArray().put(passenger1).put(passenger2));

    return new JSONObject()
        .put("templateType", "TRAIN_TICKET")
        .put(
            "templateParameters",
            new JSONObject()
                .put("merchantTicketId", UUID.randomUUID().toString())
                .put("travelLegs", new JSONArray().put(trainLeg)));
  }

}
