package com.online.shop.application.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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
    @CreationTimestamp
    private LocalDate dateOfCreation;
    @JoinColumn
    @ManyToOne(optional = false)
    private Product product;
    @JoinColumn
    @ManyToOne(optional = false)
    private User user;
}
