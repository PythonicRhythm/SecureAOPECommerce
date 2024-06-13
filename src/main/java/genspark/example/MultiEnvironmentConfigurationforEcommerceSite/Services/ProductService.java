package genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Services;

import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Entity.Product;
import java.util.List;
public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getBySellers(String seller);
    List<Product> getById();
    List<Product> getByName();
    List<String> getByNames();
    List<String> getBySellers();
    List<Product> getByCategory();
    List<Product> getBySorted();
    Product addMutlipleProduct(String name, int amount);
    List<Product> addProduct();
    List<Product> updateProduct();
    String deleteProduct();
}
