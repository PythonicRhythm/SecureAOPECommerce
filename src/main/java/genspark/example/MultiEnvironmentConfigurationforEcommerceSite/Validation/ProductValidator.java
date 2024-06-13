package genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Validation;

import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Controller.ProductController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.regex.Pattern;

import static java.lang.StringTemplate.STR;

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
            // Validate Email
            if (product.getSeller() == null || product.getSeller().isEmpty()) {
                errors.reject("product.empty", "Seller must not be empty for product at index : " + index);
                logger.error("Seller must not be empty for product at index " + index + " : " + product);
            }
            // Validate Phone Number
            if (product.get() == null || product.getPhoneNumber().isEmpty()) {
                errors.reject("customer.empty", STR."Phone must not be empty for customer at index \{index}");
                logger.error(STR."Phone must not be empty for customer at index \{index}: \{product}");
            }
            index++;
        }
    }
}


