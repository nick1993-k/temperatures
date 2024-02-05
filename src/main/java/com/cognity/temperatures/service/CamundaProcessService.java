package com.cognity.temperatures.service;

import com.cognity.temperatures.dto.CityData;
import com.cognity.temperatures.dto.MockoonResponseDTO;
import com.cognity.temperatures.entities.Cities;
import com.cognity.temperatures.repository.CitiesRepository;
import org.camunda.bpm.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.cognity.temperatures.utils.FilteredCitiesUtils.splitString;


@Service
public class CamundaProcessService {

    private static final String PROCESS_KEY = "TemperaturesProcess";
    private static final String VAR_CITIES_DATA = "citiesData";
    private static final String VAR_NUMBER_OF_CITIES = "numberOfCities";
    private static final String VAR_HIGHEST_TEMPERATURE = "highestTemperature";
    private static final String VAR_LOWEST_TEMPERATURE = "lowestTemperature";

    private final ProcessEngine processEngine;
    private final CitiesRepository repository;


    @Autowired
    public CamundaProcessService(ProcessEngine processEngine, CitiesRepository repository) {
        this.processEngine = processEngine;
        this.repository = repository;
    }

    public MockoonResponseDTO startProcessWithMockoonData(MockoonResponseDTO mockoonResponseDTO, int numberOfCities, String highestTemperature, String lowestTemperature) {
        processEngine.getRepositoryService().createDeployment()
                .addClasspathResource("bpnm/temperatures.bpmn")
                .deploy();
        int ht = splitString(highestTemperature);
        int lt = splitString(lowestTemperature);
        var runtimeService = processEngine.getRuntimeService();
        var process = runtimeService.createProcessInstanceByKey(PROCESS_KEY)
                .setVariable(VAR_CITIES_DATA, mockoonResponseDTO).setVariable(VAR_NUMBER_OF_CITIES, numberOfCities).setVariable(VAR_HIGHEST_TEMPERATURE,ht).setVariable(VAR_LOWEST_TEMPERATURE,lt)
                .executeWithVariablesInReturn();

        var response = (MockoonResponseDTO) process.getVariables().getValue("filteredCities", MockoonResponseDTO.class);
        persistToDB(response);
        return response;
    }

    @Transactional
    @Qualifier("transactionManager")
    protected void persistToDB(MockoonResponseDTO responseDTO) {
        for (CityData cityData : responseDTO.getCities()) {
            Cities cities = new Cities();
            cities.setCity(cityData.getCity());
            cities.setTemperature(cityData.getTemperature());
            cities.setHumidity(cityData.getHumidity());
            repository.save(cities);
        }
    }
}
