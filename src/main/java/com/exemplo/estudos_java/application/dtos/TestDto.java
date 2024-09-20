package com.exemplo.estudos_java.application.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestDto {
    @NotNull(message = "Value cannot be null")
    @NotBlank(message = "The name cannot be blank")
    private String name;

    @Email(message = "Email is invalid")
    private String email;

    @Min(value = 18, message = "The age must be 18 or higher")
    private Integer age;
}
