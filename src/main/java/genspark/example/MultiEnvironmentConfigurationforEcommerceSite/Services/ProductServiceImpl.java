package genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Services;

import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Entity.Product;
import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Repository.ProductDAO;
import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Controller.ProductController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    public Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    public ProductDAO productDao;

    public List<Product> getAllProducts() {
        return this.productDao.findAll();
    }

    @Override
    public List<Product> getBySellers(String seller) {
        return List.of();
    }

    @Override
    public List<Product> getById(long id) {
        return List.of();
    }

    @Override
    public List<Product> getByName(String name) {
        return List.of();
    }

    @Override
    public List<String> getByNames() {
        return List.of();
    }

    @Override
    public List<String> getBySellers() {
        return List.of();
    }

    @Override
    public List<Product> getByCategory(String category) {
        return List.of();
    }

    @Override
    public List<Product> getBySorted() {
        return List.of();
    }

    @Override
    public Product addMultipleProduct(String name, int amount) {
        return null;
    }

    @Override
    public List<Product> addProduct(Product product) {
        return List.of();
    }

    @Override
    public List<Product> updateProduct() {
        return List.of();
    }

    @Override
    public String deleteProduct() {
        return "";
    }

    public Product getProductById(int id) {

    }

}
