package com.paypal.bfs.test.employeeserv.api.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author gursimran.kaur
 */

@Data
public class ErrorResponse implements Serializable {

    private String errorCode;
    private String errorMessage;
    private String userMessage;


    public ErrorResponse(String errorCode, String errorMessage, String userMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.userMessage = userMessage;
    }

    public ErrorResponse(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.userMessage = errorMessage;
    }

    public ErrorResponse(){}

}
