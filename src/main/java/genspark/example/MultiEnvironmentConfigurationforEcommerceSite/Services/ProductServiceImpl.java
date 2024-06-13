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
        logger.info("Attempting to retrieve all products");
        return this.productDao.findAll();
    }

    @Override
    public List<Product> getBySellers(String seller) {
        logger.info(String.format("Attempting to retrieve Products by Seller: %s", seller));
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
        logger.info(String.format("Attempting to Retrieve Products by name: %s", name));
        return this.productDao.findByName(name);
    }

    @Override
    public List<String> getByNames() {
        logger.info("Attempting to retrieve all products by Name:");
        return this.productDao.findByNames();
    }

    @Override
    public List<String> getBySellers() {
        logger.info("Attempting to retrieve all products by Seller:");
        return this.productDao.findBySellers();
    }

    @Override
    public List<Product> getByCategory(String category) {
        logger.info(String.format("Attempting to Retrieve Products by category: %s", category));
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
    public List<Product> getBySortedName() {
        logger.info("Attempting to retrieve sorted products");

        return this.productDao.findProductsByNameSort();
    }

    @Override
    public List<Product> getBySortedSeller() {
        logger.info("Attempting to retrieve sorted products");

        return this.productDao.findProductsBySellerSort();
    }

    @Override
    public List<Product> addMutlipleProduct(List<Product> products, int amount) {
        logger.info(String.format("Attempting to add Products: %s times %d", products,amount));
        List<Product> newProducts = new ArrayList<>();
        for(Product product: products){
            for(int i = 0; i < amount; i++){

                newProducts.add(product);
                this.productDao.save(product);
            }
        }
        return newProducts;
    }

    @Override
    public List<Product> addProducts(List<Product> products) {
        if(products.size() == 1){
            logger.info("adding a single product");
            return List.of(this.productDao.save(products.get(0)));
        }
        else{
            logger.info("adding a multiple products");
            return this.productDao.saveAll(products);
        }
    }
    @Override
    public Product addProduct(Product product) {
        return this.productDao.save(product);
    }

    @Override
    public List<Product> updateProduct(List<Product> products) {
        if(products.size() == 1){
            logger.info("updating a single product");
            return List.of(this.productDao.save(products.get(0)));
        }
        else{
            logger.info("updating a multiple products");
            return this.productDao.saveAll(products);
        }
    }

    @Override
    public String deleteProduct(long productID) {
        logger.info(String.format("Attempting to delete Product by product ID: %s", productID));
        this.productDao.deleteById(productID);
        return "Product Deleted Successfully";
    }


}
