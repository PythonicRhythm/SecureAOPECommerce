package genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Controller;

import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Entity.Product;
import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Validation.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.lang.StringTemplate.STR;

@RestController
public class ProductController {
    // automatically wire up beans (components) defined in application context and inject them into ps
    @Autowired
    public ProductService ps;
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
        return this.ps.getAllProducts();
    }

    // retrieve all products sorted by names
    @GetMapping("/products/sorted")
    public List<Product> getSortedProducts(){
        return this.ps.getBySorted();
    }

    // Get product based on name
    @GetMapping("/products/name")
    public List<Product> getProductByName(@RequestParam String name) {
        return this.ps.getByName(name);
    }

    // Get all the names of all the products
    @GetMapping("/products/names")
    public List<String> getAllProductNames() {
        return this.ps.getByNames();
    }

    // Get product based on sellers   HOW TO USE (http://localhost:9090/products/seller?seller=orw)
    @GetMapping("/products/seller")
    public List<Product> getProductBySeller(@RequestParam String seller) {
        return this.ps.getBySeller(seller);
    }
    // get all the name of sellers
    @GetMapping("/products/sellers")
    public List<String> getAllProductSellers() {
        return this.ps.getBySellers();
    }

    // Get product based on category
    @GetMapping("/products/category/{category}")
    public List<Product> getProductByCategory(@PathVariable String category) {
        return this.ps.getByCategory(category);
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
            List<Product> listOfProducts = this.ps.addProduct(products);
            logger.info("Successfully Added Products");
            return listOfProducts;
        }
        return null;
    }

    // update product(s) with validation
    @PostMapping("/products")
    public List<Product> addProduct(@Valid @RequestBody List<Product> products,
                                    @RequestParam int num,
                                    BindingResult result){

        logger.info("Attempting to Add Products");
        // Validate the inputs to see if there's any error
        validator.validate(products,result);
        if (result.hasErrors()){
            // if fails print error and its location
            logger.error("Validation Failed: "+ result.getAllErrors());
        } else {
            List<Product> listOfProducts = this.ps.addMultipleProduct(products,num);
            logger.info("Successfully Added Products");
            return listOfProducts;
        }
        return null;
    }

    // add multiple of same product(s) with validation
    // POST ... /products?num=5
    @PutMapping("/products")
    public List<Product> addProduct(@Valid @RequestBody List<Product> products, BindingResult result){
        logger.info("Attempting to Update Products");
        // Validate the inputs to see if there's any error
        validator.validate(products,result);
        if (result.hasErrors()){
            // if fails print error and its location
            logger.error("Validation Failed: "+ result.getAllErrors());
        } else {
            List<Product> listOfProducts = this.ps.updateProducts(products);
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
