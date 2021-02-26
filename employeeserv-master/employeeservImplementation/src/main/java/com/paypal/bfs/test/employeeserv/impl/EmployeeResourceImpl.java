package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.impl.exception.CustomExceptionHandler;
import com.paypal.bfs.test.employeeserv.api.requests.EmployeeRequest;
import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.service.EmployeeService;
import com.paypal.bfs.test.employeeserv.api.model.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.api.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotNull;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl extends CustomExceptionHandler implements EmployeeResource  {

    @Autowired
    EmployeeDao employeeDao;
    
    @Autowired
    EmployeeService employeeService;
    
    @Override
    public ResponseEntity<EmployeeEntity> employeeGetById(@NotNull @PathVariable("id") int id) throws Exception {
        
        EmployeeEntity employee = employeeService.getEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<EmployeeEntity> addEmployee(@Valid @RequestBody EmployeeRequest employeeRequest, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) handleRequestError(bindingResult);
            //throw new ValidationException("Validation exception occured");
        
        EmployeeEntity employeeEntity = employeeService.addEmployee(employeeRequest);
        return new ResponseEntity<>(employeeEntity, HttpStatus.CREATED);
    }
}
