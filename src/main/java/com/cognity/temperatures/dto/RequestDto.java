package com.cognity.temperatures.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDto {

    @JsonProperty("numberOfCities")
    private int numberOfCities;

    @JsonProperty("highestTemperature")
    private String highestTemperature;

    @JsonProperty("lowestTemperature")
    private String lowestTemperature;
}
