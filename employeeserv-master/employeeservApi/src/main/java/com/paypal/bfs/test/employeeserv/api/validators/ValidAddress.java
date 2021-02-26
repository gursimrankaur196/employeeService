package com.paypal.bfs.test.employeeserv.api.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author gursimran.kaur
 */
@Documented
@Constraint(validatedBy = AddressValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAddress {
    
    String message() default "Invalid Address";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}