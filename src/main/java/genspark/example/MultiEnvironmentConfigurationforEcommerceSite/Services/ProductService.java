package genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Services;

import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getBySellers(String seller);
    Product getById(long productID);
    List<Product> getByName(String name);
    List<String> getByNames();
    List<String> getBySellers();
    List<Product> getByCategory(String category);
    List<Product> getBySorted();
    List<Product> addMutlipleProduct(List<Product> products, int amount);
    List<Product> addProduct(List<Product> products);
    List<Product> updateProduct(List<Product> products);
    String deleteProduct(long productID);
}
