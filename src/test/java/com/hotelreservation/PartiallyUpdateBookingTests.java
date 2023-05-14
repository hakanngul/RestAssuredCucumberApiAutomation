package com.hotelreservation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.hotelreservation.Utility.URL;
import static io.restassured.RestAssured.given;

public class PartiallyUpdateBookingTests extends BaseTest {

    @Test
    public void partiallyUpdateBookingTest() {
        // Create Token
        //String token = createToken();
        // Make a Reservation
        Response newBooking = createBooking();
        int bookingId = newBooking.jsonPath().getJsonObject("bookingid");
        JSONObject body = new JSONObject();
        body.put("firstname", "Melih");

        // Make a Call
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + createToken())
                .body(body.toString())
                .patch(URL + "booking/" + bookingId);

        response.prettyPrint();

        // Assertions

        Assertions.assertEquals("Melih", response.jsonPath().getJsonObject("firstname"));
    }
}
