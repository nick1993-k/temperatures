package com.cognity.temperatures.utils;

import com.cognity.temperatures.dto.CityData;
import com.cognity.temperatures.dto.MockoonResponseDTO;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class FilteredCitiesUtils {

    public static MockoonResponseDTO filterCities(MockoonResponseDTO dto,int minTemperature,int maxTemperature, int numberOfCities) {
        List<CityData> filteredCities = dto.filterCitiesByTemperature(minTemperature, maxTemperature, numberOfCities);
        MockoonResponseDTO response = dto.builder().cities(filteredCities).build();
        return response;
    }

    public static int splitString(String inputString) {
        String[] parts = inputString.split(" ");
        int integerValue = Integer.parseInt(parts[0]);
        return integerValue;
    }
}
