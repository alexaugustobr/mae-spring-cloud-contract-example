package com.algaworks.example.product.api;

import com.algaworks.example.product.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductModel {

	private Long id;
	private String name;
	private BigDecimal price;
	
	public List<ReviewModel> reviews = new ArrayList<>();

	public ProductModel() {
	}

	public ProductModel(Long id, String name, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public static ProductModel of(Product product) {
		return new ProductModel(
				product.getId(),
				product.getName(),
				product.getPrice()
		);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<ReviewModel> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewModel> reviews) {
		this.reviews = reviews;
	}

	public ProductModel addReviews(List<ReviewModel> reviews) {
		this.setReviews(reviews);
		return this;
	}
}
