package hu.lenartl.petstore.order.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrderDetails(Long id,
                           Long petId,
                           Integer quantity,
                           LocalDateTime shipDate,
                           String status,
                           boolean complete) {
}
