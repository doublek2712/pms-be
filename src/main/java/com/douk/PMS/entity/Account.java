package com.douk.PMS.entity;

import jakarta.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private AccountRole accountRole;


    public Account() {
    }

    public Account(String email, String password, AccountRole accountRole) {

        this.email = email;
        this.password = password;
        this.accountRole = accountRole;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountRole getAccountRole() {
        return accountRole;
    }

    public void setAccountRole(AccountRole accountRole) {
        this.accountRole = accountRole;
    }
}
