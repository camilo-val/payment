package com.pasarela.application.mapper;

import com.pasarela.application.command.ProcessPaymentCommand;
import com.pasarela.domain.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(target = "orderId", source = "orderId")
    @Mapping(target = "transactionId", source = "transactionId")
    @Mapping(target = "amount", source = "amount")
    @Mapping(target = "currency", source = "currency")
    @Mapping(target = "message", source = "description")
    ProcessPaymentCommand toCommand(Payment payment);
}
