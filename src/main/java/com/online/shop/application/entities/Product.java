package com.online.shop.application.entities;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private BigDecimal price;
    @Lob
    private String description;
    @ElementCollection
    private List<String> properties = new ArrayList<>();
    @JoinColumn
    @ManyToOne(optional = false)
    private Category category;
    @Builder.Default
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "product")
    private List<Purchase> purchases = new ArrayList<>();
    @Builder.Default
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "product")
    private List<ProductReview> reviews = new ArrayList<>();
}
