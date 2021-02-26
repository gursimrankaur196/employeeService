package com.paypal.bfs.test.employeeserv.impl.exception.customExceptions.ExceptionCodes;

public enum ExceptionCodes {
    EMPLOYEE_NOT_PRESENT("ER-0001", "Employee not present"),
    ERROR_WHILE_FETCHING_FROM_DATABASE("ER-0002", "unable to fetch transaction from database"),
    ERROR_WHILE_ADDING_TO_DATABASE("ER-0003", "Unable to add the employee in database"),
    INVALID_REQUEST("ER-0004", "Invalid Request")
    ;
    private String errCode;
    private String errMsg;
    private String userMsg;

    private ExceptionCodes(String errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    private ExceptionCodes(String errCode, String errMsg, String userMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.userMsg = userMsg;
    }

    public String errCode() {
        return this.errCode;
    }

    public String errMsg() {
        return this.errMsg;
    }

    public String userMsg() { return this.userMsg; }
}
