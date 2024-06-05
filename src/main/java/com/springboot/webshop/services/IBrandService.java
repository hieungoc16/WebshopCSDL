package com.springboot.webshop.services;

import com.springboot.webshop.models.Brand;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IBrandService {
    List<Brand> getAllBrands();

    Brand getBrand(String id);

    void deleteBrand(String id);

    Page<Brand> getBrandsByPage(int pageNo, int pageSize);

    void saveBrand(Brand brand);
}
