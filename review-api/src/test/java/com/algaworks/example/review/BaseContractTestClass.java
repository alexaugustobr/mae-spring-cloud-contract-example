package com.algaworks.example.review;

import com.algaworks.example.review.domain.Review;
import com.algaworks.example.review.domain.ReviewRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;

@WebMvcTest
public abstract class BaseContractTestClass {

    @MockBean
    ReviewRepository reviewRepository;

    @Autowired
    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        Mockito.when(reviewRepository.save(any(Review.class))).then(r -> {
            var review = r.getArgument(0, Review.class);
            review.setId(1L);
            return review;
        });

        Mockito.when(reviewRepository.findByProductId(1L)).thenReturn(Arrays.asList(
                new Review(1L,  OffsetDateTime.now(), 5, "Adorei o produto", 1L),
                new Review(2L,  OffsetDateTime.now(), 4, "Bom custo-benefício", 1L),
                new Review(3L,  OffsetDateTime.now(), 3, "Esperava mais do produto", 1L)
        ));

        RestAssuredMockMvc.mockMvc(mockMvc);
    }

}
