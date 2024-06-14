package genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Controller;

import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Entity.Product;
import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Services.ProductService;
import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Services.ProductServiceImpl;
import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Validation.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {
    // automatically wire up beans (components) defined in application context and inject them into ps
    @Autowired
    public ProductServiceImpl ps;
    @Autowired
    private ProductValidator validator;


    //send to login page to authenticate user
    @GetMapping("login")
    public String loginPage(){
        return "login";
    }
    @GetMapping("admin")
    public String adminPage(){
        return "admin";
    }
    @GetMapping("normal")
    public String normalPage(){
        return "normal";
    }

    // retrieve all products
    @GetMapping("/products")
    public String getProducts(Model model){
        List<Product> listOfProducts =  this.ps.getAllProducts();
        model.addAttribute("productlist", listOfProducts);
        return "products";
    }

    // retrieve all products sorted by names
    @GetMapping("/products/names/sorted")
    public String getSortedNames(Model model){
        List<Product> listOfProducts = this.ps.getBySortedName();
        model.addAttribute("productlist", listOfProducts);
        return "products";
    }
    @GetMapping("/products/sellers/sorted")
    public String getSortedSellers(Model model){
        List<Product> listOfProducts = this.ps.getBySortedSeller();
        model.addAttribute("productlist", listOfProducts);
        return "products";
    }

    // Get product based on name
    @GetMapping("/products/name")
    public String getProductByName(@RequestParam String name, Model model) {
        List<Product> listOfProducts = this.ps.getByName(name);
        model.addAttribute("productlist", listOfProducts);
        return "products";
    }

    // Get all the names of all the products
    @GetMapping("/products/names")
    public String getAllProductNames(Model model) {
        List<Product> listOfProducts= this.ps.getByNames();
        model.addAttribute("productlist", listOfProducts);
        return "products";
    }

    // Get product based on sellers   HOW TO USE (http://localhost:9090/products/seller?seller=orw)
    @GetMapping("/products/seller")
    public String getProductBySeller(@RequestParam String seller, Model model) {
        List<Product> listOfProducts = this.ps.getBySellers(seller);
        model.addAttribute("productlist", listOfProducts);
        return "products";
    }
    // get all the name of sellers
    @GetMapping("/products/sellers")
    public String getAllProductSellers(Model model) {
        List<Product> listOfProducts = this.ps.getBySellers();
        model.addAttribute("productlist", listOfProducts);
        return "products";
    }

    // Get product based on category
    @GetMapping("/products/category/{category}")
    public String getProductByCategory(@PathVariable String category, Model model) {
        List<Product> listOfProducts = this.ps.getByCategory(category);
        model.addAttribute("productlist", listOfProducts);
        return "products";
    }

    // Get product based on id
    @GetMapping("/products/{productId}")
    public String getProductById(@PathVariable Long productId, Model model) {
        model.addAttribute("product", this.ps.getById(productId));
        return "targetproduct";
    }

    // add new product(s) with validation
    @PostMapping("/products")
    public String addProduct(@Valid @RequestBody List<Product> products, BindingResult result, Model model){
        // Validate the inputs to see if there's any error
        validator.validate(products,result);
        model.addAttribute("productlist", products);
        if (!result.hasErrors()){
            List<Product> listOfProducts = this.ps.addProducts(products);
            return "products";
        }
        return "products";
    }

    // add multiple of same product(s) with validation
    // POST ... /products/multiple?num=5
    @PostMapping("/products/multiple")
    public String addProducts(@Valid @RequestBody List<Product> products,
                                    @RequestParam int num,
                                    BindingResult result,
                                     Model model){
        validator.validate(products,result);
        model.addAttribute("productlist", products);
        if (!result.hasErrors()){
            List<Product> listOfProducts = this.ps.addMutlipleProduct(products,num);
            return "products";
        }
        return "products";
    }

    // update product(s) with validation
    @PutMapping("/products")
    public String updateproducts(@Valid @RequestBody List<Product> products, BindingResult result, Model model){
        validator.validate(products,result);
        model.addAttribute("productlist", products);
        if (!result.hasErrors()){
            List<Product> listOfProducts = this.ps.updateProduct(products);
            return "products";
        }
        return "products";
    }

    // delete a product
    @DeleteMapping("/products/{productId}")
    public String deleteProduct(@PathVariable Long productId, Model model){
        this.ps.deleteProduct(productId);
        return "products";
    }



}
