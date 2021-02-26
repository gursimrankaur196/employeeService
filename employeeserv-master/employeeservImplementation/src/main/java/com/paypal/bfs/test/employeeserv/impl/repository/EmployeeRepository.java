package com.paypal.bfs.test.employeeserv.impl.repository;

import com.paypal.bfs.test.employeeserv.api.model.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author gursimran.kaur
 */

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {}

