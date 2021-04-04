package com.gridnine.testing.service.exceptions;

public class ServiceException extends RuntimeException {
    public ServiceException(String string) {
        super(string);
    }

    public ServiceException() {
        super();
    }
}