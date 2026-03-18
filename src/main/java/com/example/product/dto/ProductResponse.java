package com.example.product.dto;

public record ProductResponse(
        Long id,
        Long userId,
        String name,
        String description,
        int price,
        int stock,
        String category
) {
}
