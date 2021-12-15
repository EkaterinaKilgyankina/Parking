package com.epamtraining.parking.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class CarRequest {
    //TODO to ask why annotations do not show the message
    @NotBlank(message = "location is mandatory")
    @Pattern(regexp = "[0-9]{3}", message = "invalid car number")
    String number;
}
