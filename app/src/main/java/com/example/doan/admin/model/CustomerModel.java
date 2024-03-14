package com.example.doan.admin.model;

import androidx.annotation.NonNull;

public class CustomerModel {
    private int id;
    private String CusName;
    private String Password;

    //constructors


    public CustomerModel(int id, String cusName, String password) {
        this.id = id;
        CusName = cusName;
        Password = password;
    }
    public CustomerModel(String name, String password) {
        // Set a default value for ID when it's not provided
        this.id = -1;
        CusName = name;
        Password = password;
    }

    public CustomerModel(){

    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCusName() {
        return CusName;
    }

    public void setCusName(String cusName) {
        CusName = cusName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
