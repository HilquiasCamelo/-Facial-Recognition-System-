package com.hilquiascamelo.facialrecognitionsystem.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WebSocketService {

    private static final Logger log = LoggerFactory.getLogger(WebSocketService.class);

    public String processMessage(String message) {
        // Lógica para processar a mensagem recebida
        log.info("Processing message: {}", message);

        // Exemplo de lógica: contar o número de caracteres na mensagem
        int length = message.length();

        // Exemplo de lógica: verificar se a mensagem contém uma palavra específica
        String keyword = "importante";
        boolean containsKeyword = message.contains(keyword);

        // Construir uma resposta baseada no processamento
        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append("Mensagem recebida: ").append(message)
                .append("\nNúmero de caracteres: ").append(length)
                .append("\nContém a palavra '").append(keyword).append("': ").append(containsKeyword);

        return responseBuilder.toString(); // Retorna a mensagem processada
    }
}
