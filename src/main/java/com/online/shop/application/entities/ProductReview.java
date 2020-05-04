package com.online.shop.application.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder
@ToString(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class ProductReview {

    @Id
    @GeneratedValue
    private Long id;
    @Lob
    private String content;
    private Integer rating;
    private LocalDate dateOfCreation;
    @ManyToOne
    private Product product;
    @ManyToOne
    @JoinColumn
    private User user;
}
