package com.hotelreservation;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingDates {
    @JsonProperty("checkin")
    String checkin;
    @JsonProperty("checkout")
    String checkout;
}
