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
public class UserInfo {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String address;
    private String phoneNumber;
    private String bankAccount;
}
