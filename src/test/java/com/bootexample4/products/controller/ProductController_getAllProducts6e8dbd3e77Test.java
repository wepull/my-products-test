import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductController_getAllProducts6e8dbd3e77Test {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void getAllProducts_ReturnsAllProducts() {
        // Given
        Product product1 = new Product("Product 1", 100);
        Product product2 = new Product("Product 2", 200);
        productRepository.save(product1);
        productRepository.save(product2);

        // When
        ResponseEntity<List<Product>> response = restTemplate.getForEntity("/products", List.class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().size());
        assertEquals(product1.getName(), response.getBody().get(0).getName());
        assertEquals(product2.getName(), response.getBody().get(1).getName());
    }

    @Test
    public void getAllProducts_WhenNoProductsExist_ReturnsEmptyList() {
        // Given
        productRepository.deleteAll();

        // When
        ResponseEntity<List<Product>> response = restTemplate.getForEntity("/products", List.class);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }
}