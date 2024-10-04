package com.exemplo.estudos_java.infra.security;

import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {

    public boolean hasAdminRole() {
        return this.hasRole("ROLE_ADMIN");
    }

    public boolean hasConsultantRole() {
        return this.hasRole("ROLE_CONSULTANT");
    }

    public boolean hasUserRole() {
        return this.hasRole("ROLE_USER");
    }

    private boolean hasRole(String role) {
        return org.springframework.security.core.context.SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role));
    }
}