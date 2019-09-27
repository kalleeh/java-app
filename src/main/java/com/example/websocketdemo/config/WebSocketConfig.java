package com.example.websocketdemo.config;

import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompReactorNettyCodec;
import org.springframework.messaging.tcp.reactor.ReactorNettyTcpClient;
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
        final String host = "b-4dc5ab16-0521-4a0c-8f2b-eac112152088-1.mq.eu-west-1.amazonaws.com";
        final int port = 61614;

        ReactorNettyTcpClient<byte[]> client = new ReactorNettyTcpClient<>(builder -> builder.secure(
                        spec -> {
                            spec.sslContext(SslContextBuilder.forClient());
                        }
                )
                .host(host)
                .port(port), new StompReactorNettyCodec()
        );

        registry.setApplicationDestinationPrefixes("/app");

        registry.enableStompBrokerRelay("/topic")
                .setSystemLogin("test")
                .setSystemPasscode("Adm1nistrator!")
                .setTcpClient(client);
    }

}
