package com.exemplo.estudos_java.application.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestForMongoDto {
    private String identify;
    private String name;
}
