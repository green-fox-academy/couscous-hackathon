package com.greenfoxacademy.webshop.exception;

public class MissingCartIdException extends CartException{
    public MissingCartIdException(String message) {
        super(message);
    }
}
