package com.algaworks.example.review.database;

import com.algaworks.example.review.domain.Review;
import com.algaworks.example.review.domain.ReviewRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseDataLoader implements ApplicationRunner {

    private final ReviewRepository reviewRepository;

    public DatabaseDataLoader(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (isDatabaseEmpty()) {
            loadDefaultData();
        }
    }

    private void loadDefaultData() {
        var reviews = List.of(
                new Review(4, "Ótimo produto.", 1L),
                new Review(5, "Superou as expectativas.", 1L),
                new Review(1, "Não gostei.", 1L),
                new Review(2, "Não tem um bom custo benefício.", 2L),
                new Review(3, "Gostei, mas poderia ser mais barato..", 2L)
        );
        reviewRepository.saveAll(reviews);
    }

    private boolean isDatabaseEmpty() {
        return reviewRepository.count() == 0;
    }
}
