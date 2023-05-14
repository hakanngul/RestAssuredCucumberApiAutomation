package com.hotelreservation;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.hotelreservation.Utility.URL;
import static io.restassured.RestAssured.given;

public class UpdateBookingTests extends BaseTest {

    @Test
    public void updateBookingTest() {
        // Create Token
        var token = createToken();

        // Make a Reservation
        Response createBookingObject = createBooking();
        int bookingId = createBookingObject.jsonPath().getJsonObject("bookingid");

        // Request

        Response response = given()
                .contentType(ContentType.JSON)
                .header("Cookie", "token=" + token)
                .body(bookingObject("Ayse", "Test", 500, false))
                .put(URL + "booking/" + bookingId);

        response.prettyPrint();
        // Assertions
        String firstName = response.jsonPath().getJsonObject("firstname");
        String lastName = response.jsonPath().getJsonObject("lastname");
        int totalPrice = response.jsonPath().getJsonObject("totalprice");
        boolean depositPaid = response.jsonPath().getJsonObject("depositpaid");
        Assertions.assertEquals("Ayse", firstName);
        Assertions.assertEquals("Test", lastName);
        Assertions.assertEquals(500, totalPrice);
        Assertions.assertFalse(depositPaid);
    }

    public String createToken() {
        JSONObject body = new JSONObject();
        body.put("username", "admin");
        body.put("password", "password123");
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(body.toString())
                .log().all()
                .post(URL + "auth");

        return response.jsonPath().getJsonObject("token").toString();

    }
}
