package com.teklifver.Controller;

public class GlobalMessage
{
    private String message;

    public GlobalMessage() {
    }

    public GlobalMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}