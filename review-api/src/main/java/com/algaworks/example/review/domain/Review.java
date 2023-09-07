package com.algaworks.example.review.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.OffsetDateTime;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private OffsetDateTime createdAt = OffsetDateTime.now();
	private Integer grade;
	private String comment;
	private String name;
	private Long productId;

	public Review() {
	}

	public Review(Long id, OffsetDateTime createdAt, Integer grade, String comment, String name, Long productId) {
		this.id = id;
		this.createdAt = createdAt;
		this.grade = grade;
		this.comment = comment;
		this.name = name;
		this.productId = productId;
	}

	public Review(Integer grade, String comment, String name, Long productId) {
		this.grade = grade;
		this.comment = comment;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}
}
