package com.algaworks.example.product.api.model;

import com.algaworks.example.product.domain.Product;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductInputModel {

	@NotBlank
	private String name;

	@NotNull
	@DecimalMin("1.00")
	@DecimalMax("100000.00")
	private BigDecimal price;

	public ProductInputModel() {
	}

	public ProductInputModel(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
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

	public Product toDomain() {
		return new Product(this.getName(), this.getPrice());
	}
}