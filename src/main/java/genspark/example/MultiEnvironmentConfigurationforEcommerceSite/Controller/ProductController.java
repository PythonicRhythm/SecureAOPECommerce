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
        logger.info("Entering home page...");
        StringBuilder allHTML = new StringBuilder();

        allHTML.append("<style>" +
                ".homepanel {" +
                "display: flex;" +
                "flex-direction: column;" +
                "margin: 20;" +
                "align-items: center;" +
                "justify-content: center;" +
                "}" +
                ".homepanel > h1 {" +
                "margin: 0;" +
                "margin-bottom: 10;" +
                "font-size: 50;" +
                "}" +
                ".homepanel > a {" +
                "font-size: 30;" +
                "}" +
                "</style>");

        allHTML.append("<div class=\"homepanel\">");
        allHTML.append("<h1>Welcome to our E-Commerce System!</h1>");
        allHTML.append("<a href=\"/products\">View all current products.</a>");
        allHTML.append("</div>");
        return allHTML.toString();
    }

    private String productContainerBuilder(Product p)
    {
        StringBuilder childHTML = new StringBuilder();
        childHTML.append(String.format("<h2>Product (ID: %d):</h2>", p.getProductid()));
        childHTML.append(String.format("<p>Name: %s</p>", p.getName()));
        childHTML.append(String.format("<p>Description: %s</p>", p.getDescription()));
        childHTML.append(String.format("<p>Categories: %s</p>", Arrays.toString(p.getCategories())));
        childHTML.append(String.format("<p>Price: %f</p>", p.getPrice()));
        childHTML.append(String.format("<p>Seller: %s</p>", p.getSeller()));
        childHTML.append(String.format("<a href=\"/products/%d\">View Product</a>", p.getProductid()));
        return childHTML.toString();
    }

    private String productPanelBuilder(List<Product> products)
    {
        StringBuilder allHTML = new StringBuilder();

        // CSS Styling
        allHTML.append("<style>" +
                ".productpanel {" +
                "display: flex;" +
                "flex-direction: row;" +
                "flex-wrap: wrap;" +
                "align-items: stretch;" +
                "justify-content: space-evenly;" +
                "border-top: 1px solid black;" +
                "border-bottom: 1px solid black;" +
                "padding: 15;" +
                "}" +
                ".productcomponent {" +
                "width: 30%;" +
                "height: 100%;" +
                "border: 1px solid black;" +
                "border-radius: 10% / 50%;" +
                "margin-top: 15;" +
                "display: flex;" +
                "flex-direction: column;" +
                "align-items: center;" +
                "justify-content: center;" +
                "}" +
                ".searchpanel {" +
                "display: flex;" +
                "flex-direction: column;" +
                "align-items: center;" +
                "justify-content: center;" +
                "padding: 15;" +
                "padding-left: 50;" +
                "padding-right: 50;" +
                "}" +
                ".inputrow {" +
                "display: flex;" +
                "flex-direction: row;" +
                "width: 100%;" +
                "align-items: center;" +
                "justify-content: center;" +
                "padding: 10;" +
                "gap: 10px;" +
                "}" +
                ".productcomponent > p {" +
                "margin: 2;" +
                "}" +
                ".productcomponent > a {" +
                "margin: 5;" +
                "}" +
                ".header {" +
                "display: flex;" +
                "flex-direction: column;" +
                "align-items: center;" +
                "justify-content: center;" +
                "padding-bottom: 20;" +
                "}" +
                ".header > h1 {" +
                "margin-bottom: 5;" +
                "font-size: 60;" +
                "}" +
                ".header > a {" +
                "margin-bottom: 3;" +
                "}" +
                "</style>");
        allHTML.append("<script>" +
                "function namesearchredirec() {" +
                "window.location.href = \"http://localhost:8080/customers/findbyname/\"+document.querySelectorAll('#name')[0].value;" +
                "}" +
                "function emailsearchredirec() {" +
                "window.location.href = \"http://localhost:8080/customers/findbyemail/\"+document.querySelectorAll('#email')[0].value;" +
                "}" +
                "function phonenumbersearchredirec() {" +
                "window.location.href = \"http://localhost:8080/customers/findbyphonenumber/\"+document.querySelectorAll('#phonenumber')[0].value;" +
                "}" +
                "</script>");
        allHTML.append("<div class=\"header\">");
        allHTML.append("<h1>ALL PRODUCTS</h1>");

        if(products.isEmpty()) {
            allHTML.append("<h2>No Products found. Create some via Postman!</h2>");
            allHTML.append("</div>");
            return allHTML.toString();
        }

        // Create sorting links
        allHTML.append("<a href=\"/products/names\"><button>Sort by Name</button></a>");
        allHTML.append("<a href=\"/products/sellers\"><button>Sort by Seller</button></a>");
        allHTML.append("<a href=\"/products\"><button>Default ID Sort</button></a>");

        // Close header container
        allHTML.append("</div>");

        allHTML.append("<div class=\"productpanel\">");
        for(Product p: products) {
            logger.debug(String.format("Found Product %d, creating html component for customer.", p.getProductid()));
            allHTML.append("<div class=\"productcomponent\">");
            allHTML.append(productContainerBuilder(p));
            allHTML.append("</div>");
        }
        allHTML.append("</div>");

        allHTML.append("<div class=\"searchpanel\">");

        allHTML.append("<div class=\"inputrow\">");
        allHTML.append("<label for=\"name\">Name Search: </label>");
        allHTML.append("<input type=\"text\" id=\"name\" name=\"name\">");
        allHTML.append("<button onclick=\"namesearchredirec()\">Search</button>");
        allHTML.append("</div>");

        allHTML.append("<div class=\"inputrow\">");
        allHTML.append("<label for=\"email\">Email Search: </label>");
        allHTML.append("<input type=\"text\" id=\"email\" name=\"email\">");
        allHTML.append("<button onclick=\"emailsearchredirec()\">Search</button>");
        allHTML.append("</div>");

        allHTML.append("<div class=\"inputrow\">");
        allHTML.append("<label for=\"phonenumber\">Phone Number Search: </label>");
        allHTML.append("<input type=\"text\" id=\"phonenumber\" name=\"phonenumber\">");
        allHTML.append("<button onclick=\"phonenumbersearchredirec()\">Search</button>");
        allHTML.append("</div>");

        allHTML.append("</div>");

        return allHTML.toString();
    }

    private String productPageBuilder(Product p)
    {
        StringBuilder allHTML = new StringBuilder();

        allHTML.append("<style>" +
                ".pagepanel {" +
                "display: flex;" +
                "flex-direction: row;" +
                "height: 100%;" +
                "align-items: center;" +
                "justify-content: center;" +
                "}" +
                ".productcomp {" +
                "display: flex;" +
                "flex-direction: column;" +
                "border: 1px solid black;" +
                "border-radius: 20px;" +
                "padding: 20;" +
                "align-items: center;" +
                "justify-content: center;" +
                "}" +
                ".productcomp > p {" +
                "font-size: 30px;" +
                "margin: 0;" +
                "margin-bottom: 10;" +
                "}" +
                ".productcomp > h1 {" +
                "font-size: 60px;" +
                "}" +
                "</style>");

        allHTML.append("<div class=\"pagepanel\">");
        allHTML.append("<div class=\"productcomp\">");

        if(p == null) {
            logger.info("Product not found, creating null page...");
            allHTML.append("<h1>Product Not Found!</h1>");
            allHTML.append("<a href=\"/products\"><button>Go Back</button></a>");
            allHTML.append("</div>");
            allHTML.append("</div>");
        }
        else {
            logger.info("Product found, creating product page...");
            allHTML.append("<a href=\"/products\"><button>Go Back</button></a>");
            allHTML.append(String.format("<h1>Product (ID: %d):</h1>", p.getProductid()));
            allHTML.append(String.format("<p>Description: %s</p>", p.getDescription()));
            allHTML.append(String.format("<p>Categories: %s</p>", Arrays.toString(p.getCategories())));
            allHTML.append(String.format("<p>Price: %f</p>", p.getPrice()));
            allHTML.append(String.format("<p>Seller: %s</p>", p.getSeller()));

        }

        allHTML.append("</div>");
        allHTML.append("</div>");


        return allHTML.toString();
    }

    // retrieve all products
    @GetMapping("/products")
    public String getProducts(){
        List<Product> listOfProducts =  this.ps.getAllProducts();
        if (listOfProducts.isEmpty()){
            logger.info("There Is Currently No Product In The Database");
        } else {
            logger.info("Successfully Retrieved All Products");
        }
        return productPanelBuilder(listOfProducts);
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
        return productPanelBuilder(listOfProducts);
    }

    // Get product based on name
    @GetMapping("/products/name")
    public String getProductByName(@RequestParam String name) {
        List<Product> listOfProducts = this.ps.getByName(name);
        if (listOfProducts.isEmpty()){
            logger.info("There Is Currently No Products With the Name: " + name);
        } else {
            logger.info("Successfully Retrieved Products with the Name: " + name);
        }
        return productPanelBuilder(listOfProducts);
    }

    // Get all the names of all the products
    @GetMapping("/products/names")
    public String getAllProductNames() {
        List<Product> listOfProducts= this.ps.getByNames();
        if (listOfProducts.isEmpty()){
            logger.info("There Is Currently No Products");
        } else {
            logger.info("Successfully Retrieved Products Names");
        }
        return productPanelBuilder(listOfProducts);
    }

    // Get product based on sellers   HOW TO USE (http://localhost:9090/products/seller?seller=orw)
    @GetMapping("/products/seller")
    public String getProductBySeller(@RequestParam String seller) {
        List<Product> listOfProducts = this.ps.getBySellers(seller);
        if (listOfProducts.isEmpty()){
            logger.info("There Is Currently No Products With the Seller Name: " + seller);
        } else {
            logger.info("Successfully Retrieved Products with the Seller Name: " + seller);
        }
        return productPanelBuilder(listOfProducts);
    }
    // get all the name of sellers
    @GetMapping("/products/sellers")
    public String getAllProductSellers() {
        List<Product> listOfProducts = this.ps.getBySellers();
        if (listOfProducts.isEmpty()){
            logger.info("There Is Currently No Products");
        } else {
            logger.info("Successfully Retrieved All Sellers Names");
        }
        return productPanelBuilder(listOfProducts);
    }

    // Get product based on category
    @GetMapping("/products/category/{category}")
    public String getProductByCategory(@PathVariable String category) {
        List<Product> listOfProducts = this.ps.getByCategory(category);
        if (listOfProducts.isEmpty()){
            logger.info("There Is Currently No Products In The Category: " + category);
        } else {
            logger.info("Successfully Retrieved Products In The Category: " + category);
        }
        return productPanelBuilder(listOfProducts);
    }

    // Get product based on id
    @GetMapping("/products/{productId}")
    public String getProductById(@PathVariable Long productId) {
        return productPageBuilder(this.ps.getById(productId));
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
            List<Product> listOfProducts = new ArrayList<>();
            for (int i = 0; i < num; i++){
                for (Product product : products){
                    this.ps.addProduct(product);
                }
            }
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
