package com.example.service;

import com.example.dto.ProductRequest;
import com.example.dto.ProductResponse;
import com.example.model.Product;
import com.example.model.User;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
// Entity -> DTO 변환 담당
public class ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    // 등록
    public ProductResponse create(ProductRequest request) {
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        Product product = new Product();
        product.setUser(user);
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setStock(request.stock());
        product.setCategory(request.category());

        Product saved = productRepository.save(product);
        return toResponse(saved);
    }

    // 전체 조회
    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // 단건 조회
    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));
        return toResponse(product);
    }

    // 수정
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));

        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setStock(request.stock());
        product.setCategory(request.category());

        return toResponse(productRepository.save(product));
    }

    // 삭제
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    // Entity → Response 변환
    private ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getUser().getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getCategory()
        );
    }
}
