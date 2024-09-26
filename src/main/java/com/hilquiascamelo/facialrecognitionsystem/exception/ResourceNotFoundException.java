package com.hilquiascamelo.facialrecognitionsystem.exception;

public class ResourceNotFoundException extends RuntimeException {


    public ResourceNotFoundException() {
        super("Recurso não encontrado");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }


    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
