package com.sendmessage;

import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A class to construct {@link JSONObject} for a flight ticket with dummy data.
 */
final class FlightTicket {

  private FlightTicket() {
  }

  static JSONObject constructMessage() throws JSONException {
    JSONObject passenger1 =
        new JSONObject()
            .put("name", "Kishore Kumar")
            .put("ageYears", 30)
            .put("seatType", "Business")
            .put("seatNumber", "2A");
    JSONObject passenger2 =
        new JSONObject()
            .put("name", "Ashish Tilokani")
            .put("ageYears", 24)
            .put("seatType", "Business")
            .put("seatNumber", "1A");

    JSONObject departureAirport =
        new JSONObject()
            .put("cityName", "Bengaluru")
            .put("airportName", "Kempegowda International Airport")
            .put("airportCode", "BLR")
            .put("terminal", "T1");
    JSONObject layoverAirport =
        new JSONObject()
            .put("cityName", "Pune")
            .put("airportName", "Pune International Airport")
            .put("airportCode", "PNQ")
            .put("terminal", "T2");
    JSONObject arrivalAirport =
        new JSONObject()
            .put("cityName", "New Delhi")
            .put("airportName", "Indira Gandhi International Airport")
            .put("airportCode", "DEL")
            .put("terminal", "T1");

    JSONObject flightInfo1 =
        new JSONObject().put("airlineName", "Jet Airways").put("flightNumber", "9W 26");
    String pnr = UUID.randomUUID().toString();
    JSONObject flightLeg1 =
        new JSONObject()
            .put("flightInfo", flightInfo1)
            .put("pnrNumber", pnr)
            .put("departureAirport", departureAirport)
            .put("departureTime", "2018-05-30T15:00:00Z")
            .put("arrivalAirport", layoverAirport)
            .put("arrivalTime", "2018-05-30T18:00:00Z")
            .put("passengers", new JSONArray().put(passenger1).put(passenger2))
            .put("checkInUrl", "www.airline-web-site.com/check-in-page");

    JSONObject flightInfo2 =
        new JSONObject().put("airlineName", "Air Asia").put("flightNumber", "AA 56");
    JSONObject flightLeg2 =
        new JSONObject()
            .put("flightInfo", flightInfo2)
            .put("pnrNumber", pnr)
            .put("departureTime", "2018-05-30T18:00:00Z")
            .put("departureAirport", layoverAirport)
            .put("arrivalTime", "2018-05-30T21:00:00Z")
            .put("arrivalAirport", arrivalAirport)
            .put("passengers", new JSONArray().put(passenger1).put(passenger2))
            .put("checkInUrl", "www.airline-web-site.com/check-in-page");

    return new JSONObject()
        .put("templateType", "AIRLINE_TICKET")
        .put(
            "templateParameters",
            new JSONObject()
                .put("merchantTicketId", UUID.randomUUID())
                .put("travelLegs", new JSONArray().put(flightLeg1).put(flightLeg2)));
  }
}
