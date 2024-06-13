package genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productid;
    private String name;
    private String description;
    private double price;
    private String seller;
    private String[] categories;

    public Long getProductid() {
        return productid;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getSeller() {
        return seller;
    }

    public String[] getCategories() {
        return categories;
    }
}
