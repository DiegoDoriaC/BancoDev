package com.bancoDev.MicroservicioCuentas.DTOs;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {

    private String menssage;
    private T data;
    private boolean status;

}
