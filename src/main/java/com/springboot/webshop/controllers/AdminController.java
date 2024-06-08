package com.springboot.webshop.controllers;

import com.springboot.webshop.models.Product;
import com.springboot.webshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;


    @GetMapping({"", "/"})
    public String showAdminPage() {
        return "admin/admin";
    }

    @GetMapping("/products")
    public String showProductList(Model model) {
        List<Product> products = productService.findEnableProduct();
        model.addAttribute("products", products);
        return "products/product";
    }

    @GetMapping("/disableproducts")
    public String showDisableProductList(Model model) {
        List<Product> products = productService.findDisableProduct();
        model.addAttribute("products", products);
        return "disableproducts/DisableProduct";
    }
}



