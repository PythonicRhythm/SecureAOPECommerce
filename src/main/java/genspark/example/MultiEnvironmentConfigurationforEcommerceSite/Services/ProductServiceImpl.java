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
        return this.productDao.findBySeller(seller);
    }

    public Product getById(long productID) {

        Optional<Product> t = this.productDao.findById(productID);
        Product product = null;
        if (t.isPresent()) {
            product = t.get();
            }
        return product;
    }

    @Override
    public List<Product> getByName(String name) {
        return this.productDao.findByName(name);
    }

    @Override
    public List<Product> getByNames() {

        return this.productDao.findProductsByNameSort();
    }

    @Override
    public List<Product> getBySellers() {
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
    public List<Product> getBySortedName() {

        return this.productDao.findProductsByNameSort();
    }

    @Override
    public List<Product> getBySortedSeller() {
        return this.productDao.findProductsBySellerSort();
    }

    @Override
    public List<Product> addMutlipleProduct(List<Product> products, int amount) {
        List<Product> addedProducts = new ArrayList<>();
        // Ensure num is positive and greater than 0
        if (amount <= 0) {
            throw new IllegalArgumentException("Number of products to add must be greater than 0");
        }
        for (Product product : products) {
            for (int i = 0; i < amount; i++) {
                Product newProduct = new Product();
                newProduct.setName(product.getName());

                newProduct.setSeller(product.getSeller());
                newProduct.setPrice(product.getPrice());
                newProduct.setDescription(product.getDescription());
                newProduct.setCategories(product.getCategories());
                addedProducts.add(productDao.save(newProduct));
            }
        }
        return addedProducts;
    }

    @Override
    public List<Product> addProducts(List<Product> products) {
        if(products.size() == 1){
            return List.of(this.productDao.save(products.get(0)));
        }
        else{
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
