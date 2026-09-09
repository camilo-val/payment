package com.pasarela.application.usecase;

import com.pasarela.application.command.PaymentCommand;
import com.pasarela.application.command.ProcessPaymentCommand;
import com.pasarela.application.mapper.PaymentMapper;
import com.pasarela.application.port.PaymentPort;
import com.pasarela.application.port.PaymentEventPublisherPort;
import com.pasarela.domain.exceptions.BusinessExceptions;
import com.pasarela.domain.exceptions.BusinessTransactionalExceptions;
import com.pasarela.domain.model.Payment;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Component
public class PaymentUseCase {
    private final PaymentPort paymentPort;
    private final PaymentEventPublisherPort eventPublisher;
    private final PaymentMapper mapper;

    public Mono<Payment> processPayment(PaymentCommand payment) {
        return paymentPort.getPaymentByOrderId(payment.orderId())
                .hasElement()
                .flatMap(exist -> {
                    if (exist) {
                        return Mono.error(new BusinessExceptions(BusinessTransactionalExceptions.TRANSACTION_ALREADY_EXISTS));
                    }
                    return paymentPort.processPayment(Payment.create(UUID.randomUUID(),payment.amount(),
                            payment.currency(), payment.description(),payment.orderId()))
                            .flatMap(savedPayment -> {
                                ProcessPaymentCommand event = mapper.toCommand(savedPayment);
                                return eventPublisher.publish(event).thenReturn(savedPayment);
                            });
               });
    }

//    private ProcessPaymentCommand toCommand(Payment payment) {
//        return ProcessPaymentCommand.builder()
//                .amount(payment.getAmount())
//                .currency(payment.getCurrency())
//                .orderID(payment.getOrderId())
//                .transactionId(payment.getTransactionId())
//                .message(payment.getDescription())
//                .build();
//    }
}
