package genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Repository;

import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductDAO extends JpaRepository<Product, Long> {

    @Query("SELECT p from Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> findByName(String name);

    @Query("SELECT p from Product p WHERE LOWER(p.seller) LIKE LOWER(CONCAT('%', :seller, '%'))")
    List<Product> findBySeller(String seller);

    // Custom query - gets all product with each name in the list of names
    @Query("SELECT DISTINCT product.name FROM Product product")
    List<String> findByNames();

    @Query("SELECT DISTINCT product.sellers FROM Product product")
    List<String> findBySellers();

    //Sort by name / Sort by sellers
    @Query(value = "SELECT * from product ORDER BY name", nativeQuery = true)
    List<Product> findProductsByNameSort();

    @Query(value = "SELECT * from product ORDER BY seller", nativeQuery = true)
    List<Product> findProductsBySellerSort();


}
