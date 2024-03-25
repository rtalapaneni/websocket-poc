package com.tgc.poc.websocket.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MessageController {
    @MessageMapping("/broadcast")
    @SendTo("/topic/reply")
    public String broadcastMessage(@Payload String message) {
        log.info("Inside broadcastMessage - {}", message);
        return "You have received a message: " + message;
    }

    @MessageMapping("/user-message")
    @SendToUser("/queue/reply")
    public String sendBackToUser(@Payload String message, @Header("simpSessionId") String sessionId) {
        log.info("Inside sendBackToUser - {}, {}", message, sessionId);
        return "Only you have received this message: " + message;
    }
}
