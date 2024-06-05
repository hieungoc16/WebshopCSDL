package com.springboot.webshop.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import com.springboot.webshop.models.Brand;
import com.springboot.webshop.repositories.BrandsRepository;

import java.util.List;

@Service
public class BrandService implements IBrandService {
    @Autowired
    private BrandsRepository brandsRepository;

    @Override
    public List<Brand> getAllBrands() {
        return brandsRepository.findAll();
    }

    @Override
    public Brand getBrand(String id) {
        return null;
    }

    @Override
    public void deleteBrand(String id) {
        brandsRepository.deleteById(id);
    }

    @Override
    public Page<Brand> getBrandsByPage(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public void saveBrand(Brand brand){
        brandsRepository.save(brand);
    }

}
