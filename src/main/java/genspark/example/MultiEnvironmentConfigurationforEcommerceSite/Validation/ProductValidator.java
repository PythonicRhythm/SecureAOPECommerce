package genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Validation;

import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Controller.ProductController;
import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Entity.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;


// Class that validate inputs for Products Creation
@Component
public class ProductValidator implements Validator{
    // Logger to log invalid inputs
    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Override
    public boolean supports(Class<?> className) {
        return Product.class.equals(className);
    }

    @Override
    public void validate(Object target, Errors errors) {
        List<Product> products = (List<Product>) target;
        // Index so user who add more than 1 Customer can know where the error was
        int index = 0;
        // Validate each customer separately
        for (Product product : products) {
            // Validate name
            if (product.getName() == null || product.getName().isEmpty()) {
                errors.reject("product.empty", "Name must not be empty for product at index : " + index);
                logger.error("Name must not be empty for product at index " + index + " : " + product);
            }
            // Validate seller
            if (product.getSeller() == null || product.getSeller().isEmpty()) {
                errors.reject("product.empty", "Seller must not be empty for product at index : " + index);
                logger.error("Seller must not be empty for product at index " + index + " : " + product);
            }
            // Validate price
            if (product.getPrice() == 0) {
                errors.reject("product.empty", "Price must not be null for product at index : " + index);
                logger.error("Price must not be empty for product at index " + index + " : " + product);
            }
            // Validate description
            if (product.getDescription() == null || product.getDescription().isEmpty()) {
                errors.reject("product.empty", "Description must not be null for product at index : " + index);
                logger.error("Description must not be empty for product at index " + index + " : " + product);
            }
            // Validate description
            if (product.getCategories() == null || product.getCategories().length == 0) {
                errors.reject("product.empty", "Categories must not be null for product at index : " + index);
                logger.error("Categories must not be empty for product at index " + index + " : " + product);
            }
            index++;
        }
    }
}


