package com.example.websocketdemo.config;

<<<<<<< HEAD
import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompReactorNettyCodec;
import org.springframework.messaging.tcp.reactor.ReactorNettyTcpClient;
=======
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
>>>>>>> 01f171c8d3d89d631dae8ceffdb85b8a14e990c9
import org.springframework.web.socket.config.annotation.*;

/**
 * Created by rajeevkumarsingh on 24/07/17.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
<<<<<<< HEAD
        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
        
=======
        registry.addEndpoint("/ws").withSockJS();
>>>>>>> 01f171c8d3d89d631dae8ceffdb85b8a14e990c9
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
<<<<<<< HEAD
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
            .setClientLogin("test")
            .setClientPasscode("Adm1nistrator!")
            .setTcpClient(client);
    }

=======
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");   // Enables a simple in-memory broker


        //   Use this for enabling a Full featured broker like RabbitMQ

        /*
        registry.enableStompBrokerRelay("/topic")
                .setRelayHost("localhost")
                .setRelayPort(61613)
                .setClientLogin("guest")
                .setClientPasscode("guest");
        */
    }
>>>>>>> 01f171c8d3d89d631dae8ceffdb85b8a14e990c9
}
