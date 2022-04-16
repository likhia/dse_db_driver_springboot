package com.example.db.driverdemo.service;

public class Status {

    public static String SUCCESS = "1";
    public static String ERROR = "-1";
    public static String FAIL = "0";

    private String status;

    public Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    } 


    
}
