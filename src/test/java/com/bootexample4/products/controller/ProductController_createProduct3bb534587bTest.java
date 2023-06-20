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

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductController_getAllProducts6e8dbd3e77Test {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testGetAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Test Product 1", 100));
        products.add(new Product("Test Product 2", 200));
        productRepository.saveAll(products);

        ResponseEntity<List<Product>> response = restTemplate.getForEntity("/products", List.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products.size(), response.getBody().size());
    }
}