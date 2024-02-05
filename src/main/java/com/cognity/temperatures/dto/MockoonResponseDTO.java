package com.cognity.temperatures.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MockoonResponseDTO implements Serializable {

    @JsonProperty("cities")
    private List<CityData> cities;

    public List<CityData> filterCitiesByTemperature(int minTemperature, int maxTemperature, int numberOfCities) {
        return cities.stream()
                .filter(city -> {
                    int temperature = Integer.parseInt(city.getTemperature().split(" ")[0]);
                    return temperature >= minTemperature && temperature <= maxTemperature;
                })
                .sorted(Comparator.comparingInt(city -> Integer.parseInt(city.getTemperature().split(" ")[0])))
                .limit(numberOfCities)
                .collect(Collectors.toList());
    }

}
