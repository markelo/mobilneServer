package com.seba.mobile.entities;

import com.google.common.base.Objects;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by sebastian on 31.10.15.
 */
public class User {
    private long id;
    private String login;
    private String password;
    private List<Product> products;

    public User() {
        this.products = new LinkedList<>();
    }

    public User(long id, String login, String password) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.products = new LinkedList<>();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String msg) {
        this.login = msg;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equal(id, user.id) &&
                Objects.equal(login, user.login) &&
                Objects.equal(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, login, password);
    }
}
