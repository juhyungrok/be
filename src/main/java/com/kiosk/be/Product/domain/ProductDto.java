package com.kiosk.be.Product.domain;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Table(name = "product")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Long productId; // 상품번호

    private String name; // 이름
    private int price; // 가격
    private boolean hasice; // 아이스인지 선택
    private boolean hashot; // 핫인지 선택
    private int quantity; // 장바구니에 담긴 수량

}
