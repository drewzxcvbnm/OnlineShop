package com.online.shop.application.entities;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private List<String> properties;
    @Enumerated(EnumType.STRING)
    private Category category;
    private BigDecimal price;
}
