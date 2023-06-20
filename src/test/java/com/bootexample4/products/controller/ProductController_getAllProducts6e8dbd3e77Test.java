import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.bootexample4.products.model.Product;
import com.bootexample4.products.repository.ProductRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductController_getAllProducts6e8dbd3e77Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void getAllProducts_returnsProducts() throws Exception {
        // Given
        List<Product> products = new ArrayList<>();
        products.add(new Product("1", "Product 1", 100.0));
        products.add(new Product("2", "Product 2", 200.0));

        Mockito.when(productRepository.findAll()).thenReturn(products);

        // When
        ResponseEntity<List<Product>> response = mockMvc.perform(get("/products"))
                .andReturn().getResponse().getBody();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products, response.getBody());
    }

    @Test
    public void getAllProducts_returnsEmptyList() throws Exception {
        // Given
        Mockito.when(productRepository.findAll()).thenReturn(new ArrayList<>());

        // When
        ResponseEntity<List<Product>> response = mockMvc.perform(get("/products"))
                .andReturn().getResponse().getBody();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(new ArrayList<>(), response.getBody());
    }
}