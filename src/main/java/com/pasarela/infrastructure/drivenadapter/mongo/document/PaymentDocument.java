package com.pasarela.infrastructure.drivenadapter.mongo.document;

import com.pasarela.domain.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@AllArgsConstructor
@Getter
@Document(collection = "payment")
public class PaymentDocument {
    private UUID id;
    private UUID transactionId;
    private BigDecimal amount;
    private String currency;
    private PaymentStatus status;
    private String description;
    private UUID orderId;
    private Instant createdAt;
    private Instant updatedAt;
}
