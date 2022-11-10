package com.mycompany.myapp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResponsDTO<T> {

    private boolean access;

    private String message;

    private T data;
}
