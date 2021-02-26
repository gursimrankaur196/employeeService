package com.paypal.bfs.test.employeeserv.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.employeeserv.impl.exception.customExceptions.EmployeeServiceException;
import com.paypal.bfs.test.employeeserv.api.requests.EmployeeRequest;
import com.paypal.bfs.test.employeeserv.api.response.ErrorResponse;
import com.paypal.bfs.test.employeeserv.Stubs.RequestStubs;
import com.paypal.bfs.test.employeeserv.api.service.EmployeeService;
import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.EmployeeEntity;
import com.paypal.bfs.test.employeeserv.impl.EmployeeResourceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author gursimran.kaur
 */
public class EmployeeResourceTests {
    
    @InjectMocks
    private EmployeeResourceImpl employeeResource;

    @Mock
    private EmployeeService employeeService;
    private static final String FETCH_EMPLOYEE = "/v1/bfs/employees/1";
    private static final String ADD_EMPLOYEE = "/v1/bfs/employees/add";
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Before
    public void init_mocks() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeResource)
                .build();
    }
    
    
    @Test
    public void testFetchEmployeeById_Success() throws Exception {

        Integer id = 1;
        EmployeeEntity requestEmployeeEntity = RequestStubs.getEmployeeEntity();
        when(employeeService.getEmployeeById(id)).thenReturn(requestEmployeeEntity);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get(FETCH_EMPLOYEE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        EmployeeEntity response  = objectMapper.readValue(result.getResponse().getContentAsString(),
                EmployeeEntity.class);
        assertEquals(requestEmployeeEntity.getFirstName(), response.getFirstName());
        assertEquals(requestEmployeeEntity.getLastName(), response.getLastName());
        Assert.assertTrue(new ReflectionEquals(requestEmployeeEntity.getAddress()).matches(response.getAddress()));
        assertEquals(requestEmployeeEntity.getDateOfBirth(), response.getDateOfBirth());
    }


    @Test(expected = Exception.class)
    public void testFetchEmployeeById_InvalidId() throws Exception {

        Integer id = 0;
        EmployeeEntity requestEmployeeEntity = RequestStubs.getEmployeeEntity();
        when(employeeService.getEmployeeById(id)).thenThrow(new EmployeeServiceException(Mockito.anyString(), Mockito.anyString()));

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/v1/bfs/employees/0"))
                .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                .andReturn();

        ErrorResponse response  = objectMapper.readValue(result.getResponse().getContentAsString(),
                ErrorResponse.class);
        assertNotNull(response);
    }

    @Test
    public void testAddEmployee_Success() throws Exception {

        EmployeeRequest requestEmployeeEntity = RequestStubs.getEmployeeRequest();
        EmployeeEntity employeeEntity = RequestStubs.getEmployeeEntity();
        when(employeeService.addEmployee(Mockito.any())).thenReturn(employeeEntity);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post(ADD_EMPLOYEE)
                .content(objectMapper.writeValueAsString(requestEmployeeEntity))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        EmployeeEntity response  = objectMapper.readValue(result.getResponse().getContentAsString(),
                EmployeeEntity.class);
        assertEquals(requestEmployeeEntity.getFirstName(), response.getFirstName());
        assertEquals(requestEmployeeEntity.getLastName(), response.getLastName());
        assertEquals(requestEmployeeEntity.getDateOfBirth(), response.getDateOfBirth());
    }

    @Test
    public void testAddEmployee_InvalidZipCode() throws Exception {

        EmployeeRequest requestEmployeeEntity = RequestStubs.getEmployeeRequest();
        Address invalidZipCodeAddress = requestEmployeeEntity.getAddress();
        invalidZipCodeAddress.setZipCode("invalid");
        requestEmployeeEntity.setAddress(invalidZipCodeAddress);
        EmployeeEntity employeeEntity = RequestStubs.getEmployeeEntity();
        when(employeeService.addEmployee(Mockito.any())).thenReturn(employeeEntity);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post(ADD_EMPLOYEE)
                        .content(objectMapper.writeValueAsString(requestEmployeeEntity))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }
    @Test
    public void testAddEmployee_NullAddress() throws Exception {

        EmployeeRequest requestEmployeeEntity = RequestStubs.getEmployeeRequest();
        requestEmployeeEntity.setAddress(null);
        EmployeeEntity employeeEntity = RequestStubs.getEmployeeEntity();
        when(employeeService.addEmployee(Mockito.any())).thenReturn(employeeEntity);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post(ADD_EMPLOYEE)
                        .content(objectMapper.writeValueAsString(requestEmployeeEntity))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }

    @Test
    public void testAddEmployee_NullFirstName() throws Exception {

        EmployeeRequest requestEmployeeEntity = RequestStubs.getEmployeeRequest();
        requestEmployeeEntity.setFirstName(null);
        EmployeeEntity employeeEntity = RequestStubs.getEmployeeEntity();
        when(employeeService.addEmployee(Mockito.any())).thenReturn(employeeEntity);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post(ADD_EMPLOYEE)
                        .content(objectMapper.writeValueAsString(requestEmployeeEntity))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }
    @Test
    public void testAddEmployee_NullLastName() throws Exception {

        EmployeeRequest requestEmployeeEntity = RequestStubs.getEmployeeRequest();
        requestEmployeeEntity.setLastName(null);
        EmployeeEntity employeeEntity = RequestStubs.getEmployeeEntity();
        when(employeeService.addEmployee(Mockito.any())).thenReturn(employeeEntity);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post(ADD_EMPLOYEE)
                        .content(objectMapper.writeValueAsString(requestEmployeeEntity))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }

    @Test
    public void testAddEmployee_NullDOB() throws Exception {

        EmployeeRequest requestEmployeeEntity = RequestStubs.getEmployeeRequest();
        requestEmployeeEntity.setDateOfBirth(null);
        EmployeeEntity employeeEntity = RequestStubs.getEmployeeEntity();
        when(employeeService.addEmployee(Mockito.any())).thenReturn(employeeEntity);

        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.post(ADD_EMPLOYEE)
                        .content(objectMapper.writeValueAsString(requestEmployeeEntity))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError())
                .andReturn();
    }

}
