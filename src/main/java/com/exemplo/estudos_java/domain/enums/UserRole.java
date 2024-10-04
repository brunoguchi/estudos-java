package com.exemplo.estudos_java.domain.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("admin"),
    CONSULTANT("consultant"),
    USER("user");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }
}
