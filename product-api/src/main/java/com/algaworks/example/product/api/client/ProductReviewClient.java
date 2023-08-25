package com.algaworks.example.product.api.client;

import com.algaworks.example.product.api.model.ReviewModel;

import java.util.List;

public interface ProductReviewClient {
	List<ReviewModel> findByProduct(Long productId);
}
