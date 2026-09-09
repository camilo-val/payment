package com.pasarela.infrastructure.enrtrypoint;

import com.pasarela.application.command.PaymentCommand;
import com.pasarela.infrastructure.enrtrypoint.websocket.dto.PaymentRqDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WebSocketMapper {
    default PaymentCommand toDomain(PaymentRqDto request){
        return PaymentCommand.builder()
                .orderId(request.orderId())
                .description(request.description())
                .amount(request.amount())
                .currency(request.currency())
                .build();
    };
}
