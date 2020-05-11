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
@Table(name = "\"ORDER\"")
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private String address;
    private String bankAccount;
    @JoinColumn
    @ManyToOne(optional = true)
    private User user;
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<Purchase> purchases = new ArrayList<>();

    public void setPurchases(List<Purchase> purchases) {
        this.purchases.clear();
        this.purchases.addAll(purchases);
        purchases.forEach(p -> p.setOrder(this));
    }

}
