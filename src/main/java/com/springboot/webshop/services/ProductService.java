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
    public List<Product> findEnableProduct(String sortBy, String sortDir){
        return productsRepository.findStatusProduct(1, sortBy, sortDir);
    }

    @Override
    public List<Product> findDisableProduct(String sortBy, String sortDir){
        return productsRepository.findStatusProduct(0, sortBy, sortDir);
    }

    @Override
    public Void enableProduct(Integer id){
        return productsRepository.updateStatus(id, 1);
    }

    @Override
    public Void disableProduct(Integer id){
        return productsRepository.updateStatus(id, 0);
    }

    @Override
    public List<Product> findProductsByName(String name){
        return productsRepository.findByName(name);
    }

    @Override
    public Integer findMaxId() {
        int maxIdNumber = 0;
        List<Product> products = productsRepository.findStatusProduct(1, "id", "desc");
        maxIdNumber = products.getFirst().getId();
        // Tạo ID mới
        int newIdNumber = maxIdNumber + 1;
        return newIdNumber;
    }



}
