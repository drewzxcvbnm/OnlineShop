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
public class User {

    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Authority authority;
    @OneToOne(optional = true, cascade = CascadeType.ALL)
    private UserInfo userInfo;
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<ProductReview> productReviews = new ArrayList<>();
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Order> orders = new ArrayList<>();
    @Lob
    private byte[] profilePicture;
}
