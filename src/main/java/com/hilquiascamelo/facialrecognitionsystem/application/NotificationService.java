//package com.hilquiascamelo.facialrecognitionsystem.application;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class NotificationService {
//
//    @Autowired
//    private JavaMailSender emailSender;
//
//    // Método para enviar notificação por email
//    public void sendEmail(String to, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        emailSender.send(message);
//    }
//
//    // Método para notificar quando uma face é reconhecida
//    public void notifyFaceRecognized(String userEmail, String userName) {
//        String subject = "Face Reconhecida!";
//        String text = String.format("Olá %s, sua face foi reconhecida com sucesso no sistema.", userName);
//        sendEmail(userEmail, subject, text);
//    }
//
//    // Método para notificar quando uma nova face é registrada
//    public void notifyFaceRegistered(String userEmail, String userName) {
//        String subject = "Nova Face Registrada!";
//        String text = String.format("Olá %s, sua nova face foi registrada com sucesso no sistema.", userName);
//        sendEmail(userEmail, subject, text);
//    }
//}