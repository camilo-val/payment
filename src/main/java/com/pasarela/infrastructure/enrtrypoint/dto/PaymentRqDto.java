package com.pasarela.infrastructure.enrtrypoint.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record PaymentRqDto(
        BigDecimal amount,
        String currency,
        String description,
        UUID orderId)
{
}
