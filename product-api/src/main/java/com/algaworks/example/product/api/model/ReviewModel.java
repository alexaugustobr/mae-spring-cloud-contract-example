package com.algaworks.example.product.api.model;

import java.time.OffsetDateTime;

public class ReviewModel {

	private Long id;
	private Integer grade;
	private String comment;
	private OffsetDateTime createdAt;
	private String name;

	public ReviewModel() {
	}

	public ReviewModel(Long id, Integer grade, String comment, OffsetDateTime createdAt, String name) {
		this.id = id;
		this.grade = grade;
		this.comment = comment;
		this.createdAt = createdAt;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public OffsetDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(OffsetDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
