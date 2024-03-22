package com.example.doan.admin.model;

public class PhoneModel {
    private int PhoneID;
    private String PhoneName;
    private String PhoneDescription;
    private String Views;
    private String Bought;
    private int CategoryID;
    private int BrandID;
    private int SpecificationID;

    public PhoneModel(int phoneID, String phoneName, String phoneDescription, String views, String bought, int categoryID, int brandID, int specificationID) {
        PhoneID = phoneID;
        PhoneName = phoneName;
        PhoneDescription = phoneDescription;
        Views = views;
        Bought = bought;
        CategoryID = categoryID;
        BrandID = brandID;
        SpecificationID = specificationID;
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

    public String getViews() {
        return Views;
    }

    public void setViews(String views) {
        Views = views;
    }

    public String getBought() {
        return Bought;
    }

    public void setBought(String bought) {
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
