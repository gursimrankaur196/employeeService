package com.paypal.bfs.test.employeeserv.api.dao;

import com.paypal.bfs.test.employeeserv.api.model.EmployeeEntity;

/**
 * @author gursimran.kaur
 */
public interface EmployeeDao {

    EmployeeEntity save(EmployeeEntity employee);

    EmployeeEntity lookupById(int id);
    
}
