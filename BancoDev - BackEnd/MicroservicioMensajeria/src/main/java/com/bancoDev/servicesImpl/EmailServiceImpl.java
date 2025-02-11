package com.bancoDev.servicesImpl;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.bancoDev.models.Email;
import com.bancoDev.services.EmailService;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    @Override
    public void sendMail(Email email){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(email.getDestinatario());
            helper.setSubject(email.getAsunto());
    
            //Procesamiento de la plantilla de thymeleaf
            Context context = new Context();
            context.setVariable("mensaje", email.getMensaje());
            String contentHtml = templateEngine.process("email", context);
            helper.setText(contentHtml, true);
            javaMailSender.send(message);
        }
        catch (Exception e){
            throw new RuntimeException("Error al enviar el mensage: " + e.getMessage());
        }
    }

}
