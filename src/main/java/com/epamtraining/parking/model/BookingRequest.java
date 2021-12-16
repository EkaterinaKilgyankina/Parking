package com.epamtraining.parking.model;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
public class BookingRequest {
    @NotBlank(message = "car number is mandatory")
    @Pattern(regexp = "^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}$", message = "invalid car number")
    private String carNumber;
    @Min(value = 1, message = "invalid spot number")
    @Pattern(regexp = "[0-9]{3}", message = "invalid spot number")
    private String spotLocation;
    @NotNull(message = "please, enter the time for start")
    @Future(message = "please, enter the actual time in the future")
    private LocalDateTime from;
    // TODO TO CHANGE BACK TO 30 MIN
    @NotNull(message = "please, enter the time for start")
    @Future(message = "please, enter the actual time in the future")
    private LocalDateTime to;
}
