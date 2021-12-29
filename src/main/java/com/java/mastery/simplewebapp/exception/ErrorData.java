package com.java.mastery.simplewebapp.exception;

import lombok.Data;

@Data
public class ErrorData {
    private String message;
    private Object data;
}
