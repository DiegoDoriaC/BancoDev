package com.bancoDev.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {

    private String message;
    private T data;
    private boolean status;

}
