package com.algaworks.example.review.api.controller;

import com.algaworks.example.review.api.model.ReviewInput;
import com.algaworks.example.review.api.model.ReviewModel;
import com.algaworks.example.review.domain.Review;
import com.algaworks.example.review.domain.ReviewRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products/{productId}/reviews")
public class ReviewController {
	
	private final ReviewRepository reviewRepository;

	public ReviewController(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	@GetMapping
	public List<ReviewModel> findByProduct(@PathVariable Long productId) {
		return reviewRepository.findByProductId(productId)
				.stream()
				.map(ReviewModel::of)
				.collect(Collectors.toList());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ReviewModel addReview(@PathVariable Long productId, @RequestBody  @Valid ReviewInput input) {
		var review = new Review(input.getGrade(), input.getComment(), productId);
		return ReviewModel.of(reviewRepository.save(review));
	}
}
