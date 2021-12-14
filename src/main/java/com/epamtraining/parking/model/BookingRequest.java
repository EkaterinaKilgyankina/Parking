package com.epamtraining.parking.model;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
public class BookingRequest {
    @NotBlank(message = "car number is mandatory")
    @Pattern(regexp = "[0-9]{3}", message = "invalid car number")
    private String carNumber;
    @Min(value = 1, message = "invalid spot number")
    private long spotNumber;
    @NotNull(message = "please, enter the time for start")
    @Future(message = "please, enter the actual time in the future")
    private LocalDateTime from;
    // TODO TO CHANGE BACK TO 30 MIN
    @Min(value = 5, message = "min time for booking = 30 minutes")
    @Max(value = 1440, message = " time for booking = 24 hours")
    private int duration; //минуты
}
