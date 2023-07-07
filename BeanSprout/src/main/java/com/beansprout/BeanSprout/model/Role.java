package com.beansprout.BeanSprout.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int roleID;

    @Column
    String roleName;

    @ManyToMany(mappedBy="roles")
    private List<User> users;

}
