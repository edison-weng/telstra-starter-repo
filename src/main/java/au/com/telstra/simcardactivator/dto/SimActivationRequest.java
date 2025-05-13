package au.com.telstra.simcardactivator.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class SimActivationRequest {
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