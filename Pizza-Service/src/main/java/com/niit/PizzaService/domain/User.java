package com.niit.PizzaService.domain;

import java.util.List;

public class User {

    private String email;
    private String password;
    private List<Pizza> pizza;

    public User() {
    }

    public User(String email, String password, List<Pizza> pizza) {
        this.email = email;
        this.password = password;
        this.pizza = pizza;
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

    public List<Pizza> getPizza() {
        return pizza;
    }

    public void setPizza(List<Pizza> pizza) {
        this.pizza = pizza;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", pizza=" + pizza +
                '}';
    }
}
