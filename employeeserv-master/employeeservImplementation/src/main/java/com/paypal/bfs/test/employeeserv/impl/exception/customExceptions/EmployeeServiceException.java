package com.paypal.bfs.test.employeeserv.impl.exception.customExceptions;

import com.paypal.bfs.test.employeeserv.impl.exception.customExceptions.ExceptionCodes.ExceptionCodes;
import lombok.Data;

/**
 * @author gursimran.kaur
 */

@Data
public class EmployeeServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private String errCode;
    private String errMsg;
    private String userMsg;
    private String metadata;

    public EmployeeServiceException(String errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public EmployeeServiceException(String errCode, String errMsg, String userMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.userMsg = userMsg;
    }

    public EmployeeServiceException(String errCode, String message, Throwable cause) {
        super(message, cause);
        this.errCode = errCode;
        this.errMsg = message;
    }

    public EmployeeServiceException(ExceptionCodes code){
        super(code.errMsg());
        this.errCode = code.errCode();
        this.errMsg = code.errMsg();
    }

    public EmployeeServiceException(String errCode, String errMsg, String userMsg, String metadata) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.userMsg = userMsg;
        this.metadata = metadata;
    }
    public EmployeeServiceException(){};

}
