package com.springboot.webshop.controllers;


import com.springboot.webshop.models.Brand;
import com.springboot.webshop.models.Category;
import com.springboot.webshop.models.Product;
import com.springboot.webshop.models.ProductDto;
import com.springboot.webshop.repositories.BrandsRepository;
import com.springboot.webshop.repositories.CategoriesRepository;
import com.springboot.webshop.repositories.ProductsRepository;
import com.springboot.webshop.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsRepository repo;
    @Autowired
    private CategoriesRepository cate;
    @Autowired
    private BrandsRepository bra;
    @Autowired
    private ProductService productService;
    @GetMapping({"", "/"})
    public String showProductList(Model model) {
        List<Product> products = productService.findEnableProduct();
        model.addAttribute("products", products);
        return "products/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        ProductDto productDto = new ProductDto();
        List<Brand> brandList = bra.findAll();
        List<Category> categoryList = cate.findAll();
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("brandList", brandList);
        return "products/CreateProduct";
    }

    @PostMapping("/create")
    public String createProduct(
            Model model,
            @RequestParam("name") String name,
            @RequestParam("brandid") String brandId,
            @RequestParam("categoryid") String categoryId,
            @RequestParam("description") String description,
            @RequestParam("price") int price,
            @RequestParam("imageFile") MultipartFile imageFile
    ){

        //Save image file
        MultipartFile image = imageFile;
        Date createdAt = new Date();
        String storedFileName = image.getOriginalFilename();
        try {
            String uploadDir = "public/images/";
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)){
                Files.createDirectories(uploadPath);
            }
            try(InputStream inputStream = image.getInputStream()){
                Files.copy(inputStream, Paths.get(uploadDir + storedFileName), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Brand brand = this.bra.findById(brandId).get();
        Category category = this.cate.findById(categoryId).get();
        Product product = new Product();
        String id = "P001";

        product.setId(id);
        product.setName(name);
        product.setBrand(brand);
        product.setCategory(category);
        product.setPrice(price);
        product.setDescription(description);
        product.setCreatedAt(createdAt);
        product.setImageFileName(storedFileName);

        repo.save(product);

        return "redirect:/products";
    }

    @GetMapping("/edit")
    public String showEditPage(
            Model model,
            @RequestParam String id
    ) {
        try{
            Product product = repo.findById(id).get();
            model.addAttribute("product", product);
            Optional<Brand> brand = bra.findById(product.getBrand().getBrand_id());
           // model.addAttribute("brand", brand);
           // model.addAttribute("category", category);

            ProductDto productDto = new ProductDto();
            productDto.setName(product.getName());
            productDto.setBrand(product.getBrand());
            productDto.setPrice(product.getPrice());
           // productDto.setCategory(product.getCategory());
            productDto.setDescription(product.getDescription());

            model.addAttribute("productDto", productDto);

            ProductDto brandDto = new ProductDto();
            productDto.setBrand(product.getBrand());
            model.addAttribute("brandDto", productDto);

        }
        catch (Exception ex){
            System.out.println("Exception: " + ex.getMessage());
            return "redirect:/products";
        }
        return "products/EditProduct";
    }

    @PostMapping("/edit")
    public String updateProduct(
            Model model,
            @RequestParam String id,
            @Valid @ModelAttribute ProductDto productDto,
            BindingResult result
            ){

        try {
            Product product = repo.findById(id).get();
            model.addAttribute("product", product);

            if (result.hasErrors()){
            return "products/EditProduct";
            }

            if (!productDto.getImageFile().isEmpty()) {
                String uploadDir = "public/images/";
                Path oldImagePath = Paths.get(uploadDir + product.getImageFileName());

                try {
                    Files.delete(oldImagePath);
                } catch (Exception ex) {
                    System.out.println("Exception: " + ex.getMessage());
                }
                //Save image file
                MultipartFile image = productDto.getImageFile();
                Date createdAt = new Date();
                String storageFileName = image.getOriginalFilename();
                try(InputStream inputStream = image.getInputStream()){
                        Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
                    }

                product.setImageFileName(storageFileName);
                }

            product.setName(productDto.getName());
            product.setBrand(productDto.getBrand());
            product.setCategory(productDto.getCategory());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());

            repo.save(product);
        }
        catch (Exception ex){
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/products";
    }

    @GetMapping("/delete")
    public String deleteProduct(
            @RequestParam String id
    ){
        try{
            Product product = repo.findById(id).get();

            Path imagePath = Paths.get("public/images/" + product.getImageFileName());
            try{
                Files.delete(imagePath);
            }
            catch (Exception ex) {
                System.out.println("Exception: " + ex.getMessage());

            }
            repo.delete(product);
        }
        catch (Exception ex){
            System.out.println("Exception: " + ex.getMessage());
        }
        return "redirect:/products";
    }

}
