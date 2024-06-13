package genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Services;

import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Entity.Product;
import java.util.List;
public interface ProductService {
    List<Product> getAllProducts();
    List<Product> getBySellers(String seller);
<<<<<<< Updated upstream
    List<Product> getById(long id);
=======
    Product getById(long productID);
>>>>>>> Stashed changes
    List<Product> getByName(String name);
    List<String> getByNames();
    List<String> getBySellers();
    List<Product> getByCategory(String category);
    List<Product> getBySorted();
<<<<<<< Updated upstream
    List<Product> addMultipleProduct(List<Product> products, int amount);
    List<Product> addProduct();
    List<Product> updateProduct();
    String deleteProduct();
=======
    List<Product> addMutlipleProduct(List<Product> products, int amount);
    List<Product> addProduct(List<Product> products);
    List<Product> updateProduct(List<Product> products);
    String deleteProduct(long productID);
>>>>>>> Stashed changes
}
