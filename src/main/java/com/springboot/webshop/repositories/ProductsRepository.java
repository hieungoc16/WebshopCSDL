package com.springboot.webshop.repositories;

import com.springboot.webshop.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT product FROM Product product WHERE (product.status = :status)" +
            "ORDER BY " +
            "CASE WHEN :sortBy = 'price' AND :sortDir = 'asc' THEN product.price END ASC, " +
            "CASE WHEN :sortBy = 'price' AND :sortDir = 'desc' THEN product.price END DESC, " +
            "CASE WHEN :sortBy = 'name' AND :sortDir = 'asc' THEN product.name END ASC, " +
            "CASE WHEN :sortBy = 'name' AND :sortDir = 'desc' THEN product.name END DESC, " +
            "CASE WHEN :sortBy = 'id' AND :sortDir = 'asc' THEN product.id END ASC, " +
            "CASE WHEN :sortBy = 'id' AND :sortDir = 'desc' THEN product.id END DESC")
    public List<Product> findStatusProduct(@Param("status") Integer product_status,
                                           @Param("sortBy") String sortBy,
                                           @Param("sortDir") String sortDir);

    @Transactional
    @Modifying
    @Query("UPDATE Product product SET product.status = ?2 WHERE product.id = ?1")
    public Void updateStatus(int product_id, Integer product_status);


    @Query("SELECT product FROM Product product WHERE (product.name LIKE %:name%)" + "ORDER BY product.id ASC")
    public List<Product> findByName(@Param("name") String name);

    @Query("SELECT p FROM Product p WHERE p.status = ?1 ORDER BY p.id DESC LIMIT 1")
    Product findMaxId(String status);
}


