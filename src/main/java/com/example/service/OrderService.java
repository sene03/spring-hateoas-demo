//package com.example.service;
//
//import com.example.model.Order;
//import com.example.model.Product;
//import com.example.model.User;
//import com.example.repository.OrderRepository;
//import com.example.repository.ProductRepository;
//import com.example.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class OrderService {
//
//    private final OrderRepository orderRepository;
//    private final UserRepository userRepository;
//    private final ProductRepository productRepository;
//
//    // 주문 생성
//    public Order create(Long userId, Long productId, int quantity){
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다.")); // repository에서 optional을 반환
//        // service layer에서 해당 예외에 대한 처리를 해주어야함
//        Product product = productRepository.findById(productId)
//                .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));;
//
//        if(product.getStock()<quantity){
//            throw new RuntimeException("재고가 부족합니다.");
//        }
//
//        // 재고 차감
//        product.setStock(product.getStock() - quantity);
//        productRepository.save(product);
//
//        Order order = new Order();
//        order.setUser(user);
//        order.setProduct(product);
//        order.setQuantity(quantity);
//        order.setTotalPrice(product.getPrice() * quantity);
//
//        return orderRepository.save(order);
//    }
//
//    // 특정 유저의 주문 목록
//    public List<Order> findByUserId(Long userId) {
//        return orderRepository.findByUserId(userId);
//    }
//
//    // 주문 단건 조회
//    public Order findById(Long id) {
//        return orderRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다."));
//    }
//
//}
