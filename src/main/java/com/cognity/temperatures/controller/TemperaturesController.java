package com.cognity.temperatures.controller;

import com.cognity.temperatures.dto.MockoonResponseDTO;
import com.cognity.temperatures.dto.RequestDto;
import com.cognity.temperatures.service.CamundaProcessService;
import com.cognity.temperatures.service.MockoonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemperaturesController {

    private final MockoonService mockoonService;
    private final CamundaProcessService camundaService;

    @Autowired
    public TemperaturesController(MockoonService mockoonService, CamundaProcessService camundaService) {
        this.mockoonService = mockoonService;
        this.camundaService = camundaService;
    }

    @PostMapping
    public ResponseEntity<MockoonResponseDTO> getFilteredCities(@RequestBody RequestDto dto) {
        MockoonResponseDTO mockoonResponseDTO = mockoonService.fetchDataFromMockoon();
        int numberOfCities = dto.getNumberOfCities();
        String highestTemperature = dto.getHighestTemperature();
        String lowestTemperature = dto.getLowestTemperature();
        var returnValue = camundaService.startProcessWithMockoonData(mockoonResponseDTO, numberOfCities, highestTemperature, lowestTemperature);
        return ResponseEntity.ok(returnValue);
    }
}
