package com.exemplo.estudos_java.application.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestMessageDto {
    private String QueueName;
    private String Message;
}
