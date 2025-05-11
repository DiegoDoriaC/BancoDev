package com.bancoDev.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bancoDev.models.Email;
import com.bancoDev.services.EmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mensajeria")
public class EmailController {

    private final EmailService _emailService;

    @PostMapping("/enviar")
    public ResponseEntity<String> sendEmail(@RequestBody Email email){
        _emailService.sendMail(email);
        return new ResponseEntity<>("Correo enviardo exitosamente", HttpStatus.OK);
    }

}
