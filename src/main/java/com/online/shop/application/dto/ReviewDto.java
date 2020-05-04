package com.online.shop.application.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
public class ReviewDto {
    private String author;
    private LocalDate dateOfCreation;
    @NotEmpty
    private String content;
    @Min(1)
    @Max(10)
    private Integer rating;
}
