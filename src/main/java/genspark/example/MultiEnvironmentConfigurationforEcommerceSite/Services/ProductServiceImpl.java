package genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Services;

import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Entity.Product;
import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Repository.ProductDAO;
import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Controller.ProductController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
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
        logger.info(String.format("Attempting to retreive Products by Seller: %s", seller));

        return this.productDao.findBySeller(seller);
    }

    public Product getById(long productID) {

        logger.info(String.format("Attempting to Retrieve product by ID: %d", productID));
        Optional<Product> t = this.productDao.findById(productID);
        Product product = null;
        if (t.isPresent()) {
            product = t.get();
            logger.info(String.format("Successfully Retrieved product with ID: %d", productID));
        }else {
            logger.info(String.format("Product Not Found by ID: %d", productID));

        }
        return product;
    }

    @Override
    public List<Product> getByName(String name) {
        //logger.info(STR."Attempting to Retrieve Products by name: \{name}");
        return this.productDao.findByName(name);
    }

    @Override
    public List<Product> getByNames() {
        logger.info("Attempting to retrieve all products by Name:");
        return this.productDao.findProductsByNameSort();
    }

    @Override
    public List<Product> getBySellers() {
        logger.info("Attempting to retrieve all products by Seller:");
        return this.productDao.findProductsBySellerSort();
    }

    @Override
    public List<Product> getByCategory(String category) {
        List<Product> allProducts = this.productDao.findAll();
        List<Product> matchCategory = new ArrayList<>();

        for( Product product: allProducts){
            for(String productCategory : product.getCategories()){
                if(productCategory.toLowerCase().contains(category.toLowerCase())){
                    matchCategory.add(product);
                    break;
                }
            }
        }
        return matchCategory;
    }

    @Override
    public List<Product> getBySorted() {
        logger.info("Attempting to retrieve sorted products");

        return this.productDao.findProductsByNameSort();
    }

    @Override
    public List<Product> addMutlipleProduct(List<Product> products, int amount) {
        List<Product> newProducts = new ArrayList<>();
        for(Product product: this.getAllProducts()){
            for(int i =0; i<amount; i++){
                newProducts.add(product);
            }
        }
        return this.productDao.saveAll(newProducts);
    }

    @Override
    public List<Product> addProduct(List<Product> products) {
        if(products.size() == 1){
            return List.of(this.productDao.save(products.get(0)));
        }
        else{
            return this.productDao.saveAll(products);
        }
    }

    @Override
    public List<Product> updateProduct(List<Product> products) {
        if(products.size() == 1){
            return List.of(this.productDao.save(products.get(0)));
        }
        else{
            return this.productDao.saveAll(products);
        }
    }

    @Override
    public String deleteProduct(long productID) {
        this.productDao.deleteById(productID);
        return "Product Deleted Successfully";
    }


}
