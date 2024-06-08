package com.springboot.webshop.controllers;



import com.springboot.webshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/admin/disableproducts")
public class DisableProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/restore")
    public String restoreProduct(
            @RequestParam int id
    ){
        try{
            productService.enableProduct(id);
        }
        catch(Exception ex){
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/disableproducts";
    }

}
