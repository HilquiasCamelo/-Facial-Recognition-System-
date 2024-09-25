package com.hilquiascamelo.facialrecognitionsystem.web;

import com.hilquiascamelo.facialrecognitionsystem.application.WebSocketService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ws") // Endpoint base para WebSocket
public class WebSocketController {

    private final WebSocketService webSocketService;

    @Autowired
    public WebSocketController(WebSocketService webSocketService) {
        this.webSocketService = webSocketService;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public String sendMessage(String message) {
        String response = webSocketService.processMessage(message);
        return response; // Retorna a resposta do serviço
    }
}
