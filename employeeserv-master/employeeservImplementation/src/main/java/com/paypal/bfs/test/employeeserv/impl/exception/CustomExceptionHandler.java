package com.paypal.bfs.test.employeeserv.impl.exception;

import com.paypal.bfs.test.employeeserv.impl.exception.customExceptions.EmployeeServiceException;
import com.paypal.bfs.test.employeeserv.impl.exception.customExceptions.ExceptionCodes.ExceptionCodes;
import com.paypal.bfs.test.employeeserv.api.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ValidationException;

/**
 * @author gursimran.kaur
 */

@Slf4j
@Controller
@ControllerAdvice
public class CustomExceptionHandler {
    
    @ExceptionHandler({Exception.class})
    @ResponseBody
    public ResponseEntity<String> handleException(Exception ex){
        log.error("Exception occurred : ", ex);
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({ValidationException.class})
    @ResponseBody
    public ResponseEntity<String> handleValidationException(Exception ex){
        log.error("Validation Exception occurred : ", ex);
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
    
    
    @ExceptionHandler({EmployeeServiceException.class})
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleEmployeeServiceException(EmployeeServiceException ex){
        log.error("Employee Service Exception occurred : ", ex);
        ResponseEntity<ErrorResponse> responseEntity = 
                new ResponseEntity<ErrorResponse>(new ErrorResponse(ex.getErrCode(),ex.getErrMsg(),
                        ex.getUserMsg()==null? ex.getErrMsg(): ex.getUserMsg()), HttpStatus.BAD_REQUEST);
        return responseEntity;
    }

    protected void handleRequestError(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("Invalid request error occured while validating request");
            for (ObjectError objError : bindingResult.getAllErrors()) {
                try {
                    ExceptionCodes code = ExceptionCodes
                            .valueOf(objError.getDefaultMessage());
                    throw new EmployeeServiceException(code.errCode(),code.errMsg());
                }
                catch (Exception ex){
                    log.error("Could not find the error code for this error");
                    //logging error as invalid request parameter encountered.
                    throw new EmployeeServiceException(ExceptionCodes.INVALID_REQUEST.errCode(), objError.getDefaultMessage(),
                            ExceptionCodes.INVALID_REQUEST.errMsg());
                }
            }

        }
    }
}
