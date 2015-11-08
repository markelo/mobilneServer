package com.seba.mobile.controller;

/**
 * Created by sebastian on 07.11.15.
 */
public class IncreaseProductRequest {
    private String name;
    private int quantity;

    public IncreaseProductRequest() {
    }

    public IncreaseProductRequest(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
