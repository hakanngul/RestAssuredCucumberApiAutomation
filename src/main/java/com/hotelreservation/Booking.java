package com.hotelreservation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Booking {

    @JsonProperty("bookingid")
    int bookingid;
    ResponseObject booking;
}
