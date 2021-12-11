package com.epamtraining.parking.model;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
public class BookingRequest {
    @NotBlank (message = "car number is mandatory")
    @Pattern(regexp = "[0-9]{3}",message = "ivalid car number")
    private String carNumber;
    @Min(1)
    private long spotNumber;
    @NotNull
    @Future
    private LocalDateTime from;
    @Min(30)
    @Max(1440)
    private int duration; //минуты
}
