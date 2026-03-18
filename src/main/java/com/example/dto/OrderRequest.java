package com.example.dto;

public record OrderRequest(
        Long userId,
        Long productId,
        int quantity
) {
}
