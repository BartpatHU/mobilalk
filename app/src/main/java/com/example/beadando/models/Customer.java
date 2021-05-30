package com.example.beadando.models;

public class Customer {

    private String id;
    private String href;
    private String name;
    private String status;
    private String statusReason;

    public Customer(){}

    public Customer(String id, String href, String name, String status, String statusReason){
        this.id = id;
        this.href = href;
        this.name = name;
        this.status = status;
        this.statusReason = statusReason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusReason() {
        return statusReason;
    }

    public void setStatusReason(String statusReason) {
        this.statusReason = statusReason;
    }
}
