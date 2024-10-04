package com.exemplo.estudos_java.application.controllers;

import com.exemplo.estudos_java.application.dtos.AuthDto;
import com.exemplo.estudos_java.application.dtos.LoginResponseDto;
import com.exemplo.estudos_java.application.dtos.RegisterDto;
import com.exemplo.estudos_java.application.services.TokenService;
import com.exemplo.estudos_java.domain.models.Users;
import com.exemplo.estudos_java.infra.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthDto dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.login(), dto.password());
        var auth = this.authenticationManager.authenticate((usernamePassword));
        var token = tokenService.generateToken((Users) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto dto) {
        if (this.userRepository.findByLogin(dto.login()) != null)
            return ResponseEntity.badRequest().build();

        var encryptedPassword = new BCryptPasswordEncoder().encode(dto.password());
        var user = new Users(dto.login(), encryptedPassword, dto.role());

        this.userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
