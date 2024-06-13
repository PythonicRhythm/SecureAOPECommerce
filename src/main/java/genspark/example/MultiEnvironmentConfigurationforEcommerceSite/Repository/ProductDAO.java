package genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Repository;

import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product, Long> {
}
