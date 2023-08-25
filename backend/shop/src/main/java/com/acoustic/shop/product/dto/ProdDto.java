package com.acoustic.shop.product.dto;

import com.acoustic.shop.category.entity.Category;
import com.acoustic.shop.product.constant.ProdSellStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProdDto {

    private Long prodId;               // 상품번호

    private Category categoryId;       // 카테고리 번호

    private String prodName;           // 상품명

    private String prodDesc;           // 상품설명

    private int prodPrice;             // 상품가격

    private int prodStock;             // 상품재고

    private String imgUrl;             // 상품이미지링크

    private LocalDateTime prodCreated; // 생성일자

    private LocalDateTime prodUpdated; // 수정일자

    private ProdSellStatus prodStatus;  // 상품 상태
}
