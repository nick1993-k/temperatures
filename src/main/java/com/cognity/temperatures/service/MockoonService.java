package com.cognity.temperatures.service;

import com.cognity.temperatures.dto.MockoonResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class MockoonService {
    private final RestTemplate restTemplate;

    @Autowired
    public MockoonService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public MockoonResponseDTO fetchDataFromMockoon() {
        String mockoonUrl = "http://localhost:3002/";
        return restTemplate.getForObject(mockoonUrl, MockoonResponseDTO.class);
    }
}
