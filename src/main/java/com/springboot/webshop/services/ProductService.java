package com.springboot.webshop.services;

import com.springboot.webshop.models.Product;
import com.springboot.webshop.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<Product> findAllProduct() {
        return productsRepository.findAll();
    }

    @Override
    public List<Product> findEnableProduct(){
        return productsRepository.findStatusProduct(1);
    }

}
