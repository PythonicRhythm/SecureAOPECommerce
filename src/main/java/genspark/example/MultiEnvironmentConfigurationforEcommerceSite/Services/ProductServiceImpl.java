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

import static org.yaml.snakeyaml.nodes.Tag.STR;

@Service
public class ProductServiceImpl implements ProductService{
    public Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    public ProductDAO productDao;

    public List<Product> getAllProducts() {
        return this.productDao.findAll();
    }
<<<<<<< Updated upstream

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
=======
>>>>>>> Stashed changes

    @Override
    public List<Product> getBySellers(String seller) {
        logger.info(STR."Attempting to retreive Products by Seller: \{seller}");
        return this.productDao.findBySeller(seller);
    }

    public Product getById(long productID) {
        logger.info(STR."Attempting to Retrieve product by ID: \{productID}");
        Optional<Product> t = this.productDAO.findById(productID);
        Product product = null;
        if (t.isPresent()) {
            product = t.get();
            logger.info(STR."Successfully Retrieved product with ID: \{productID}");
        }else {
            logger.info(STR."Product Not Found by ID: \{productID}");
        }
        return product;
    }

    @Override
    public List<Product> getByName(String name) {
        //logger.info(STR."Attempting to Retreieve Products by name: \{name}");
        return this.productDao.findByName(name);
    }

    @Override
    public List<String> getByNames() {
        logger.info("Attempting to retrieve all products by Name:");
        List<Product> allProducts = this.productDao.findAll();
        List<String> productNames = new ArrayList<>() ;
        for( Product product: allProducts){
            productNames.add(product.getName());
        }
        return productNames;
    }

    @Override
    public List<String> getBySellers() {
        return this.productDao.findBySellers();
    }

    @Override
    public List<Product> getByCategory(String category) {
        List<Product> allProducts = this.productDao.findAll();
        List<Product> matchCategory = new ArrayList<Product>;
        for( Product product: allProducts){
            for(String productCategory : product.getCategories()){
                if(productCategory.toLowerCase().contains(category.toLowerCase())){
                    matchCategory.add(product);
                    break;
                }
            }
        }
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
            return List.of(this.productDao.save(products.getFirst()));
        }
        else{
            return this.productDao.saveAll(products);
        }
    }

    @Override
    public List<Product> updateProduct(List<Product> products) {
        if(products.size() == 1){
            return List.of(this.productDao.save(products.getFirst()));
        }
        else{
            return this.productDao.saveAll(products);
        }
    }

    @Override
    public String deleteProduct(long productID) {
        this.productDao.deleteByID(productID);
        return "Product Deleted Successfully";
    }


}
