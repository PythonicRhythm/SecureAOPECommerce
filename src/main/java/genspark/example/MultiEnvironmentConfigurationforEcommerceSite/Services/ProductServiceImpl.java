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
    public ProductDao productDao;

    public List<Product> getAllProducts() {
        return this.productDao.findall();
    }
    public Product getProductById(int id) {

    }

}
