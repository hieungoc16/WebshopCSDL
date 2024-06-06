package com.springboot.webshop.services;

import com.springboot.webshop.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAllProduct();

    List<Product> findEnableProduct();

    List<Product> findDisableProduct();

    Void enableProduct(Integer id);

    Void disableProduct(Integer id);
}
