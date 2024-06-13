package genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Services;

import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Entity.Product;
import java.util.List;
public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getBySellers(String seller);
    List<Product> getById(long id);
    List<Product> getByName(String name);
    List<String> getByNames();
    List<String> getBySellers();
    List<Product> getByCategory(String category);
    List<Product> getBySorted();
    List<Product> addMultipleProduct(List<Product> products, int amount);
    List<Product> addProduct();
    List<Product> updateProduct();
    String deleteProduct();
}
