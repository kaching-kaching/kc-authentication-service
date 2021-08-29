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
@Table(name = "permission")
public class Permission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigDecimal id;

    @Column(name = "name")
    private String name;

    @ToString.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
       name = "role_permission",
       joinColumns = @JoinColumn(name = "permission_id"),
       inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
}
