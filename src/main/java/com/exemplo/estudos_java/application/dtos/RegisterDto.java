package com.exemplo.estudos_java.application.dtos;

import com.exemplo.estudos_java.domain.enums.UserRole;

public record RegisterDto(String login, String password, UserRole role) {
}
