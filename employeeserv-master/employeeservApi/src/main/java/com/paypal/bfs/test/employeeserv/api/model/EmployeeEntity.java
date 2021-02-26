package com.paypal.bfs.test.employeeserv.api.model;

import com.paypal.bfs.test.employeeserv.api.converters.AddressConverter;
//import com.paypal.bfs.test.employeeserv.DateOfBirthConverter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * @author gursimran.kaur
 */

@Entity
@ToString
public class EmployeeEntity extends Employee{
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Override
    public Integer getId() {
        return super.getId();
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }
    
    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
//    @Convert(converter = DateOfBirthConverter.class)
    public Date getDateOfBirth() {
        return super.getDateOfBirth();
    }


    @Override
    @Convert(converter = AddressConverter.class)
    public Address getAddress() {
        return super.getAddress();
    }
    
}
