package com.springboot.webshop.repositories;

import com.springboot.webshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, String> {
    @Query("SELECT product FROM Product product WHERE (product.status = ?1)" + "ORDER BY product.id ASC")
    public List<Product> findStatusProduct(Integer product_status);

    @Transactional
    @Modifying
    @Query("UPDATE Product product SET product.status = ?2 WHERE product.id = ?1")
    public Void updateStatus(String product_id, Integer product_status);

}


