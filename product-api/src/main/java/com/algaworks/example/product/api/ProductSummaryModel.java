package com.algaworks.example.product.api;

import com.algaworks.example.product.domain.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductSummaryModel {

	private Long id;
	private String name;
	private BigDecimal price;

	public ProductSummaryModel() {
	}

	public ProductSummaryModel(Long id, String name, BigDecimal price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public static ProductSummaryModel of(Product product) {
		return new ProductSummaryModel(
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

}
