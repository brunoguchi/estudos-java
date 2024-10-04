package com.exemplo.estudos_java.application.dtos;

import org.springframework.web.multipart.MultipartFile;

public record PayloadWithImageDto(String id, String name, String date) {
}
