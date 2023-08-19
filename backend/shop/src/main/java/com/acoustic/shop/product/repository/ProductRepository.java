package com.acoustic.shop.product.repository;

import com.acoustic.shop.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByProdName(String ProductName);

    List<Product> findByProdNameOrProdDesc(String ProductName, String ProdDesc);

}
