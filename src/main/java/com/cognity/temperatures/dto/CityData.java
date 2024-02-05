package com.cognity.temperatures.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class CityData implements Serializable {

    @JsonProperty("city")
    private String city;

    @JsonProperty("temperature")
    private String temperature;

    @JsonProperty("humidity")
    private String humidity;
}
