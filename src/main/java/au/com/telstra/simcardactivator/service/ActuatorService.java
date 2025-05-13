package au.com.telstra.simcardactivator.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import au.com.telstra.simcardactivator.dto.ActuatorRequest;
import au.com.telstra.simcardactivator.dto.ActuatorResponse;

@Service
public class ActuatorService {
    
    private final RestTemplate restTemplate;
    private static final String ACTUATOR_URL = "http://localhost:8444/actuate";
    
    public ActuatorService() {
        this.restTemplate = new RestTemplate();
    }
    
    public boolean activateSimCard(String iccid) {
        ActuatorRequest request = new ActuatorRequest(iccid);
        
        ResponseEntity<ActuatorResponse> response = restTemplate.postForEntity(
            ACTUATOR_URL,
            request,
            ActuatorResponse.class
        );
        
        ActuatorResponse responseBody = response.getBody();
        if (responseBody == null) {
            throw new IllegalStateException("Actuator service returned an invalid response");
        }
        
        return responseBody.isSuccess();
    }
} 