package com.seba.mobile.controller;

/**
 * Created by sebastian on 07.11.15.
 */
public class ActionResponse {
    private boolean successful;
    private String reason;
    private Object data;

    public ActionResponse(boolean successful, String reason, Object data) {
        this.successful = successful;
        this.reason = reason;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
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
