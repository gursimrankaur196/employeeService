package com.paypal.bfs.test.employeeserv.api.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paypal.bfs.test.employeeserv.api.validators.ValidAddress;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author gursimran.kaur
 */

@Data
public class EmployeeRequest {
    
    @NotNull
    String firstName; 
    
    @NotNull
    String lastName;
    
    @NotNull
    @ValidAddress(message = "Invalid Address")
    Address address;
    
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    Date dateOfBirth;
    
}
