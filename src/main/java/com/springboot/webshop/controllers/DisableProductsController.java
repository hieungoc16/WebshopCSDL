package com.springboot.webshop.controllers;



import com.springboot.webshop.models.Product;
import com.springboot.webshop.repositories.BrandsRepository;
import com.springboot.webshop.repositories.CategoriesRepository;
import com.springboot.webshop.repositories.ProductsRepository;
import com.springboot.webshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/disableproducts")
public class DisableProductsController {

    @Autowired
    private ProductsRepository repo;
    @Autowired
    private CategoriesRepository cate;
    @Autowired
    private BrandsRepository bra;
    @Autowired
    private ProductService productService;


    @GetMapping({"","/"})
    public String showDisableProductList(Model model) {
        List<Product> products = productService.findDisableProduct();
        model.addAttribute("products", products);
        return "disableProducts/DisableProduct";
    }

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
