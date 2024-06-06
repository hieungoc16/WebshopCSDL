package com.springboot.webshop.controllers;


import com.springboot.webshop.models.Brand;
import com.springboot.webshop.models.Category;
import com.springboot.webshop.models.Product;
import com.springboot.webshop.repositories.BrandsRepository;
import com.springboot.webshop.repositories.CategoriesRepository;
import com.springboot.webshop.repositories.ProductsRepository;
import com.springboot.webshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.Date;
import java.util.List;

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
        return "products/product";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
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
            @RequestParam("number") String number,
            @RequestParam("brandid") String brandId,
            @RequestParam("categoryid") String categoryId,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
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

        int maxIdNumber = 0;
        List<Product> products = productService.findEnableProduct();
        // Tìm giá trị lớn nhất của sản phẩm hiện có
        for (Product productCurrent : products) {
            String productId = productCurrent.getId(); // Ví dụ: "P001", "P002",...
            String numberPart = productId.substring(1); // Bỏ ký tự 'P' ở đầu

            try {
                int currentIdNumber = Integer.parseInt(numberPart);
                if (currentIdNumber > maxIdNumber) {
                    maxIdNumber = currentIdNumber;
                }
            } catch (NumberFormatException e) {
                // Xử lý ngoại lệ nếu số không hợp lệ
                // Có thể bỏ qua sản phẩm có ID không hợp lệ hoặc đưa ra thông báo lỗi
                System.err.println("Invalid product ID format: " + productId);
            }
        }

        // Tạo ID mới
        int newIdNumber = maxIdNumber + 1;
        String id = "P" + newIdNumber;

        product.setId(id);
        product.setName(name);
        product.setNumber(number);
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
            @RequestParam("id") String id
    ) {
        try{
            Product product = repo.findById(id).get();
            List<Brand> brandList = bra.findAll();
            List<Category> categoryList = cate.findAll();
            model.addAttribute("brandList", brandList);
            model.addAttribute("categoryList", categoryList);
            model.addAttribute("product", product);
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
            @RequestParam("id") String id,
            @RequestParam("name") String name,
            @RequestParam("number") String number,
            @RequestParam("brandid") String brandId,
            @RequestParam("categoryid") String categoryId,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("imageFile") MultipartFile imageFile
            ){

        try {
            Product product = repo.findById(id).get();
            model.addAttribute("product", product);

            if (!imageFile.isEmpty()) {
                String uploadDir = "public/images/";
                Path oldImagePath = Paths.get(uploadDir + product.getImageFileName());

                try {
                    Files.delete(oldImagePath);
                } catch (Exception ex) {
                    System.out.println("Exception: " + ex.getMessage());
                }
                //Save image file
                MultipartFile image = imageFile;
                Date createdAt = new Date();
                String storageFileName = image.getOriginalFilename();
                try(InputStream inputStream = image.getInputStream()){
                        Files.copy(inputStream, Paths.get(uploadDir + storageFileName), StandardCopyOption.REPLACE_EXISTING);
                    }

                product.setImageFileName(storageFileName);
                }


            Brand brand = this.bra.findById(brandId).get();
            Category category = this.cate.findById(categoryId).get();

            product.setName(name);
            product.setNumber(number);
            product.setBrand(brand);
            product.setCategory(category);
            product.setPrice(price);
            product.setDescription(description);

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
