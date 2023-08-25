package com.algaworks.example.product;

import com.algaworks.example.product.api.client.ProductReviewClient;
import com.algaworks.example.product.api.controller.ProductController;
import com.algaworks.example.product.api.exceptionhandler.ApiExceptionHandler;
import com.algaworks.example.product.domain.Product;
import com.algaworks.example.product.domain.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

//@SpringBootTest
//@WebMvcTest(controllers = ProductController.class)
@SpringBootTest
//@AutoConfigureMockMvc
//@JsonTest
//@AutoConfigureJson
//@AutoConfigureJsonTesters
@DirtiesContext /*Reseta beans*/
public class BaseContractIntegrationTestClass {

//    @Autowired
//    private ProductController productController;
//
//    @MockBean
//    private ProductRepository productRepository;
//
//    @MockBean
//    private ProductReviewClient productReviewClient;

//    @Autowired
//    private MockMvc mockMvc;

//    @Autowired
//    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context; //Carrega exception handler? mas nao o json mapper

//    public static Long EXISTING_PRODUCT_ID = 1L;
//    public static Long NON_EXISTING_PRODUCT_ID = 9999L;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.webAppContextSetup(context);

//        lenient().when(productRepository.saveAndFlush(any(Product.class))).then(s -> {
//            var product = s.getArgument(0, Product.class);
//            product.setId(EXISTING_PRODUCT_ID);
//            return product;
//        });
//        lenient().when(productRepository.findById(1L)).thenReturn(
//                Optional.of(new Product(EXISTING_PRODUCT_ID, "Notebook Gamer RX76", new BigDecimal("1999.9")))
//        );
//        lenient().when(productRepository.findById(NON_EXISTING_PRODUCT_ID)).thenReturn(Optional.empty());


//        RestAssuredMockMvc.standaloneSetup(
//                MockMvcBuilders
//                        .standaloneSetup(productController)
//                        .setControllerAdvice(new ApiExceptionHandler(Mockito.mock()))
//                        .setMessageConverters(new MappingJackson2HttpMessageConverter(objectMapper))
//        );
//        var mockMvcBuilder = MockMvcBuilders.webAppContextSetup(context);
//        RestAssuredMockMvc.webAppContextSetup(context);

//        var standaloneMockMvcBuilder = MockMvcBuilders.standaloneSetup(productController);
//        RestAssuredMockMvc.standaloneSetup(standaloneMockMvcBuilder);
    }
}
