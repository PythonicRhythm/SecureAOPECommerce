package genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Controller;

import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Entity.Product;
import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Services.ProductService;
import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Services.ProductServiceImpl;
import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Validation.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {
    // automatically wire up beans (components) defined in application context and inject them into ps
    @Autowired
    public ProductServiceImpl ps;
    @Autowired
    private ProductValidator validator;
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    // Home page
    @GetMapping("/")
    public String home(){
        return "<h1>Welcome to your Ecommerce</h1>";
    }

    // retrieve all products
    @GetMapping("/products")
    public List<Product> getProducts(){
        List<Product> listOfProducts =  this.ps.getAllProducts();
        if (listOfProducts.isEmpty()){
            logger.info("There Is Currently No Product In The Database");
        } else {
            logger.info("Successfully Retrieved All Products");
        }
        return listOfProducts;
    }

    // retrieve all products sorted by names
    @GetMapping("/products/names/sorted")
    public List<Product> getSortedNames(){
        List<Product> listOfProducts = this.ps.getBySortedName();
        if (listOfProducts.isEmpty()){
            logger.info("There Is Currently No Product In The Database");
        } else {
            logger.info("Successfully Retrieved All Products In Sorted Orders of Name");
        }
        return listOfProducts;
    }
    @GetMapping("/products/sellers/sorted")
    public List<Product> getSortedSellers(){
        List<Product> listOfProducts = this.ps.getBySortedSeller();
        if (listOfProducts.isEmpty()){
            logger.info("There Is Currently No Product In The Database");
        } else {
            logger.info("Successfully Retrieved All Products In Sorted Orders of Sellers");
        }
        return listOfProducts;
    }

    // Get product based on name
    @GetMapping("/products/name")
    public List<Product> getProductByName(@RequestParam String name) {
        List<Product> listOfProducts = this.ps.getByName(name);
        if (listOfProducts.isEmpty()){
            logger.info("There Is Currently No Products With the Name: " + name);
        } else {
            logger.info("Successfully Retrieved Products with the Name: " + name);
        }
        return listOfProducts;
    }

    // Get all the names of all the products
    @GetMapping("/products/names")
    public List<String> getAllProductNames() {
        List<String> listOfName= this.ps.getByNames();
        if (listOfName.isEmpty()){
            logger.info("There Is Currently No Products");
        } else {
            logger.info("Successfully Retrieved Products Names");
        }
        return listOfName;
    }

    // Get product based on sellers   HOW TO USE (http://localhost:9090/products/seller?seller=orw)
    @GetMapping("/products/seller")
    public List<Product> getProductBySeller(@RequestParam String seller) {
        List<Product> listOfProducts = this.ps.getBySellers(seller);
        if (listOfProducts.isEmpty()){
            logger.info("There Is Currently No Products With the Seller Name: " + seller);
        } else {
            logger.info("Successfully Retrieved Products with the Seller Name: " + seller);
        }
        return listOfProducts;
    }
    // get all the name of sellers
    @GetMapping("/products/sellers")
    public List<String> getAllProductSellers() {
        List<String> listOfName= this.ps.getBySellers();
        if (listOfName.isEmpty()){
            logger.info("There Is Currently No Products");
        } else {
            logger.info("Successfully Retrieved All Sellers Names");
        }
        return listOfName;
    }

    // Get product based on category
    @GetMapping("/products/category/{category}")
    public List<Product> getProductByCategory(@PathVariable String category) {
        List<Product> listOfProducts = this.ps.getByCategory(category);
        if (listOfProducts.isEmpty()){
            logger.info("There Is Currently No Products In The Category: " + category);
        } else {
            logger.info("Successfully Retrieved Products In The Category: " + category);
        }
        return listOfProducts;
    }

    // Get product based on id
    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return this.ps.getById(productId);
    }

    // add new product(s) with validation
    @PostMapping("/products")
    public List<Product> addProduct(@Valid @RequestBody List<Product> products, BindingResult result){
        logger.info("Attempting to Add Products");
        // Validate the inputs to see if there's any error
        validator.validate(products,result);
        if (result.hasErrors()){
            // if fails print error and its location
            logger.error("Validation Failed: "+ result.getAllErrors());
        } else {
            List<Product> listOfProducts = this.ps.addProducts(products);
            logger.info("Successfully Added Products");
            return listOfProducts;
        }
        return null;
    }

    // add multiple of same product(s) with validation
    // POST ... /products/multiple?num=5
    @PostMapping("/products/multiple")
    public List<Product> addProducts(@Valid @RequestBody List<Product> products,
                                    @RequestParam int num,
                                    BindingResult result){
        logger.info("Attempting to Add Products");
        // Validate the inputs to see if there's any error
        validator.validate(products,result);
        if (result.hasErrors()){
            // if fails print error and its location
            logger.error("Validation Failed: "+ result.getAllErrors());
        } else {
            List<Product> listOfProducts = this.ps.addMutlipleProduct(products,num);
            logger.info("Successfully Added Products");
            return listOfProducts;
        }
        return null;
    }

    // update product(s) with validation
    @PutMapping("/products")
    public List<Product> updateproducts(@Valid @RequestBody List<Product> products, BindingResult result){
        logger.info("Attempting to Update Products");
        // Validate the inputs to see if there's any error
        validator.validate(products,result);
        if (result.hasErrors()){
            // if fails print error and its location
            logger.error("Validation Failed: "+ result.getAllErrors());
        } else {
            List<Product> listOfProducts = this.ps.updateProduct(products);
            logger.info("Successfully Updated Products");
            return listOfProducts;
        }
        return null;
    }

    // delete a product
    @DeleteMapping("/products/{productId}")
    public String deleteProduct(@PathVariable Long productId){
        return this.ps.deleteProduct(productId);
    }



}
