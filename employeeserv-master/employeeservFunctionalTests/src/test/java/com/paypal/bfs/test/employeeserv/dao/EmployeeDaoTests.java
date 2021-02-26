package com.paypal.bfs.test.employeeserv.dao;

import com.paypal.bfs.test.employeeserv.Stubs.*;
import com.paypal.bfs.test.employeeserv.impl.exception.customExceptions.*;
import com.paypal.bfs.test.employeeserv.impl.repository.EmployeeRepository;
import com.paypal.bfs.test.employeeserv.api.model.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.impl.dao.EmployeeDaoImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @author gursimran.kaur
 */

public class EmployeeDaoTests {

    @InjectMocks
    private EmployeeDaoImpl dao;
    
    @Mock
    private EmployeeRepository repository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void TestSaveEmployee(){

        EmployeeEntity transitionLog = RequestStubs.getEmployeeEntity();
        when(repository.save(transitionLog)).thenReturn(transitionLog);
        EmployeeEntity result = dao.save(transitionLog);
        
        verify(repository,times(1)).save(any(EmployeeEntity.class));
        assertEquals(transitionLog.getId(), result.getId());
        assertEquals(transitionLog.getFirstName(), result.getFirstName());
        assertEquals(transitionLog.getLastName(), result.getLastName());
        assertEquals(transitionLog.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(transitionLog.getAddress(), result.getAddress());
    }
    
    @Test(expected = EmployeeServiceException.class)
    public void TestSaveEmployeeWithIncorrectInput(){

        EmployeeEntity transitionLog = RequestStubs.getEmployeeEntity();
        when(repository.save(transitionLog)).thenThrow(new EmployeeServiceException(Mockito.anyString(), Mockito.anyString()));
        EmployeeEntity result = dao.save(transitionLog);
        verify(repository,times(1)).save(any(EmployeeEntity.class));
    }

    @Test
    public void TestFetchEmployee_whenIdInDb() throws Exception {

        EmployeeEntity transitionLog = RequestStubs.getEmployeeEntity();
        Optional<EmployeeEntity> optionalEmployeeEntity = Optional.of(transitionLog);
        when(repository.findById(anyInt())).thenReturn(optionalEmployeeEntity);
        EmployeeEntity result = dao.lookupById(anyInt());

        verify(repository,times(1)).findById(anyInt());
        assertEquals(transitionLog.getId(), result.getId());
        assertEquals(transitionLog.getFirstName(), result.getFirstName());
        assertEquals(transitionLog.getLastName(), result.getLastName());
        assertEquals(transitionLog.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(transitionLog.getAddress(), result.getAddress());
    }

    @Test(expected = EmployeeServiceException.class)
    public void TestFetchEmployee_whenIdNotInDb() {

        EmployeeEntity transitionLog = RequestStubs.getEmployeeEntity();
        Optional<EmployeeEntity> optionalEmployeeEntity = null;
        when(repository.findById(anyInt())).thenReturn(Optional.empty());
        EmployeeEntity result = dao.lookupById(anyInt());

        verify(repository,times(1)).findById(anyInt());
    }
    @Test(expected = EmployeeServiceException.class)
    public void TestFetchEmployee_whenDbThrowsException() {

        EmployeeEntity transitionLog = RequestStubs.getEmployeeEntity();
        when(repository.findById(anyInt())).thenThrow(
                new EmployeeServiceException(Mockito.anyString(),Mockito.anyString()));
        EmployeeEntity result = dao.lookupById(anyInt());

        verify(repository,times(1)).findById(anyInt());
    }
}
