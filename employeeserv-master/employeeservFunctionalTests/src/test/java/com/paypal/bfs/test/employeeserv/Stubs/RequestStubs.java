package com.paypal.bfs.test.employeeserv.Stubs;

import com.paypal.bfs.test.employeeserv.api.requests.EmployeeRequest;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.EmployeeEntity;
import java.util.Date;

/**
 * @author gursimran.kaur
 */
public class RequestStubs {

    public static EmployeeEntity getEmployeeEntity(){
        EmployeeEntity employeeRequest = new EmployeeEntity();
        employeeRequest.setId(1);
        employeeRequest.setFirstName("Gursimran");
        employeeRequest.setLastName("Kaur");
        employeeRequest.setDateOfBirth(new Date(1996,05,24));
        Address address = new Address();
        address.setLine1("Line1");
        address.setLine2("Line2");
        address.setCity("City");
        address.setState("State");
        address.setCountry("Country");
        address.setZipCode("ZipCode");
        employeeRequest.setAddress(address);
        return employeeRequest;
    }
    
    public static EmployeeRequest getEmployeeRequest(){
        EmployeeRequest employeeRequest = new EmployeeRequest();
        employeeRequest.setFirstName("Gursimran");
        employeeRequest.setLastName("Kaur");
        employeeRequest.setDateOfBirth(new Date(1996,05,24));
        Address address = new Address();
        address.setLine1("Line1");
        address.setLine2("Line2");
        address.setCity("City");
        address.setState("State");
        address.setCountry("Country");
        address.setZipCode("121001");
        employeeRequest.setAddress(address);
        return employeeRequest;
    }
}
