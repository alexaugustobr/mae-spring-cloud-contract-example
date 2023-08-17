package com.algaworks.example.product.api;

import java.util.List;

public interface ProductReviewClient {
	List<ReviewModel> findByProduct(Long productId);
}
