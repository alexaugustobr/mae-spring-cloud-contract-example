package com.algaworks.example.product.api;

import com.algaworks.example.product.domain.Product;
import com.algaworks.example.product.domain.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private final ProductRepository products;
	private final ProductReviewClient reviews;

	public ProductController(ProductRepository products, ProductReviewClient reviews) {
		this.products = products;
		this.reviews = reviews;
	}

	@GetMapping
	public List<ProductSummaryModel> findAll() {
		return products.findAll()
				.stream()
				.map(ProductSummaryModel::of)
				.collect(Collectors.toList());
	}

	@GetMapping("/{productId}")
	public ProductModel findById(@PathVariable Long productId) {
		return products.findById(productId)
				.map(ProductModel::of)
				.map(addReviews(productId))
				.orElseThrow(ResourceNotFoundException::new);
	}

	private Function<ProductModel, ProductModel> addReviews(Long productId) {
		return model -> model.addReviews(reviews.findByProduct(productId));
	}
}
