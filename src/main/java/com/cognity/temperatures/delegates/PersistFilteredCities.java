package com.cognity.temperatures.delegates;

import com.cognity.temperatures.dto.MockoonResponseDTO;
import lombok.NoArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("persistFilteredCities")
@NoArgsConstructor
public class PersistFilteredCities implements JavaDelegate {

    private static final String VAR_FILTERED_CITIES_DATA = "filteredCities";


    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("Executing Persist to Temperatures DB Service Task...");
        var filteredCitiesToDB = (MockoonResponseDTO) delegateExecution.getVariable(VAR_FILTERED_CITIES_DATA);
    }
}
