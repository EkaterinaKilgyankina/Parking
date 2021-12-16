package com.epamtraining.parking.model;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class BookingRequestForProlonging {
    @Min(value = 5, message = "min time for prolonging = 30 min")
    //TODO to check the max duration for prolong/ add logic for actual day
    @Max(value = 1440, message = "max time for prolonging = 24 hours")
    private int duration; //минуты
}

