package com.cognity.temperatures.delegates;

import com.cognity.temperatures.dto.MockoonResponseDTO;
import lombok.NoArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.cognity.temperatures.utils.FilteredCitiesUtils.filterCities;


@Component("getCities")
@NoArgsConstructor
public class GetCities implements JavaDelegate {

    private static final String VAR_CITIES_DATA = "citiesData";
    private static final String VAR_FILTERED_CITIES_DATA = "filteredCities";
    private static final String VAR_NUMBER_OF_CITIES = "numberOfCities";
    private static final String VAR_HIGHEST_TEMPERATURE = "highestTemperature";
    private static final String VAR_LOWEST_TEMPERATURE = "lowestTemperature";

    @Override
    @Transactional
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("Executing Get Cities Service Task...");
        var citiesData = (MockoonResponseDTO) delegateExecution.getVariable(VAR_CITIES_DATA);
        var numberOfCities = (int) delegateExecution.getVariable(VAR_NUMBER_OF_CITIES);
        var highestTemperature = (int) delegateExecution.getVariable(VAR_HIGHEST_TEMPERATURE);
        var lowestTemperature = (int) delegateExecution.getVariable(VAR_LOWEST_TEMPERATURE);

        var filteredCities = (MockoonResponseDTO) filterCities(citiesData,lowestTemperature,highestTemperature,numberOfCities);
        delegateExecution.setVariable(VAR_FILTERED_CITIES_DATA, filteredCities);
    }
}
