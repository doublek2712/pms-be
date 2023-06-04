package com.douk.PMS.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class AccountDTO {
    private String email;
    private String password;


    public AccountDTO( String email, String password) {
        this.email = email;
        this.password = password;
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

    @Override
    public String toString() {
        return "AccountDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
