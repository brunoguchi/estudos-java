package com.exemplo.estudos_java.application.exceptions;

public class InternalErrorException extends RuntimeException {
    public InternalErrorException(String identifier) {
        super("Deu bereguejohnson ai. Identifier: " + identifier);
    }
}
