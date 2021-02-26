package com.paypal.bfs.test.employeeserv.api.service;
import com.paypal.bfs.test.employeeserv.api.requests.EmployeeRequest;
import com.paypal.bfs.test.employeeserv.api.model.EmployeeEntity;

//@Service
public interface EmployeeService {

   public EmployeeEntity getEmployeeById (int id) throws Exception;

   public EmployeeEntity addEmployee(EmployeeRequest employeeRequest) ;
    
}
