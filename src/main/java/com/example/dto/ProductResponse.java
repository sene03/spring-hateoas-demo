package com.example.dto;

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
