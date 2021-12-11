package com.epamtraining.parking.model;

import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class BookingRequestForProlonging {
    @Min(30)
    @Max(1440)
    private int duration; //минуты
}

