package com.farinia.proyectoFinal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendEmail(String titulo,String subTitulo,String mensaje,String email) {
        var message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject(titulo);
        message.setText(subTitulo+"\n"+mensaje);

        javaMailSender.send(message);
    }
}
