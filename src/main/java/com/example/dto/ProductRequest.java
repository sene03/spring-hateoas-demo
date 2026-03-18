package com.example.dto;

public record ProductRequest(
        Long userId,
        String name,
        String description,
        int price,
        int stock,
        String category
) { // record: DTO를 간결하게 정의하기 위한 타입
    // 컴파일 시 생성자, getter, setter, ... 메서드 자동 생성
}
