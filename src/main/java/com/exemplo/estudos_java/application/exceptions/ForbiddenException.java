package com.exemplo.estudos_java.application.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String identifier) {
        super("Deu bereguejohnson ai. Identifier: " + identifier);
    }
}
