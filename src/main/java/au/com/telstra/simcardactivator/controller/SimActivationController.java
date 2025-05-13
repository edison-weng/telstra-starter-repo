package au.com.telstra.simcardactivator.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/api/v1")
public class SimActivationController {

    private final RestTemplate restTemplate;
    private static final String ACTUATOR_URL = "http://localhost:8444/actuate";

    public SimActivationController() {
        this.restTemplate = new RestTemplate();
    }

    @PostMapping("/activate")
    public ResponseEntity<String> activateSim(@Valid @RequestBody SimActivationRequest request) {
        ActuatorRequest actuatorRequest = new ActuatorRequest(request.getIccid());
        
        ResponseEntity<ActuatorResponse> response = restTemplate.postForEntity(
            ACTUATOR_URL,
            actuatorRequest,
            ActuatorResponse.class
        );

        ActuatorResponse responseBody = response.getBody();
        if (responseBody == null) {
            String errorMessage = "Actuator service returned an invalid response";
            System.out.println(errorMessage);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
        
        if (responseBody.isSuccess()) {
            String successMessage = "SIM card activated successfully";
            System.out.println(successMessage);
            return ResponseEntity.ok(successMessage);
        } else {
            String errorMessage = "SIM card activation failed: The provided ICCID is not valid or cannot be activated";
            System.out.println(errorMessage);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
        }
    }

    private static class SimActivationRequest {
        @NotBlank(message = "ICCID is required")
        private String iccid;
        
        @NotBlank(message = "Email is required")
        @Email(message = "Email should be valid")
        private String customerEmail;

        public String getIccid() {
            return iccid;
        }

        public void setIccid(String iccid) {
            this.iccid = iccid;
        }

        public String getCustomerEmail() {
            return customerEmail;
        }

        public void setCustomerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
        }
    }

    private static class ActuatorRequest {
        private String iccid;

        public ActuatorRequest(String iccid) {
            this.iccid = iccid;
        }

        public String getIccid() {
            return iccid;
        }
    }

    private static class ActuatorResponse {
        private boolean success;

        public boolean isSuccess() {
            return success;
        }
    }
} 