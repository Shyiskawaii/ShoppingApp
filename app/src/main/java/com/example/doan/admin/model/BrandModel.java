package com.example.doan.admin.model;

public class BrandModel {
    private int BrandID;
    private String BrandName;
    private String BrandDescription;

    public BrandModel(int brandID, String brandName, String brandDescription) {
        BrandID = brandID;
        BrandName = brandName;
        BrandDescription = brandDescription;
    }

    public int getBrandID() {
        return BrandID;
    }

    public void setBrandID(int brandID) {
        BrandID = brandID;
    }

    public String getBrandName() {
        return BrandName;
    }

    public void setBrandName(String brandName) {
        BrandName = brandName;
    }

    public String getBrandDescription() {
        return BrandDescription;
    }

    public void setBrandDescription(String brandDescription) {
        BrandDescription = brandDescription;
    }
}
