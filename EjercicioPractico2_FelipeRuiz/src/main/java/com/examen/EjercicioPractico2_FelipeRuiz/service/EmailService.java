package com.examen.EjercicioPractico2_FelipeRuiz.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarCorreoBienvenida(String destinatario, String nombre) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatario);
        mensaje.setSubject("Bienvenido a la Plataforma de Eventos");
        mensaje.setText("Hola " + nombre + ",\n\n"
                + "Tu cuenta ha sido creada exitosamente en nuestra Plataforma de Reservas de Eventos.\n\n"
                + "¡Bienvenido!\n"
                + "Equipo de Gestión de Eventos");
        mailSender.send(mensaje);
    }
}
