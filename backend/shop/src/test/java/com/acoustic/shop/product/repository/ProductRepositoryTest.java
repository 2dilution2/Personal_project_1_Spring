package com.acoustic.shop.product.repository;

import com.acoustic.shop.product.constant.ProdSellStatus;
import com.acoustic.shop.product.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("상품저장 테스트")
    public void createProductTest() {
        Product product = new Product();

        product.setCategoryId(123);
        product.setProdName("테스트 상품");
        product.setProdDesc("");
        product.setProdPrice(10000);
        product.setProdStock(5);
        product.setImgUrl("");
        product.setProdCreated(LocalDateTime.now());
        product.setProdUpdated(LocalDateTime.now());

        Product saveProd = productRepository.save(product);

        System.out.println(saveProd.toString());
    }

    @Test
    @DisplayName("상품리스트 생성 테스트")
    public void createProdList() {
        for (int i=1 ; i<=10 ; i++){
            Product product = new Product();
            product.setProdName("테스트상품" + i);
            product.setProdName("테스트상품설명" + i);
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
    void test() {
        Product product = new Product();
        System.out.println(product);
    }
}
