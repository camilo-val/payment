package com.pasarela.application.command;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ProcessPaymentCommand(
        UUID orderID,
        UUID transactionId,
        BigDecimal amount,
        String currency,
        String message
){
}
