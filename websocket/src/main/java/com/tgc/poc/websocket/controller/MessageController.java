package com.tgc.poc.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @MessageMapping("/broadcast")
    @SendTo("/topic/reply")
    public String broadcastMessage(@Payload String message) {
        return "You have received a message: " + message;
    }
}
