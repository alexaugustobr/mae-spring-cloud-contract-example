package com.algaworks.example.product.api;

import java.time.OffsetDateTime;

public class ReviewModel {

	private Long id;
	private OffsetDateTime createdAt = OffsetDateTime.now();
	private Integer grade;
	private String comment;
	private Long productId;

	public ReviewModel() {
	}

	public ReviewModel(Integer grade, String comment, Long productId) {
		this.grade = grade;
		this.comment = comment;
		this.productId = productId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
}
