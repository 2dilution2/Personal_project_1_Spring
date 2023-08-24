package com.acoustic.shop.product.repository;

import com.acoustic.shop.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> ,
        QuerydslPredicateExecutor<Product> {

    List<Product> findByProdName(String ProductName);

    List<Product> findByProdNameOrProdDesc(String ProductName, String ProdDesc);

    @Query(value = "SELECT p FROM Product p WHERE p.prodDesc LIKE %:productDesc%"
            + "order by p.prodPrice asc")
    List<Product> findByProdDescNative(String keyword);


}
