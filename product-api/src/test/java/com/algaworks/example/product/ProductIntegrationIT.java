package com.algaworks.example.product;

import com.algaworks.example.product.api.model.ReviewModel;
import com.algaworks.example.product.domain.Product;
import com.algaworks.example.product.domain.ProductRepository;
import com.algaworks.example.product.security.BasicSecurityConfig;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.internal.http.HTTPBuilder;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import io.restassured.response.ResponseOptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static com.toomuchcoding.jsonassert.JsonAssertion.assertThatJson;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
@ActiveProfiles("security-basic")
@AutoConfigureStubRunner(ids = {"com.algaworks.example:review-api:+:8082"}, stubsMode = StubRunnerProperties.StubsMode.LOCAL)
public class ProductIntegrationIT {

    @LocalServerPort
    private int port;

    @MockBean
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.port = port;
        RestAssured.basePath = "/products";
        //Já que é integração vamos testar tudo, unico mock é o BD, por questões de facilitar o mock de dependnencias externas
        Mockito.when(productRepository.findById(1L)).thenReturn(
                Optional.of(new Product(1L, "Notebook Gamer RX76", new BigDecimal("1999.9")))
        );
    }

    @Test
    public void shouldReturnFoundProductByIdWithComments()  throws Exception {
        // given:
        var request = given().auth().basic("admin", "123456");

        // when:
        var response = given().spec(request).get("/1");

        // then:
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.header("Content-Type")).matches("application/json.*");

        // and:
        DocumentContext parsedJson = JsonPath.parse(response.getBody().asString());
        assertThatJson(parsedJson).array("['reviews']").hasSize(3);

        // and:
        assertThat(parsedJson.read("$.id", Integer.class)).isEqualTo(1);
        assertThat(parsedJson.read("$.name", String.class)).isEqualTo("Notebook Gamer RX76");
        assertThat(parsedJson.read("$.price", String.class)).isEqualTo("1999.9");
    }
}

