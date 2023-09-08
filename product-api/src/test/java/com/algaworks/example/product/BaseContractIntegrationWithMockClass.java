package com.algaworks.example.product;

import com.algaworks.example.product.api.client.ProductReviewClient;
import com.algaworks.example.product.domain.Product;
import com.algaworks.example.product.domain.ProductRepository;
import com.algaworks.example.product.security.BasicSecurityConfig;
import io.restassured.RestAssured;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@ExtendWith(SpringExtension.class)
@WebMvcTest(excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = ProductReviewClient.class))
@ActiveProfiles("security-basic") //Força ativação do profile
@Import(BasicSecurityConfig.class) //Importa configuração pois o @WebMvcTest não está carregando, dando erro de CSRF no post
@WithMockUser(username = "admin", password = "123456", roles = "ADMIN")
public class BaseContractIntegrationWithMockClass {

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private ProductReviewClient productReviewClient;

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private WebApplicationContext context;

//    public static Long EXISTING_PRODUCT_ID = 1L;
//    public static Long NON_EXISTING_PRODUCT_ID = 9999L;

    public String generateToken() {
        return UUID.randomUUID().toString();
    }

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssuredMockMvc.mockMvc(mockMvc);

        Mockito.when(productReviewClient.findByProduct(1L)).thenReturn(new ArrayList<>());

        Mockito.when(productRepository.saveAndFlush(any(Product.class))).then(s -> {
            var product = s.getArgument(0, Product.class);
            if (product.getId() == null) {
                product.setId(1L);
            }
            return product;
        });

        Mockito.when(productRepository.findById(1L)).thenReturn(
                Optional.of(new Product(1L, "Notebook Gamer RX76", new BigDecimal("1999.9")))
        );
        Mockito.when(productRepository.findById(9999L)).thenReturn(Optional.empty());

        var products = Arrays.asList(
                new Product(1L, "Notebook Gamer RX76", new BigDecimal("1999.9")),
                new Product(2L, "Monitor 22p", new BigDecimal("1500.0")),
                new Product(3L, "Microfone FT342", new BigDecimal("300.0"))
        );

        Mockito.when(productRepository.findAll(any(Pageable.class))).thenAnswer(a -> {
            var pageable = a.getArgument(0, Pageable.class);
            List<Product> productsOnPage;
            if (products.size() > pageable.getPageSize()) {
                productsOnPage = products.subList(0, pageable.getPageSize());
            } else {
                productsOnPage = products;
            }
            return new PageImpl<>(productsOnPage, pageable, products.size());
        });
    }
}


