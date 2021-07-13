package com.example.community.exception;

public class CustomException extends RuntimeException {
    String message;
    Integer code;

    @Override
    public String getMessage() {
        return message;
    }
   public Integer getCode(){
        return code;
   }
    public CustomException(ICustomErroeCode customErrorCode) {

        this.message = customErrorCode.getMessage();
        this.code=customErrorCode.getcode();
    }
}
