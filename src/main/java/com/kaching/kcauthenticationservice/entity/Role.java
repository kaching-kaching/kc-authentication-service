package com.kaching.kcauthenticationservice.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "role")
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigDecimal id;

    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @ManyToMany(mappedBy = "roles", cascade = CascadeType.PERSIST)
    private List<Permission> permissions;

    @ToString.Exclude
    @OneToMany(mappedBy = "role", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<User> user;
}
