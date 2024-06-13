package genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Repository;

import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDAO extends JpaRepository<Product, Long> {

    @Query("SELECT p from Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> findByName(Product name);

    @Query("SELECT p from Product p WHERE LOWER(p.seller) LIKE LOWER(CONCAT('%', :seller, '%'))")
    List<Product> findBySeller(List<Product> seller);

    // Custom query - gets all product with each name in the list of names
    @Query("SELECT product FROM Product product WHERE product.name IN :names")
    List<String> findByNames(@Param("names") List<String> names);

    @Query("SELECT DISTINCT product FROM Product product WHERE product.seller IN :sellers")
    List<String> findBySellers(@Param("sellers") List<String> sellers);

    //Sort by name / Sort by sellers
    @Query(value = "SELECT * from product ORDER BY name", nativeQuery = true)
    List<Product> findProductsByNameSort();

    @Query(value = "SELECT * from product ORDER BY seller", nativeQuery = true)
    List<Product> findProductsBySellerSort();


}
