package com.seba.mobile.controller;

/**
 * Created by sebastian on 07.11.15.
 */
public class LoginRespone {
    private boolean successful;
    private String reason;

    public LoginRespone() {
    }

    public LoginRespone(boolean successful, String reason) {
        this.successful = successful;
        this.reason = reason;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}

