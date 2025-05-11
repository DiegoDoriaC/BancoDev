package com.bancoDev.models;

import lombok.Data;

@Data
public class Email {

    private String destinatario;
    private String asunto;
    private String mensaje;

}
