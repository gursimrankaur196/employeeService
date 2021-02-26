package com.paypal.bfs.test.employeeserv.impl.service;

import com.paypal.bfs.test.employeeserv.api.requests.EmployeeRequest;
import com.paypal.bfs.test.employeeserv.api.service.EmployeeService;
import com.paypal.bfs.test.employeeserv.api.dao.EmployeeDao;
import com.paypal.bfs.test.employeeserv.api.model.EmployeeEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gursimran.kaur
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    EmployeeDao employeeDao;
    
    @Override
    public EmployeeEntity getEmployeeById(int id) {
        EmployeeEntity employee = employeeDao.lookupById(id);
        return employee;
    }

    @Override
    public EmployeeEntity addEmployee(EmployeeRequest employeeRequest) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employeeRequest, employeeEntity);
        return employeeDao.save(employeeEntity);
    }
}
