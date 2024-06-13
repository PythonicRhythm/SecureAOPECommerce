package genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Controller;

import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Validation.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    // To get all the books Json
    @GetMapping("/products")
    public List<Product> getProducts(){
        return this.ps.getAllProducts();
    }
    @GetMapping("/products/sorted")
    public List<Product> getSortedProducts(){
        return this.ps.getSortedProducts();
    }

    // Get Book Json based on name of the book
    @GetMapping("/products/name")
    public List<Product> getProductByName(@RequestParam String name) {
        return this.ps.getProductByName(name);
    }

    @GetMapping("/products/names")
    public List<String> getAllProductNames() {
        return this.ps.getProductByNames();
    }

    // Get Book Json based on name of the author   HOW TO USE (http://localhost:9090/Books/author?author=orw)
    @GetMapping("/products/seller")
    public List<Product> getProductBySeller(@RequestParam String seller) {
        return this.ps.getProductBySeller(seller);
    }

    @GetMapping("/products/sellers")
    public List<String> getAllProductSellers() {
        return this.ps.getProductBySellers();
    }

    // Get Book Json based on ONE of the genre
    @GetMapping("/products/category/{category}")
    public List<Product> getProductByCategory(@PathVariable String category) {
        return this.ps.getProductByCategory(category);
    }

    // get book by id
    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable Long productId) {
        return this.ps.getProductById(productId);
    }

    // add a new book
    @PostMapping("/products")
    public List<Product> addProduct(@Valid @RequestBody List<Product> products, BindingResult result){
        logger.info("Attempting to Add Products");
        // Validate the inputs to see if there's any error
        validator.validate(products,result);
        if (result.hasErrors()){
            // if fails print error and its location
            logger.error(STR."Validation Failed: \{result.getAllErrors()}");
        } else {
            List<Product> listOfProducts = this.ps.addProduct(products);
            logger.info("Successfully Added Products");
            return listOfProducts;
        }
        return null;
    }

    // update a book
    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product product){
        return this.ps.updateProduct(product);
    }

    // delete a book

    @DeleteMapping("/products/{productId}")
    public String deleteProduct(@PathVariable Long productId){
        return this.ps.deleteProduct(productId);
    }



}
