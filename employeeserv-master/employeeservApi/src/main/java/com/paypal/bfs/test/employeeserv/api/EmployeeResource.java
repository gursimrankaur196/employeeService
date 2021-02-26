package com.paypal.bfs.test.employeeserv.api;

import com.paypal.bfs.test.employeeserv.api.requests.EmployeeRequest;
import com.paypal.bfs.test.employeeserv.api.model.Employee;
import com.paypal.bfs.test.employeeserv.api.model.EmployeeEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.constraints.NotNull;

/**
 * Interface for employee resource operations.
 */
public interface EmployeeResource {

    /**
     * Retrieves the {@link Employee} resource by id.
     *
     * @param id employee id.
     * @return {@link Employee} resource.
     */
    @RequestMapping("/v1/bfs/employees/{id}")
    ResponseEntity<EmployeeEntity> employeeGetById(@NotNull @PathVariable("id") int id) throws Exception;


    @RequestMapping(value = "/v1/bfs/employees/add", method = RequestMethod.POST)
    ResponseEntity<EmployeeEntity> addEmployee(EmployeeRequest employee, BindingResult bindingResult);
    // ----------------------------------------------------------
    // TODO - add a new operation for creating employee resource.
    // ----------------------------------------------------------
}
