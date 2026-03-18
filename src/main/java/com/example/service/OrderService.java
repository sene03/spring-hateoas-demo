package com.example.service;

import com.example.dto.OrderRequest;
import com.example.dto.OrderResponse;
import com.example.model.Order;
import com.example.model.Product;
import com.example.model.User;
import com.example.repository.OrderRepository;
import com.example.repository.ProductRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    // 주문 생성
    public OrderResponse create(OrderRequest request){
        User user = userRepository.findById(request.userId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다.")); // repository에서 optional을 반환
        // service layer에서 해당 예외에 대한 처리를 해주어야함
        Product product = productRepository.findById(request.productId())
                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));;

        if(product.getStock() < request.quantity()){
            throw new RuntimeException("재고가 부족합니다.");
        }

        // 재고 차감
        product.setStock(product.getStock() - request.quantity());
        productRepository.save(product);

        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);
        order.setQuantity(request.quantity());
        order.setTotalPrice(product.getPrice() * request.quantity());

        return toResponse(orderRepository.save(order));
    }

    // 특정 유저의 주문 목록
    public List<OrderResponse> findByUserId(Long userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // 주문 단건 조회
    public OrderResponse findById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));

        return toResponse(order);
    }

    // Entity -> Response로 변환
    private OrderResponse toResponse(Order order){
        return new OrderResponse(
                order.getId(),
                order.getUser().getId(),
                order.getProduct().getId(),
                order.getProduct().getName(),
                order.getQuantity(),
                order.getTotalPrice()
        );
    }

}
