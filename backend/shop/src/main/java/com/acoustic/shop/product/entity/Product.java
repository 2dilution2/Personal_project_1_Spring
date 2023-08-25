package com.acoustic.shop.product.entity;

import com.acoustic.shop.category.entity.Category;
import com.acoustic.shop.product.constant.ProdSellStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prodId;               // 상품번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="categoryId")
    private Category categoryId;           // 카테고리 번호

    @Column(nullable = false)
    private String prodName;           // 상품명

    @Lob
    private String prodDesc;           // 상품설명

    @Column(nullable = false)
    private int prodPrice;             // 상품가격

    @Column(nullable = false)
    private int prodStock;             // 상품재고

    private String imgUrl;             // 상품이미지링크

    private LocalDateTime prodCreated; // 생성일자

    private LocalDateTime prodUpdated; // 수정일자

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProdSellStatus prodStatus;  // 상품 상태
}
