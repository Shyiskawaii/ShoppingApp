package com.example.doan.admin.model;

import java.sql.Blob;

public class PhoneModel {
    private int PhoneID;
    private String PhoneName;
    private String PhoneDescription;
    private byte[] PhoneImage;

    private int Price;
    private int Discount;
    private int Views;
    private int Bought;
    private int CategoryID;
    private int BrandID;
    private int SpecificationID;

    public PhoneModel(int phoneID, String phoneName, String phoneDescription, byte[] phoneImage, int price, int discount, int views, int bought, int categoryID, int brandID, int specificationID) {
        PhoneID = phoneID;
        PhoneName = phoneName;
        PhoneDescription = phoneDescription;
        PhoneImage = phoneImage;
        Price = price;
        Discount = discount;
        Views = views;
        Bought = bought;
        CategoryID = categoryID;
        BrandID = brandID;
        SpecificationID = specificationID;
    }
    public PhoneModel(){
    }

    public int getPhoneID() {
        return PhoneID;
    }

    public void setPhoneID(int phoneID) {
        PhoneID = phoneID;
    }

    public String getPhoneName() {
        return PhoneName;
    }

    public void setPhoneName(String phoneName) {
        PhoneName = phoneName;
    }

    public String getPhoneDescription() {
        return PhoneDescription;
    }

    public void setPhoneDescription(String phoneDescription) {
        PhoneDescription = phoneDescription;
    }

    public byte[] getPhoneImage() {
        return PhoneImage;
    }

    public void setPhoneImage(byte[] phoneImage) {
        PhoneImage = phoneImage;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public int getDiscount() {
        return Discount;
    }

    public void setDiscount(int discount) {
        Discount = discount;
    }

    public int getViews() {
        return Views;
    }

    public void setViews(int views) {
        Views = views;
    }

    public int getBought() {
        return Bought;
    }

    public void setBought(int bought) {
        Bought = bought;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }

    public int getBrandID() {
        return BrandID;
    }

    public void setBrandID(int brandID) {
        BrandID = brandID;
    }

    public int getSpecificationID() {return SpecificationID;}

    public void setSpecificationID(int specificationID) {SpecificationID = specificationID;}
}
