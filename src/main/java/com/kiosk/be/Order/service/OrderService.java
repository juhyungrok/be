package com.kiosk.be.order.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kiosk.be.order.entity.Order;
import com.kiosk.be.order.entity.OrderDetail;
import com.kiosk.be.order.repository.OrderDetailRepository;
import com.kiosk.be.order.repository.OrderRepository;
import com.kiosk.be.order.requestdto.OrderDetailDTO;
import com.kiosk.be.order.requestdto.OrderRequestDTO;
import com.kiosk.be.order.responsedto.OrderDetailResponseDTO;
import com.kiosk.be.order.responsedto.OrderResponseDTO;
import com.kiosk.be.product.entity.Product;
import com.kiosk.be.product.repository.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    public jakarta.persistence.EntityManager entityManager;

    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(this::convertToOrderResponseDTO)
                .collect(Collectors.toList());
    }

    @SuppressWarnings("null")
    public OrderResponseDTO getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            return convertToOrderResponseDTO(order);
        } else {
            return null;
        }
    }

    @SuppressWarnings("null")
    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequest) {
        // DTO로부터 주문 정보 가져오기
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setOrderStatus("IN_PROGRESS");

        // DTO로부터 주문 상세 정보 가져오기
        List<OrderDetailDTO> orderDetailsDTO = orderRequest.getOrderDetails();
        List<OrderDetail> orderDetails = new ArrayList<>();

        // 주문 상세 정보 생성 및 설정
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderDetailDTO detailDTO : orderDetailsDTO) {
            if (detailDTO.getProductId() == null) {
                throw new IllegalArgumentException("Product ID must not be null");
            }

            Product product = productRepository.findById(detailDTO.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(detailDTO.getQuantity());
            orderDetail.setProduct(product);
            orderDetail.setTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(detailDTO.getQuantity())));
            orderDetail.setOrderDetailId(order.getOrderId());
            orderDetail.setOrder(order);
            totalAmount = totalAmount.add(orderDetail.getTotalPrice()); // 각 상품의 총 가격 더하기

            orderDetails.add(orderDetail);
        }

        order.setOrderDetails(orderDetails);
        order.setTotalAmount(totalAmount);

        // 주문 및 주문 상세 정보 저장
        orderRepository.save(order);

        // DTO로 변환 후 반환
        return convertToOrderResponseDTO(order);
    }

    // 주문 생성 후 응답을 DTO로 변환하는 메서드
    private OrderResponseDTO convertToOrderResponseDTO(Order order) {
        OrderResponseDTO responseDTO = new OrderResponseDTO();
        responseDTO.setOrderId(order.getOrderId());
        responseDTO.setOrderDate(order.getOrderDate());
        responseDTO.setOrderStatus(order.getOrderStatus());
        responseDTO.setTotalAmount(order.getTotalAmount());

        List<OrderDetailResponseDTO> orderDetailDTOs = order.getOrderDetails().stream()
                .map(this::convertToOrderDetailResponseDTO)
                .collect(Collectors.toList());

        responseDTO.setOrderDetails(orderDetailDTOs);

        return responseDTO;
    }

    private OrderDetailResponseDTO convertToOrderDetailResponseDTO(OrderDetail orderDetail) {
        OrderDetailResponseDTO responseDTO = new OrderDetailResponseDTO();
        responseDTO.setOrderDetailId(orderDetail.getOrderDetailId());
        responseDTO.setProductId(orderDetail.getProduct().getProductId());
        responseDTO.setProductName(orderDetail.getProduct().getProductName()); // 상품명 추가
        responseDTO.setQuantity(orderDetail.getQuantity());
        responseDTO.setTotalPrice(orderDetail.getTotalPrice());

        return responseDTO;
    }

    @SuppressWarnings("null")
    public void delete(Long orederId) {
        orderRepository.deleteById(orederId);
    }

}
