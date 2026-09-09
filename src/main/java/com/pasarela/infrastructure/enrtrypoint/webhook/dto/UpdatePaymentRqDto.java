package com.pasarela.infrastructure.enrtrypoint.webhook.dto;

import com.pasarela.domain.enums.PaymentStatus;
import lombok.Builder;

import java.util.UUID;

@Builder
public record UpdatePaymentRqDto (
        UUID transactionId,
        PaymentStatus status,
        String description
){
}
