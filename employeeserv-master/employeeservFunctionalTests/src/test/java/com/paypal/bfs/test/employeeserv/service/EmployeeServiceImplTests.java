package com.paypal.bfs.test.employeeserv.service;

import com.paypal.bfs.test.employeeserv.impl.exception.customExceptions.EmployeeServiceException;
import com.paypal.bfs.test.employeeserv.api.requests.EmployeeRequest;
import com.paypal.bfs.test.employeeserv.Stubs.RequestStubs;
import com.paypal.bfs.test.employeeserv.api.dao.EmployeeDao;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.impl.service.EmployeeServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * @author gursimran.kaur
 */

public class EmployeeServiceImplTests {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeDao employeeDao;
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFetchEmployee_Success() {

        EmployeeEntity employeeRequest = getEmployeeEntity();
        when(employeeDao.lookupById(any(Integer.class))).thenReturn(employeeRequest);
        EmployeeEntity response = employeeService.getEmployeeById(any(Integer.class));
        verify(employeeDao,times(1)).lookupById(any(Integer.class));
        assertEquals(employeeRequest.getFirstName(), response.getFirstName());
        assertEquals(employeeRequest.getLastName(), response.getLastName());
        assertEquals(employeeRequest.getDateOfBirth(), response.getDateOfBirth());

    }

    @Test
    public void testAddEmployee_Success() {

        EmployeeEntity employeeEntity = getEmployeeEntity();
        EmployeeRequest employeeRequest = RequestStubs.getEmployeeRequest();
        when(employeeDao.save(Mockito.any())).thenReturn(employeeEntity);
        
        EmployeeEntity response = employeeService.addEmployee(employeeRequest);
        verify(employeeDao,times(1)).save(Mockito.any());
        assertEquals(employeeEntity.getFirstName(), response.getFirstName());
        assertEquals(employeeEntity.getLastName(), response.getLastName());
        assertEquals(employeeEntity.getDateOfBirth(), response.getDateOfBirth());

    }

    @Test(expected = EmployeeServiceException.class)
    public void testFetchEmployee_Failure() {

        EmployeeEntity employeeRequest = getEmployeeEntity();
        when(employeeDao.lookupById(any(Integer.class))).thenThrow(new EmployeeServiceException());
        EmployeeEntity response = employeeService.getEmployeeById(any(Integer.class));
    }

    @Test(expected = EmployeeServiceException.class)
    public void testAddEmployee_Failure() {

        EmployeeEntity employeeEntity = getEmployeeEntity();
        EmployeeRequest employeeRequest = RequestStubs.getEmployeeRequest();
        when(employeeDao.save(Mockito.any())).thenThrow(new EmployeeServiceException());

        EmployeeEntity response = employeeService.addEmployee(employeeRequest);
        verify(employeeDao,times(1)).save(Mockito.any());
    }
    
    
    
    private EmployeeEntity getEmployeeEntity(){
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

}
