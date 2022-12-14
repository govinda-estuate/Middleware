package com.est.middleware.project.estuate.Wso2.util;

public class ResponseDto {
	private int statusCode;
    private String message;
    private int errorCode;
    
    public ResponseDto() {
    }
    
    public ResponseDto(final int statusCode, final String errorMessage, final int errorCode) {
        this.statusCode = statusCode;
        this.message = errorMessage;
        this.errorCode = errorCode;
    }
    
    public int getStatusCode() {
        return this.statusCode;
    }
    
    public void setStatusCode(final int statusCode) {
        this.statusCode = statusCode;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public void setMessage(final String message) {
        this.message = message;
    }
    
    public int getErrorCode() {
        return this.errorCode;
    }
    
    public void setErrorCode(final int errorCode) {
        this.errorCode = errorCode;
    }
}


