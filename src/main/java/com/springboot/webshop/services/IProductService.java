package com.springboot.webshop.services;

import com.springboot.webshop.models.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    List<Product> findAllProduct();

    List<Product> findEnableProduct();

    List<Product> findDisableProduct();

    Void enableProduct(Integer id);

    Void disableProduct(Integer id);

    List<Product> findProductsByName(String name);

}
