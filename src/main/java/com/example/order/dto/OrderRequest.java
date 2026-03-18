package com.example.order.dto;

public record OrderRequest(
        Long userId,
        Long productId,
        int quantity
) {
}
