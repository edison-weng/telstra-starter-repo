package au.com.telstra.simcardactivator.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;

import au.com.telstra.simcardactivator.dto.SimActivationRequest;
import au.com.telstra.simcardactivator.dto.SimCardResponse;
import au.com.telstra.simcardactivator.entity.Record;
import au.com.telstra.simcardactivator.service.RecordService;
import au.com.telstra.simcardactivator.service.ActuatorService;

@RestController
@RequestMapping("/api/v1")
public class SimActivationController {

    private final RecordService recordService;
    private final ActuatorService actuatorService;

    @Autowired
    public SimActivationController(RecordService recordService, ActuatorService actuatorService) {
        this.recordService = recordService;
        this.actuatorService = actuatorService;
    }

    @GetMapping("/query")
    public ResponseEntity<SimCardResponse> getSimCard(@RequestParam Long simCardId) {
        Record record = recordService.findById(simCardId);

        if (record == null) {
            return ResponseEntity.notFound().build();
        }

        SimCardResponse response = new SimCardResponse(
                record.getIccid(),
                record.getCustomerEmail(),
                record.isActive());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/activate")
    public ResponseEntity<String> createSimCard(@Valid @RequestBody SimActivationRequest request) {
        boolean isSuccess = actuatorService.activateSimCard(request.getIccid());

        Record record = new Record(
                request.getIccid(),
                request.getCustomerEmail(),
                isSuccess);
        Record savedRecord = recordService.save(record);

        if (isSuccess) {
            String successMessage = "SIM card activated successfully, ID: " + savedRecord.getId();
            System.out.println(successMessage);
            return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
        }

        String errorMessage = "SIM card activation failed";
        System.out.println(errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}