package com.paypal.bfs.test.employeeserv.impl.dao;

import com.paypal.bfs.test.employeeserv.impl.exception.customExceptions.EmployeeServiceException;
import com.paypal.bfs.test.employeeserv.impl.exception.customExceptions.ExceptionCodes.ExceptionCodes;
import com.paypal.bfs.test.employeeserv.impl.repository.*;//EmployeeRepository.*;

import com.paypal.bfs.test.employeeserv.api.model.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.api.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author gursimran.kaur
 */

@Component
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public EmployeeEntity save(EmployeeEntity employee) {
        try {
            return employeeRepository.save(employee);
        }catch (Exception ex){
            throw new EmployeeServiceException(ExceptionCodes.ERROR_WHILE_ADDING_TO_DATABASE);
        }
    }

    @Override
    public EmployeeEntity lookupById(int id){
        try {
            return employeeRepository.findById(id)
                    .orElseThrow(() -> new EmployeeServiceException(ExceptionCodes.EMPLOYEE_NOT_PRESENT));
        }
        catch (Exception ex) {
            throw new EmployeeServiceException(ExceptionCodes.ERROR_WHILE_FETCHING_FROM_DATABASE);
        }
    }
}
