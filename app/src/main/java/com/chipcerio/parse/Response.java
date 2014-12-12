package com.chipcerio.parse;

public class Response {
    private int statusCode;
    private boolean isSuccess;
    private String messageBody;

    public Response() {
        statusCode = 0;
        isSuccess = false;
        messageBody = "";
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

}
