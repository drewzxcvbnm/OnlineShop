package com.online.shop.application.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue
    private Long id;
    private String img;
    private String displayName;
    @Builder.Default
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "category")
    private List<Product> products = new ArrayList<>();

    public Category(String img, String displayName) {
        this.img = img;
        this.displayName = displayName;
    }
}
