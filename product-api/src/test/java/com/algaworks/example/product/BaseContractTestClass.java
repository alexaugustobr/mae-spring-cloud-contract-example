package com.algaworks.example.product;

import com.algaworks.example.product.api.controller.ProductController;
import com.algaworks.example.product.domain.Product;
import com.algaworks.example.product.domain.ProductRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

//@ExtendWith(MockitoExtension.class)
public class BaseContractTestClass {

    private ProductController productController;
    private ProductRepository productRepository;

    public static Long EXISTING_PRODUCT_ID = 1L;
    public static Long NON_EXISTING_PRODUCT_ID = 9999L;

    @BeforeEach
    public void setup() {
        productRepository = Mockito.mock(ProductRepository.class);
        productController = new ProductController(productRepository, Mockito.mock());

        lenient().when(productRepository.saveAndFlush(any(Product.class))).then(s -> {
            var product = s.getArgument(0, Product.class);
            product.setId(EXISTING_PRODUCT_ID);
            return product;
        });
        lenient().when(productRepository.findById(1L)).thenReturn(
                Optional.of(new Product(EXISTING_PRODUCT_ID, "Notebook Gamer RX76", new BigDecimal("1999.9")))
        );
        lenient().when(productRepository.findById(NON_EXISTING_PRODUCT_ID)).thenReturn(Optional.empty());

        var standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(productController);
        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
    }
}
