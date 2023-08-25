package com.algaworks.example.review.api;

import com.algaworks.example.review.domain.Review;
import com.algaworks.example.review.domain.ReviewRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/{productId}/reviews")
public class ReviewController {
	
	private final ReviewRepository reviewRepository;

	public ReviewController(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	@GetMapping
	public List<Review> findByProduct(@PathVariable Long productId) {
		return reviewRepository.findByProductId(productId);
	}

	@PostMapping
	public Review addReview(@PathVariable Long productId, @RequestBody ReviewInput input) {
		var review = new Review(input.getGrade(), input.getComment(), productId);
		return reviewRepository.save(review);
	}
}
