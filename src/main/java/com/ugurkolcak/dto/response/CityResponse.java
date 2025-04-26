package com.ugurkolcak.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CityResponse {
    private String id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String code;

    private String name;
}
