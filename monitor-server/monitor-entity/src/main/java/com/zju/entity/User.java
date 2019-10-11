package com.zju.entity;

import javax.persistence.*;

@Entity
@Table(name = "T_MACHINE_USER")
public class User {

    @Id
    @Column(name = "C_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "C_NAME")
    private String name;

    @Column(name = "C_PASSWORD")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
