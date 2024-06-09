package com.springboot.webshop.controllers;

import com.springboot.webshop.models.Product;
import com.springboot.webshop.models.Users;
import com.springboot.webshop.services.ProductService;
import com.springboot.webshop.services.UserService;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.model.IModel;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @GetMapping({"", "/"})
    public String showAdminPage() {
        return "admin/admin";
    }

    @GetMapping("/products")
    public String showProductList(
            Model model,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir
    ) {
        List<Product> products = productService.findEnableProduct(sortBy, sortDir);
        model.addAttribute("products", products);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        return "products/product";
    }

    @GetMapping("/disableproducts")
    public String showDisableProductList(
            Model model,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy,
            @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir
    ) {
        List<Product> products = productService.findDisableProduct(sortBy, sortDir);
        model.addAttribute("products", products);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        return "disableproducts/DisableProduct";
    }

    @GetMapping("/users")
    public String showUserList(
            Model model
    ) {
        List<Users> users = userService.findUsers();
        model.addAttribute("users", users);
        return "users/Users";
    }
}

