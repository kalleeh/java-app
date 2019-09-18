package com.example.websocketdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

/**
 * Created by rajeevkumarsingh on 24/07/17.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");

        // registry.enableSimpleBroker("/topic");   // Enables a simple in-memory broker
        //   Use this for enabling a Full featured broker like RabbitMQ
        registry.enableStompBrokerRelay("/topic")
                .setRelayHost("b-b62b1307-b845-4a49-bda1-ee4a2c29d2f1-1.mq.eu-west-1.amazonaws.com")
                .setRelayPort(61617)
                .setClientLogin("test")
                .setClientPasscode("Adm1nistrator!");
    }
}
