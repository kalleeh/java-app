package com.example.websocketdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompReactorNettyCodec;
import org.springframework.messaging.simp.stomp.StompBrokerRelayMessageHandler;
import org.springframework.messaging.tcp.reactor.ReactorNettyTcpClient;
import org.springframework.web.socket.config.annotation.*;

/**
 * Created by rajeevkumarsingh on 24/07/17.
 */
@Configuration
//@EnableWebSocketMessageBroker
//public class WebSocketConfig extends DelegatingWebSocketMessageBrokerConfiguration {
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        ReactorNettyTcpClient<byte[]> client = new ReactorNettyTcpClient<>(builder -> {
            builder.port(61614)
                .host("b-d2a3dd17-48b5-4840-b83b-39db24a55f8f-1.mq.eu-west-1.amazonaws.com")
                .secure();
        }, new StompReactorNettyCodec());

        registry.setApplicationDestinationPrefixes("/app");

        // registry.enableSimpleBroker("/topic");   // Enables a simple in-memory broker
        //   Use this for enabling a Full featured broker like RabbitMQ
        registry.enableStompBrokerRelay("/topic")
                .setClientLogin("test")
                .setClientPasscode("Adm1nistrator!")
                .setTcpClient(client);
    }
}
