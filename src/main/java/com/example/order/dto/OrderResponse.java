package com.example.order.dto;

public record OrderResponse(
        Long id,
        Long userId,
        Long productId,
        String productName,
        int quantity,
        int totalPrice
) {
}
