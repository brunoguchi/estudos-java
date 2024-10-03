package com.exemplo.estudos_java.application.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String identifier) {
        super("Deu ruim filhão, achei não. Identifier: " + identifier);
    }
}
