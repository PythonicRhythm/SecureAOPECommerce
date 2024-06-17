package genspark.example.MultiEnvironmentConfigurationforEcommerceSite;

import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Entity.Product;
import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Services.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class MultiEnvironmentConfigurationforEcommerceSiteApplicationTests {

    @Autowired
    ProductServiceImpl service;

    @Test
    void contextLoads() {
        assertThat(service).isNotNull();
    }

    @Test
    void controllerCRUDTest() {
        String expectedName = "Test";
        String expectedDescription = "This is a test";
        double expectedPrice = 9.99;
        String expectedSeller = "Someone";
        String[] expectedCategories = {"test", "category"};
        Product expectedProduct = new Product(0, expectedName, expectedDescription, expectedPrice, expectedSeller, expectedCategories);

        Product actualProduct = service.addProduct(expectedProduct);
        assertEquals(expectedName, actualProduct.getName());
    }

}
