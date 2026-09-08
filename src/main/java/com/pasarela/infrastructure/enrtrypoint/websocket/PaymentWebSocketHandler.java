package com.pasarela.infrastructure.enrtrypoint.websocket;

import com.pasarela.application.usecase.PaymentUseCase;
import com.pasarela.domain.model.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentWebSocketHandler implements WebSocketHandler {
    private final PaymentUseCase paymentUseCase;
    private final ObjectMapper objectMapper;
    @Override
    public List<String> getSubProtocols() {
        return WebSocketHandler.super.getSubProtocols();
    }

    @Override
    public Mono<Void> handle(WebSocketSession session) {
        return session.receive()
                .map(WebSocketMessage::getPayloadAsText)
                .flatMap(this::deserialize)
                .doOnNext(message -> log.info("message: {}", message))
                .then();
    }

    private Mono<Payment> deserialize(String message) {
        try{
            return Mono.just(objectMapper.readValue(message, Payment.class));
        }catch (Exception e){
            return Mono.error(e);
        }
    }
}
