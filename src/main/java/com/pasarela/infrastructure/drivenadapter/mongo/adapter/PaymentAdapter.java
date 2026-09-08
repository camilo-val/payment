package com.pasarela.infrastructure.drivenadapter.mongo.adapter;

import com.pasarela.application.port.PaymentPort;
import com.pasarela.domain.model.Payment;
import com.pasarela.infrastructure.drivenadapter.mongo.data.PaymentData;
import com.pasarela.infrastructure.drivenadapter.mongo.mapper.MapperAdapter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@AllArgsConstructor
public class PaymentAdapter implements PaymentPort {

    private final PaymentData paymentData;
    private final MapperAdapter mapperAdapter;

    @Override
    public Mono<Payment> processPayment(Payment payment) {
        return paymentData.save(mapperAdapter.toAdapter(payment))
                .map(mapperAdapter::toDomain);
    }

    @Override
    public Mono<Payment> cancelPayment(Payment payment) {
        return null;
    }

    @Override
    public Mono<Payment> getPaymentById(UUID id) {
        return paymentData.findById(id).map(mapperAdapter::toDomain);
    }

    @Override
    public Mono<Payment> getPaymentByOrderId(UUID orderId) {
        return paymentData.findByOrderId(orderId).map(mapperAdapter::toDomain);
    }
}
