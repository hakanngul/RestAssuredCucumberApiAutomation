package com.hotelreservation;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateBookingTests extends BaseTest {

    @Test
    public void createBookingTest() {
        Response response = createBooking();
        Assertions.assertEquals("Hakan", response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("GÜL", response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals(200, (Integer) response.jsonPath().getJsonObject("booking.totalprice"));
        Assertions.assertEquals(true, response.jsonPath().getJsonObject("booking.depositpaid"));

    }
}
