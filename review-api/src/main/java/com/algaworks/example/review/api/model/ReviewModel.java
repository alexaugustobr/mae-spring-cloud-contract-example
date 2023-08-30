package com.algaworks.example.review.api.model;

import com.algaworks.example.review.domain.Review;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReviewModel {

    private Long id;
    private Integer grade;
    private String comment;

    public ReviewModel(Long id, Integer grade, String comment) {
        this.id = id;
        this.grade = grade;
        this.comment = comment;
    }

    public static ReviewModel of(Review review) {
        return new ReviewModel(review.getId(), review.getGrade(), review.getComment());
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
}
