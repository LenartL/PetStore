package hu.lenartl.petstore.order.dto;

import java.time.LocalDateTime;

public record OrderCommand(Long petId,
                           Integer quantity,
                           LocalDateTime shipDate,
                           String status,
                           boolean complete) {
}
