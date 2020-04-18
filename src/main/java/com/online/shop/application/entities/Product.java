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
    @Lob
    private String description;
    @ElementCollection
    private List<String> properties = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private Category category;
    private BigDecimal price;
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "product")
    private List<Purchase> purchases = new ArrayList<>();
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "product")
    private List<ProductReview> reviews = new ArrayList<>();
}
