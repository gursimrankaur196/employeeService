package com.paypal.bfs.test.employeeserv.api.validators;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import org.apache.commons.lang.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author gursimran.kaur
 */
public class AddressValidator implements ConstraintValidator<ValidAddress, Address> {

    private String errorMessage;

    @Override
    public void initialize(ValidAddress validAddress) {
        this.errorMessage = validAddress.message();

    }

    @Override
    public boolean isValid(Address address, ConstraintValidatorContext constraintValidatorContext) {
        // If any of the required field in address is missing , return false
        if(address == null || StringUtils.isEmpty(address.getLine1())|| StringUtils.isEmpty(address.getCity())||
                StringUtils.isEmpty(address.getState()) || StringUtils.isEmpty(address.getCountry()) ||
                StringUtils.isEmpty(address.getZipCode()))
            return false;

        String pinCode = address.getZipCode();
        String regex = "^[1-9]{1}[0-9]{2}\\s{0,1}[0-9]{3}$";
        // Compile the ReGex for India
        Pattern p = Pattern.compile(regex);

        // Pattern class contains matcher() method 
        // to find matching between given pin code 
        // and regular expression. 
        Matcher m = p.matcher(pinCode);
        errorMessage = "Invalid ZipCode";
        constraintValidatorContext.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();

        // Return if the pin code 
        // matched the ReGex 
        return m.matches();
    }


}


