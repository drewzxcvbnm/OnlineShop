package com.online.shop.application.entities;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@ToString(of = {"id"})
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn
    private Order order;
    @JoinColumn
    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    public Purchase(Order order, Product product) {
        this.order = order;
        this.product = product;
    }
}
