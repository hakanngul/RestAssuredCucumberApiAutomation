package com.hotelreservation;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetBookingByIdTests extends BaseTest {

    Gson json = new Gson();

    @Test
    public void getBookingById() {

        Response newBooking = createBooking();

        //int reservationId = newBooking.jsonPath().getJsonObject("bookingid");
        int reservationId = responseObject.bookingid;

        Response response = given().when()
                .get(Utility.URL + "booking/" + reservationId);
        response
                .then()
                .statusCode(200);

        response.prettyPrint();

        ResponseObject result = json.fromJson(response.getBody().asString(), ResponseObject.class);

        Assertions.assertEquals("Hakan", result.firstname);
        Assertions.assertEquals("GÜL", result.lastname);
        Assertions.assertEquals(200, result.totalprice);
        Assertions.assertTrue(result.depositpaid);
    }
}
