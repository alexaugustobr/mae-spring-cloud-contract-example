package com.algaworks.example.product.api.controller;

import com.algaworks.example.product.api.client.ProductReviewClient;
import com.algaworks.example.product.api.exception.ResourceNotFoundException;
import com.algaworks.example.product.api.model.ProductInputModel;
import com.algaworks.example.product.api.model.ProductModel;
import com.algaworks.example.product.api.model.ProductSummaryModel;
import com.algaworks.example.product.domain.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
	public Page<ProductSummaryModel> findAll(@PageableDefault Pageable pageable) {
		return products.findAll(pageable).map(ProductSummaryModel::of);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProductModel create(@RequestBody ProductInputModel input) {
		var product = products.saveAndFlush(input.toDomain());
		return ProductModel.of(product);
	}

	@GetMapping("/{productId}")
	public ProductModel findById(@PathVariable Long productId) {
		return products.findById(productId)
				.map(ProductModel::of)
				.map(addReviews(productId))
				.orElseThrow(ResourceNotFoundException::new);
	}

	@PutMapping("/{productId}")
	public ProductModel update(@PathVariable Long productId, @RequestBody ProductInputModel input) {
		var product = products.findById(productId).orElseThrow(ResourceNotFoundException::new);
		product.setName(input.getName());
		product.setPrice(input.getPrice());
		products.saveAndFlush(input.toDomain());
		return ProductModel.of(product);
	}

	@DeleteMapping("/{productId}")
	public void delete(@PathVariable Long productId) {
		products.findById(productId).orElseThrow(ResourceNotFoundException::new);
		products.deleteById(productId);
	}

	private Function<ProductModel, ProductModel> addReviews(Long productId) {
		return model -> model.addReviews(reviews.findByProduct(productId));
	}
}
