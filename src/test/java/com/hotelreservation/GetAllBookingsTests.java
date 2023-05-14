package com.hotelreservation;

import org.junit.jupiter.api.Test;

import static com.hotelreservation.Utility.URL;
import static io.restassured.RestAssured.given;

public class GetAllBookingsTests {


    @Test
    public void getAllBookingTest() {
        given().when()
                .get(URL + "booking")
                .then()
                .log().all()
                .statusCode(200);
    }
}


