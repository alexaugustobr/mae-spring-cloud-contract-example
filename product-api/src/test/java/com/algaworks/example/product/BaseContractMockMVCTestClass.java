package com.algaworks.example.product;

import com.algaworks.example.product.api.client.ProductReviewClient;
import com.algaworks.example.product.api.controller.ProductController;
import com.algaworks.example.product.api.exceptionhandler.ApiExceptionHandler;
import com.algaworks.example.product.domain.Product;
import com.algaworks.example.product.domain.ProductRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@SpringBootTest
public class BaseContractMockMVCTestClass {

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductReviewClient productReviewClient;

    @Autowired
    private ProductController productController;

    @Autowired
    private WebApplicationContext context;

    public static Long EXISTING_PRODUCT_ID = 1L;
    public static Long NON_EXISTING_PRODUCT_ID = 9999L;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(context);

        lenient().when(productReviewClient.findByProduct(1L)).thenReturn(new ArrayList<>());

        lenient().when(productRepository.saveAndFlush(any(Product.class))).then(s -> {
            var product = s.getArgument(0, Product.class);
            if (product.getId() == null) {
                product.setId(EXISTING_PRODUCT_ID);
            }
            return product;
        });
        lenient().when(productRepository.findById(1L)).thenReturn(
                Optional.of(new Product(EXISTING_PRODUCT_ID, "Notebook Gamer RX76", new BigDecimal("1999.9")))
        );
        lenient().when(productRepository.findById(NON_EXISTING_PRODUCT_ID)).thenReturn(Optional.empty());
        lenient().when(productRepository.findAll())
                .thenReturn(
                    Arrays.asList(
                        new Product(EXISTING_PRODUCT_ID, "Notebook Gamer RX76", new BigDecimal("1999.9")),
                        new Product(2L, "Monitor 22p", new BigDecimal("1500.0")),
                        new Product(3L, "Microfone FT342", new BigDecimal("300.0"))
                )
        );
    }
}
