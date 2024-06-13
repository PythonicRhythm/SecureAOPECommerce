package genspark.example.MultiEnvironmentConfigurationforEcommerceSite;

import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Entity.Product;
import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Repository.ProductDAO;
import genspark.example.MultiEnvironmentConfigurationforEcommerceSite.Services.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.mockito.Mockito.mock;

@SpringBootTest
@ActiveProfiles("test")
class MultiEnvironmentConfigurationforEcommerceSiteApplicationTests {

	@Test
	void testAddProduct() {
		ProductDAO pd = mock(ProductDAO.class);
		ProductServiceImpl ps = new ProductServiceImpl();
	}

}
