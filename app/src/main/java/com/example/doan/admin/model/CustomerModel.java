package com.example.doan.admin.model;

import androidx.annotation.NonNull;

public class CustomerModel {
    private int CustomerID;
    private String CustomerName;
    private String Number;
    private String Avatar;
    private String BirthDate;
    private String Address;
    private int UserID; // Foreign key reference to User

    public CustomerModel(int customerID, String customerName, String number, String avatar, String birthDate, String address, int userID) {
        CustomerID = customerID;
        CustomerName = customerName;
        Number = number;
        Avatar = avatar;
        BirthDate = birthDate;
        Address = address;
        UserID = userID;
    }

    public CustomerModel(){

    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }



}
