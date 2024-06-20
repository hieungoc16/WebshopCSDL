package com.springboot.webshop.services;

import com.springboot.webshop.models.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<Product> findAllProduct();

    List<Product> findEnableProduct(String sortBy, String sortDir);

    List<Product> findDisableProduct(String sortBy, String sortDir);

    Void enableProduct(Integer id);

    Void disableProduct(Integer id);

    List<Product> findProductsByName(String name);

    Integer findMaxId();
}
