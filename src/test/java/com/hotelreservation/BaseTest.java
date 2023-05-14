package com.hotelreservation;

import com.google.gson.Gson;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static com.hotelreservation.Utility.URL;
import static io.restassured.RestAssured.given;

public class BaseTest {

    Booking responseObject;
    Gson json = new Gson();

    protected Response createBooking() {
        Response response = given().when()
                .contentType(ContentType.JSON)
                .body(bookingObject("Hakan", "GÜL", 400, true))
                .post(URL + "booking");
        response.prettyPrint();
        response.then()
                .statusCode(200);
        setResponseObject(response.asPrettyString());
        return response;
    }

    private void setResponseObject(String body) {
        responseObject = json.fromJson(body, Booking.class);
    }

    protected String bookingObject(String firstname, String lastname, int totalprice, boolean depositpaid) {
        JSONObject body = new JSONObject();
        JSONObject bookingDates = new JSONObject();
        body.put("firstname", firstname);
        body.put("lastname", lastname);
        body.put("totalprice", totalprice);
        body.put("depositpaid", depositpaid);
        bookingDates.put("checkin", "2023-10-10");
        bookingDates.put("checkout", "2023-12-12");
        body.put("bookingdates", bookingDates);
        return body.toString();
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
