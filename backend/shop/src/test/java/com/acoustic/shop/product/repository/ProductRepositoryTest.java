package com.acoustic.shop.product.repository;

import com.acoustic.shop.product.constant.ProdSellStatus;
import com.acoustic.shop.product.entity.Product;
import com.acoustic.shop.product.entity.QProduct;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

import static com.acoustic.shop.product.entity.QProduct.product;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    EntityManager em;

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("상품저장 테스트")
    public void createProductTest() {
        Product product = new Product();

        product.setProdName("테스트 상품");
        product.setProdDesc("");
        product.setProdPrice(10000);
        product.setProdStock(5);
        product.setImgUrl("");
        product.setProdCreated(LocalDateTime.now());
        product.setProdUpdated(LocalDateTime.now());
        product.setProdStatus(ProdSellStatus.SELL);

        Product saveProd = productRepository.save(product);

        System.out.println(saveProd.toString());
    }

    @Test
    @DisplayName("상품리스트 생성 테스트")
    public void createProdList() {
        for (int i=1 ; i<=10 ; i++){
            Product product = new Product();
            product.setProdName("테스트상품" + i);
            product.setProdDesc("테스트상품설명" + i);
            product.setProdPrice(10000+i);
            product.setProdStock(100-1);
            product.setProdCreated(LocalDateTime.now());
            product.setProdUpdated(LocalDateTime.now());
            product.setProdStatus(ProdSellStatus.SELL);

            Product savedProd = productRepository.save(product);
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByProdNameTest() {
        this.createProdList();
        List<Product> productList = productRepository.findByProdName("테스트상품1");
        for (Product product : productList){
            System.out.println(product.toString());
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByProdNameTestOrProdDesc() {
        this.createProdList();
        List<Product> productList = productRepository.findByProdNameOrProdDesc("테스트상품1", "테스트상품설명5");
        for (Product product : productList) {
            System.out.println(product.toString());
        }
    }

    @Test
    @DisplayName("Native 쿼리")
    public void findByProdDescNativeTest() {
        createProdList();

        List<Product> productList = productRepository.findByProdDescNative("테스트");

        for (Product product : productList) {
            System.out.println(product);
        }
    }

    @Test
    @DisplayName("queryDSL 테스트")
    public void querydslTest() {
        createProdList();

        JPAQueryFactory queryFactory = new JPAQueryFactory(em);

        List<Product> list = queryFactory
                .select(product)
                .from(product)
                .where(product.prodStatus.eq(ProdSellStatus.SELL))
                .orderBy(product.prodPrice.desc())
                .fetch();

        for (Product product : list) {
            System.out.println(product);
        }

    }

    public void createProdList2() {
        for (int i = 1; i <= 5 ; i++){
            Product product = new Product();

            product.setProdName("테스트상품" + i);
            product.setProdDesc("테스트상품설명" + i);
            product.setProdPrice(10000+i);
            product.setProdStock(100);
            product.setProdCreated(LocalDateTime.now());
            product.setProdUpdated(LocalDateTime.now());
            product.setProdStatus(ProdSellStatus.SELL);

            Product savedProd = productRepository.save(product);
        }

        for (int i = 6; i <= 10 ; i++){
            Product product = new Product();

            product.setProdName("테스트상품" + i);
            product.setProdDesc("테스트상품설명" + i);
            product.setProdPrice(10000+i);
            product.setProdStock(0);
            product.setProdCreated(LocalDateTime.now());
            product.setProdUpdated(LocalDateTime.now());
            product.setProdStatus(ProdSellStatus.SOLD_OUT);

            Product savedProd = productRepository.save(product);
        }
    }

    @Test
    @DisplayName("queryDSL 테스트2")
    public void querydslTest2() {
        createProdList2();

        String prodDesc = "테스트";
        int prodPrice = 10003;
        String prodSellState = "SELL";

        QProduct product = QProduct.product;

        BooleanBuilder builder = new BooleanBuilder();

        builder.and(product.prodDesc.like("%" + prodDesc + "%"));
        builder.and(product.prodPrice.gt(prodPrice));

        if (StringUtils.equals(prodSellState, ProdSellStatus.SELL)) {
            builder.and(product.prodStatus.eq(ProdSellStatus.SELL));
        }

        Pageable pageable = PageRequest.of(0, 5);

        Page<Product> findAll = productRepository.findAll(builder, pageable);

        System.out.println("전체 개수 : " + findAll.getTotalElements());

        List<Product> content = findAll.getContent();
        for (Product product1 : content) {
            System.out.println(product1);
        }

    }

    @Test
    void test() {
        Product product = new Product();
        System.out.println(product);
    }
}
